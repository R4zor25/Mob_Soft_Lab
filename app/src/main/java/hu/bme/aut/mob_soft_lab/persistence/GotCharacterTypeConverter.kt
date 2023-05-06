package hu.bme.aut.mob_soft_lab.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.bme.aut.mob_soft_lab.model.GotCharacter

class GotCharacterTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun gotCharacterToString(gotCharacter: GotCharacter): String {
        return gson.toJson(gotCharacter)
    }

    @TypeConverter
    fun stringToRecipe(recipeString: String): GotCharacter {
        val objectType = object : TypeToken<GotCharacter>() {}.type
        return gson.fromJson(recipeString, objectType)
    }

}