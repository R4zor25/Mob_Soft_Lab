package hu.bme.aut.mob_soft_lab.model

data class GotCharacterList(
    var characterList: MutableList<GotCharacter> = mutableListOf()
)