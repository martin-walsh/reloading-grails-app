import reloading.grails.app.DynamicallyConfigurable

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        //Mixin all DynamicallyConfigurable beans with utility methods
        grailsApplication.mainContext.getBeansOfType(DynamicallyConfigurable).each { beanName, dynamicallyConfigurableBean ->
            dynamicallyConfigurableBean.metaClass.getConfig = {
                return grailsApplication.config
            }
        }
    }
    def destroy = {
    }
}
