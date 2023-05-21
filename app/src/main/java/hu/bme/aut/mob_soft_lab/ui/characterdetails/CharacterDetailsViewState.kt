package hu.bme.aut.mob_soft_lab.ui.characterdetails

import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListOneShotEvent

data class CharacterDetailsViewState(
    val isLoading: Boolean = false
)

sealed class CharacterDetailsOneShotEvent{
    data class ShowToastMessage(val errorText: String): CharacterDetailsOneShotEvent()
    object CharacterStateChanged : CharacterDetailsOneShotEvent()
}

sealed class CharacterDetailsUiAction{
    data class HandleLovedButtonPress(val gotCharacter: GotCharacter): CharacterDetailsUiAction()
}