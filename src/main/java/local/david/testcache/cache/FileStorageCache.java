package local.david.testcache.cache;

import java.io.*;
import java.util.Objects;

/**
 * Created by david on 23.03.17.
 */
public class FileStorageCache implements Cache {
    private String fileName = "/tmp/cache";

    public <T extends Cachable> void put(T entity) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends Cachable> T get(String key, Class<T> type) {
        return type.cast(get(key));
    }

    public Cachable get(String key) {
        Cachable cachableCandidate = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));) {
            cachableCandidate = loopInStream(key, ois);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cachableCandidate;

    }

    private Cachable loopInStream(String key, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Cachable cachableCandidate = null;
        boolean isFinished = false;
        while (isFinished) {
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

    }

    public <T extends Cachable> boolean contains(T entity) {
        Cachable cachable = get(entity.getKey());
        return cachable != null;
    }
}
