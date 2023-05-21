package hu.bme.aut.mob_soft_lab.ui.characterlist

import hu.bme.aut.mob_soft_lab.model.GotCharacter

data class CharacterListViewState(
    val characterList: MutableList<GotCharacter> = mutableListOf(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)

sealed class CharacterListOneShotEvent{
}

sealed class CharacterListUiAction{
    object GetNextPage: CharacterListUiAction()
}