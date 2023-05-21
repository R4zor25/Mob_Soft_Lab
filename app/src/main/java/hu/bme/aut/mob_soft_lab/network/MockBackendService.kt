package hu.bme.aut.mob_soft_lab.network

import hu.bme.aut.mob_soft_lab.model.GotCharacter


class MockBackendService : IBackendService {

    val characterList: MutableList<GotCharacter> = mutableListOf(
        GotCharacter(name = "1"),
        GotCharacter(name = "2")
    )
    override fun getCharactersByPaging(page: Int, pageSize: Int): List<GotCharacter> {
        return characterList.subList(page*pageSize, (page+1) * pageSize)
    }

    override fun getCharacterById(characterId: Long): GotCharacter? {
        return characterList.find { it.name.toLong() == characterId }
    }
}