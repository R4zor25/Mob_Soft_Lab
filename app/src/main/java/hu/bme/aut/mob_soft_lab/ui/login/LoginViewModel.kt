package hu.bme.aut.mob_soft_lab.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.mob_soft_lab.persistence.UserEntity
import hu.bme.aut.mob_soft_lab.persistence.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val coroutineScope = MainScope()

    @Inject
    lateinit var  userRepository: UserRepository

    private val _viewState: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState())
    val viewState = _viewState.asStateFlow()

    private val _oneShotEvents = Channel<LoginOneShotEvent>(Channel.BUFFERED)
    val oneShotEvent = _oneShotEvents.receiveAsFlow()

    fun onAction(loginUiAction: LoginUiAction){
        when(loginUiAction){
            is LoginUiAction.Login -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val user = userRepository.getUserByUserName(loginUiAction.userName)
                    if (user == null) {
                        userRepository.insertUser(
                            UserEntity(userName = loginUiAction.userName)
                        )
                    }else{
                        UserRepository.loggedInUserEntity = user
                    }
                    _oneShotEvents.send(LoginOneShotEvent.NavigateToCharacterList)
                }
            }
        }
    }
}