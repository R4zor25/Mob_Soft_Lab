package hu.bme.aut.mob_soft_lab.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.model.GotCharacterList

@Entity("users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val userName: String = "",

    @TypeConverters(GotCharacterTypeConverter::class)
    var lovedCharacterList: GotCharacterList = GotCharacterList()
)