import reloading.grails.app.Assistant

// Place your Spring DSL code here
beans = {
    personalAssistant(Assistant) {
        firstName = application.config.personalAssistant.personal.firstName
        lastName = application.config.personalAssistant.personal.lastName
        age = application.config.personalAssistant.personal.age

        title = application.config.personalAssistant.professional.title
        placeOfWork = application.config.personalAssistant.professional.placeOfWork
    }
}
