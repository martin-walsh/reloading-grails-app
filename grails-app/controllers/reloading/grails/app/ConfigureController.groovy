package reloading.grails.app

import grails.util.Environment

class ConfigureController {
    def configure() {
        // Merge the altered config:
        def configFile = System.getProperty('CONFIG_FILE')
        grailsApplication.config.merge(new ConfigSlurper(Environment.current.name).parse(new File(configFile).text))

        // Now re-configure the beans:
        grailsApplication.mainContext.getBeansOfType(DynamicallyConfigurable).each { beanName, dynamicallyConfigurableBean ->
            println "Reconfiguring $beanName"
            dynamicallyConfigurableBean.reconfigure()
        }

        render(status: 200)
    }
}
