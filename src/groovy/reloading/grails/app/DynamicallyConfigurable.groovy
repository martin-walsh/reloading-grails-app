package reloading.grails.app

/**
 * Marker interface for triggering re-configuration of Spring Beans:
 */
public interface DynamicallyConfigurable {
    void reconfigure()
}