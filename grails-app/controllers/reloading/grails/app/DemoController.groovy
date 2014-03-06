package reloading.grails.app

class DemoController {
    Assistant personalAssistant

    def demo() {
        render(status: 200, text: personalAssistant.describe())
    }
}
