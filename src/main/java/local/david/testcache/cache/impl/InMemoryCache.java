package local.david.testcache.cache.impl;

import local.david.testcache.cache.Cachable;
import local.david.testcache.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by david on 23.03.17.
 */
public class InMemoryCache implements Cache {
    private Map<String, Cachable> storageMap = new ConcurrentHashMap();

    public <T extends Cachable> void put(T entity) {
        storageMap.put(entity.getKey(), entity);
    }

    public <T extends Cachable> T get(String key, Class<T> type) {
        return type.cast(storageMap.get(key));
    }

    public Cachable get(String key) {
        return storageMap.get(key);
    }

    public void remove(String key) {
        storageMap.remove(key);
    }

    public <T extends Cachable> boolean contains(T entity) {
        return storageMap.containsKey(entity.getKey());
    }
}
