import reloading.grails.app.DemoJob
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

        log.info("Configuring JOBs...")
        DemoJob.schedule(grailsApplication.config.job.cronExpression)
    }
    def destroy = {
    }
}
