package hu.bme.aut.mob_soft_lab.persistence

import androidx.room.*
import hu.bme.aut.mob_soft_lab.model.GotCharacter

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM users where userName = :userName")
    fun getUserByName(userName: String): UserEntity?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)


}