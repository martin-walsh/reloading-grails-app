package reloading.grails.app

import org.quartz.JobExecutionContext
import org.quartz.Trigger
import org.quartz.impl.triggers.CronTriggerImpl
import org.springframework.beans.factory.InitializingBean

/**
 * Grails Quartz Jobs are not Singletons, but they are of type InitializingBean.
 * afterPropertiesSet() can be used to grab config values
 */
class DemoJob implements InitializingBean {
    def concurrent = false

    def grailsApplication

    String cronExpression
    boolean enabled = false

    static triggers = {
        //Configured by config...
    }

    def variable = 'hello'

    def execute(context) {
        if (!enabled) {
            return
        }
        reconfigure(context)

        println "Running at ${new Date().format('yyyy-MM-dd HH:mm:ss')} with cronExpression: '$cronExpression'"
    }

    @Override
    void reconfigure(JobExecutionContext context) {
        def trigger = context.trigger
        if (trigger instanceof CronTriggerImpl) {
            if (trigger.cronExpression != cronExpression) {
                DemoJob.unschedule(trigger.name, trigger.group)
                DemoJob.schedule(cronExpression)

                println "Reconfigured DemoJob"
            }
        }
    }

    @Override
    void afterPropertiesSet() throws Exception {
        enabled = grailsApplication.config.job.enabled
        cronExpression = grailsApplication.config.job.cronExpression
    }
}
