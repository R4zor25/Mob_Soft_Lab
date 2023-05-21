package hu.bme.aut.mob_soft_lab.network

import hu.bme.aut.mob_soft_lab.model.GotCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofitService {

    //get characters with paging
    @GET("characters")
    fun getCharactersWithPaging(@Query("page") page: Int, @Query("pageSize")pageSize: Int): Call<List<GotCharacter>>

    //get character details
    @GET("characters/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Long): Call<GotCharacter>

}