package local.david.testcache.cache;

/**
 * Created by david on 23.03.17.
 */
public interface Cache {
    <T extends Cachable> void put(T entity);

    <T extends Cachable> T get(String key, Class<T> type);

    Cachable get(String key);

    void remove(String key);

    <T extends Cachable> boolean contains(T entity);
}
