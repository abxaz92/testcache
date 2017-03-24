package local.david.testcache;

import local.david.testcache.cache.Cache;
import local.david.testcache.cache.CacheFactory;
import local.david.testcache.entities.SomeObject;

import java.util.logging.Logger;

/**
 * Created by david on 23.03.17.
 */
public class App {
    private static final Logger logger = Logger.getLogger("App");

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("Illegal cache level arg");

        CacheFactory cacheFactory = new CacheFactory();
        int cacheLevel = Integer.parseInt(args[0]);
        Cache cache = cacheFactory.getCache(cacheLevel);

        SomeObject object1 = new SomeObject("1", "1");
        SomeObject object2 = new SomeObject("2", "2");

        cache.put(object1);
        cache.put(object2);
        logger.info(cache.get("1", SomeObject.class).toString());
        logger.info(cache.get("2").toString());
    }
}
