package hu.bme.aut.mob_soft_lab.persistence

class MockUserRepository : IUserRepository {
    override fun getUserByUserName(userName: String): UserEntity? {
        TODO("Not yet implemented")
    }

    override fun insertUser(user: UserEntity) {
        TODO("Not yet implemented")
    }
}