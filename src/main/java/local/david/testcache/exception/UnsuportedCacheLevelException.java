package local.david.testcache.exception;

/**
 * Created by david on 23.03.17.
 */
public class UnsuportedCacheLevelException extends RuntimeException {
    public UnsuportedCacheLevelException(int level) {
        super("Cache level " + level + " not supports");
    }
}
