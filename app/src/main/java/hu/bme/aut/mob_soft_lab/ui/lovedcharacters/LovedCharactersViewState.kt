package hu.bme.aut.mob_soft_lab.ui.lovedcharacters

import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListOneShotEvent

data class LovedCharactersViewState(
    var characterList: MutableList<GotCharacter> = mutableListOf(),
)

sealed class LovedCharactersOneShotEvent{
}

sealed class LovedCharactersUiAction{

}