package hu.bme.aut.mob_soft_lab.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.mob_soft_lab.network.BackendService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val backendService: BackendService
) : ViewModel() {

    private val coroutineScope = MainScope()

    private val _viewState: MutableStateFlow<CharacterListViewState> = MutableStateFlow(CharacterListViewState())
    val viewState = _viewState.asStateFlow()

    private val _oneShotEvents = Channel<CharacterListOneShotEvent>(Channel.BUFFERED)
    val oneShotEvent = _oneShotEvents.receiveAsFlow()

    init {
        loadNextItems()
    }

    fun onAction(characterListUiAction: CharacterListUiAction){
        when(characterListUiAction){
            CharacterListUiAction.GetNextPage -> {
                loadNextItems()
            }
        }
    }

    private fun loadNextItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val characterList = backendService.getCharactersByPaging(_viewState.value.page)
            _viewState.value.characterList.addAll(characterList)
            _viewState.value = _viewState.value.copy(characterList = _viewState.value.characterList, page = _viewState.value.page + 1)
        }
    }
}