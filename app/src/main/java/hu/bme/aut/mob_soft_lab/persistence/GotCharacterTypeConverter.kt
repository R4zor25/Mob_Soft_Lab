package hu.bme.aut.mob_soft_lab.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.bme.aut.mob_soft_lab.model.GotCharacterList

@ProvidedTypeConverter
class GotCharacterTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun gotCharacterToString(gotCharacterList: GotCharacterList): String {
        return gson.toJson(gotCharacterList)
    }

    @TypeConverter
    fun stringToRecipe(characterList: String): GotCharacterList {
        val objectType = object : TypeToken<GotCharacterList>() {}.type
        return gson.fromJson(characterList, objectType)
    }

/*        @TypeConverter
        fun fromString(value: String?): ArrayList<String> {
            val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: ArrayList<String?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }*/

}