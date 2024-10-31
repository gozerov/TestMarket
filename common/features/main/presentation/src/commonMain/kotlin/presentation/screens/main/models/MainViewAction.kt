package presentation.screens.main.models

sealed interface MainViewAction {

    class ShowError(val message: String) : MainViewAction

}