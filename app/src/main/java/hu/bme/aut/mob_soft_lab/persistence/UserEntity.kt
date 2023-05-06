package hu.bme.aut.mob_soft_lab.persistence

import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hu.bme.aut.mob_soft_lab.model.GotCharacter

data class UserEntity (
    @PrimaryKey(autoGenerate = true) val
    id: Long = 0L,

    val userName: String = "",

    @TypeConverters(GotCharacterTypeConverter::class)
    var lovedCharacterList: ArrayList<GotCharacter> = arrayListOf()
)