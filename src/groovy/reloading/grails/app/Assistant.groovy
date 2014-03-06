package reloading.grails.app

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class Assistant implements DynamicallyConfigurable {
    String firstName
    String lastName
    Integer age

    String title
    String placeOfWork

    String describe() {
        return "My name is $firstName $lastName, I am $age years of age. I work as a $title at the $placeOfWork."
    }

    @Override
    void reconfigure() {
        firstName = config.personalAssistant.personal.firstName
        lastName = config.personalAssistant.personal.lastName
        age = config.personalAssistant.personal.age

        title = config.personalAssistant.professional.title
        placeOfWork = config.personalAssistant.professional.placeOfWork
    }
}
