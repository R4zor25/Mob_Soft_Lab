package hu.bme.aut.mob_soft_lab.ui.lovedcharacters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.mob_soft_lab.persistence.UserRepository
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListViewState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LovedCharactersViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val coroutineScope = MainScope()

    private val _viewState: MutableStateFlow<LovedCharactersViewState> = MutableStateFlow(LovedCharactersViewState())
    val viewState = _viewState.asStateFlow()

    private val _oneShotEvents = Channel<LovedCharactersOneShotEvent>(Channel.BUFFERED)
    val oneShotEvent = _oneShotEvents.receiveAsFlow()

    init {
        loadLovedCharactersFromDatabase()
    }

    private fun loadLovedCharactersFromDatabase() {
        _viewState.value.characterList = UserRepository.loggedInUserEntity?.lovedCharacterList?.characterList ?: mutableListOf()
    }

    fun onAction(lovedCharactersUiAction: LovedCharactersUiAction){
        when(lovedCharactersUiAction){

            else -> {}
        }
    }
}