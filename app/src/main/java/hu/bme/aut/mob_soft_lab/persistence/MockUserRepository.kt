package hu.bme.aut.mob_soft_lab.persistence

import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.model.GotCharacterList

class MockUserRepository : IUserRepository {

    val mockList: MutableList<UserEntity> = mutableListOf(
        UserEntity(id = 1L,
        userName = "Test1",
        lovedCharacterList = GotCharacterList(characterList = mutableListOf(
            GotCharacter(name = "1"),
            GotCharacter(name = "2")
        ))
        )
    )
    override fun getUserByUserName(userName: String): UserEntity? {
        return mockList.firstOrNull { it.userName == userName }
    }

    override fun insertUser(user: UserEntity) {
        if (getUserByUserName(user.userName) != null){
            mockList.set(mockList.indexOfFirst { it.userName == user.userName }, user)
        }else{
            mockList.add(user)
        }
    }

    override fun updateUser(user: UserEntity) {
        mockList[mockList.indexOfFirst { it.userName == user.userName }] = user
    }
}