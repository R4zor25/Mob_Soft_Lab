package hu.bme.aut.mob_soft_lab.persistence

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface IUserRepository{
    fun getUserByUserName(userName: String): UserEntity?
    fun insertUser(user: UserEntity)
}

@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val userDao: UserDao
) : IUserRepository{

    override fun getUserByUserName(userName: String): UserEntity? {
        return userDao.getUserByName(userName)
    }

    override fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

}