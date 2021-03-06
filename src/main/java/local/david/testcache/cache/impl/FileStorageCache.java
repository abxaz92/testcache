package local.david.testcache.cache.impl;

import local.david.testcache.cache.Cachable;
import local.david.testcache.cache.Cache;

import java.io.*;
import java.util.Objects;

/**
 * Created by david on 23.03.17.
 */
public class FileStorageCache implements Cache {
    private final static String CACHE_FOLDER = "/tmp";

    public <T extends Cachable> void put(T entity) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getCacheFileName(entity.getKey())))) {
            oos.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCacheFileName(String key) {
        StringBuilder stringBuilder = new StringBuilder(CACHE_FOLDER);
        stringBuilder.append("/");
        stringBuilder.append(key);
        return stringBuilder.toString();
    }

    public <T extends Cachable> T get(String key, Class<T> type) {
        return type.cast(get(key));
    }

    public Cachable get(String key) {
        File file = new File(getCacheFileName(key));
        if (!file.exists())
            return null;

        Cachable cachableCandidate = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            cachableCandidate = loopInStream(key, ois);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cachableCandidate;

    }

    private Cachable loopInStream(String key, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Cachable cachableCandidate = null;
        boolean isFinished = false;
        while (!isFinished) {
            try {
                Object obj = ois.readObject();
                Cachable currentEntity = (Cachable) obj;
                if (Objects.equals(key, currentEntity.getKey())) {
                    isFinished = true;
                    cachableCandidate = currentEntity;
                }
            } catch (EOFException eofe) {
                isFinished = true;
            }
        }
        return cachableCandidate;
    }

    public void remove(String key) {
        File file = new File(getCacheFileName(key));
        if (file.exists()) {
            file.delete();
        }
    }

    public <T extends Cachable> boolean contains(T entity) {
        File file = new File(getCacheFileName(entity.getKey()));
        return file.exists();
    }
}
