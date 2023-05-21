package hu.bme.aut.mob_soft_lab.ui.login

class LoginViewState()

sealed class LoginOneShotEvent{
    data class ShowToastMessage(val errorText: String): LoginOneShotEvent()
    object NavigateToCharacterList: LoginOneShotEvent()
}

sealed class LoginUiAction{
    data class Login(val userName: String): LoginUiAction()
}