package hu.bme.aut.mob_soft_lab.network

import hu.bme.aut.mob_soft_lab.model.GotCharacter


class MockBackendService : IBackendService {
    override fun getCharactersByPaging(page: Int, pageSize: Int): List<GotCharacter> {
        return emptyList()
    }

    override fun getCharacterById(characterId: Long): GotCharacter {
        return GotCharacter()
    }
}