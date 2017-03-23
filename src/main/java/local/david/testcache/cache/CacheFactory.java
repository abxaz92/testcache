package local.david.testcache.cache;

import local.david.testcache.cache.impl.FileStorageCache;
import local.david.testcache.cache.impl.InMemoryCache;
import local.david.testcache.exception.UnsuportedCacheLevelException;

/**
 * Created by david on 23.03.17.
 */
public class CacheFactory {
    public Cache getCache(int level) {
        switch (level) {
            case 1:
                return new InMemoryCache();
            case 2:
                return new FileStorageCache();
        }
        throw new UnsuportedCacheLevelException(level);
    }
}
