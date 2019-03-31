package gui.view.welcome

import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import tornadofx.Wizard
import tornadofx.enableWhen
import tornadofx.runLater

class WelcomeScreenWizard : Wizard("Formiranje rasporeda") {

    init {
        //TODO graphic = ...
        add(WelcomeStep1::class)
        add(WelcomeStep2::class)
        add(WelcomeStep3::class)

        // Preimenovanje elemenata na srpski
        stepsTextProperty.value = "Koraci"
        backButtonTextProperty.value = "Predhodno"
        nextButtonTextProperty.value = "Sledeće"
        cancelButtonTextProperty.value = "Otkaži"
        finishButtonTextProperty.value = "Gotovo"

        // Ručno ispravljanje baga (ukloniti kada izađe sledeća verzija TornadoFX-a)
        // (videti https://github.com/edvin/tornadofx/commit/1e8fdc7158a270fe73ada2cac84d5530b97e8823)
        runLater {
            (root.bottom as ButtonBar).buttons
                .filterIsInstance<Button>()
                .first { it.text == "Sledeće" }
                .enableWhen(canGoNext.and(hasNext).and(currentPageComplete))
        }
    }
}