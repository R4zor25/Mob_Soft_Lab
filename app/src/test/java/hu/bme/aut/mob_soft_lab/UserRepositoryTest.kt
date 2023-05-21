package hu.bme.aut.mob_soft_lab

import hu.bme.aut.mob_soft_lab.persistence.MockUserRepository
import hu.bme.aut.mob_soft_lab.persistence.UserEntity
import org.junit.Before
import org.junit.Test


class UserRepositoryTest {

    lateinit var mockUserRepository : MockUserRepository

    @Before
    fun setup(){
        mockUserRepository = MockUserRepository()
    }

    @Test
    fun getUserByUserNameTest(){
        val user = mockUserRepository.getUserByUserName("Test1")
        assert(user != null && user.userName == "Test1")
        val user1 = mockUserRepository.getUserByUserName("Test2")
        assert(user1 == null)
    }

    @Test
    fun insertUserTest(){
        val user = UserEntity(userName ="userName")
        mockUserRepository.insertUser(user)
        assert(mockUserRepository.mockList.size == 2)

        val user1 = UserEntity(id = 1L, userName ="userName")
        mockUserRepository.insertUser(user1)
        assert(mockUserRepository.mockList.size == 2 && mockUserRepository.mockList[1].id == 1L)

    }
}