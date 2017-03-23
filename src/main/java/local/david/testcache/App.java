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
        CacheFactory cacheFactory = new CacheFactory();
        Cache cache = cacheFactory.getCache(Integer.parseInt(args[0]));

        SomeObject object1 = new SomeObject();
        object1.setKey("1");
        object1.setName("1");

        SomeObject object2 = new SomeObject();
        object2.setKey("2");
        object2.setName("2");

        cache.put(object1);
        cache.put(object2);
        logger.info(cache.get("1", SomeObject.class).toString());
        logger.info(cache.get("2").toString());
    }
}
