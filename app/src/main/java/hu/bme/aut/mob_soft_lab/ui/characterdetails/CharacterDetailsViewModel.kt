package hu.bme.aut.mob_soft_lab.ui.characterdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.getColumnIndexOrThrow
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.persistence.UserEntity
import hu.bme.aut.mob_soft_lab.persistence.UserRepository
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor() : ViewModel() {

    private val coroutineScope = MainScope()

    @Inject
    lateinit var  userRepository: UserRepository

    private val _viewState: MutableStateFlow<CharacterDetailsViewState> = MutableStateFlow(CharacterDetailsViewState())
    val viewState = _viewState.asStateFlow()

    private val _oneShotEvents = Channel<CharacterDetailsOneShotEvent>(Channel.BUFFERED)
    val oneShotEvent = _oneShotEvents.receiveAsFlow()

    fun onAction(characterDetailsUiAction: CharacterDetailsUiAction){
        when(characterDetailsUiAction){

            is CharacterDetailsUiAction.HandleLovedButtonPress -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (isCharacterLoved(characterDetailsUiAction.gotCharacter)) {
                        UserRepository.loggedInUserEntity?.lovedCharacterList?.characterList?.remove(characterDetailsUiAction.gotCharacter)
                        userRepository.updateUser(UserRepository.loggedInUserEntity!!)
                    } else {
                        UserRepository.loggedInUserEntity?.lovedCharacterList?.characterList?.add(characterDetailsUiAction.gotCharacter)
                        userRepository.updateUser(UserRepository.loggedInUserEntity!!)
                    }
                    _oneShotEvents.send(CharacterDetailsOneShotEvent.CharacterStateChanged)
                }
            }
            else -> {}
        }
    }

    fun isCharacterLoved(gotCharacter: GotCharacter?): Boolean {
        return UserRepository.loggedInUserEntity?.lovedCharacterList?.characterList?.contains(gotCharacter) == true
    }
}