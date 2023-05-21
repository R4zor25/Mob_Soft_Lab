package hu.bme.aut.mob_soft_lab.network

import hu.bme.aut.mob_soft_lab.model.GotCharacter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

interface IBackendService{

    fun getCharactersByPaging(page: Int, pageSize: Int = 50) : List<GotCharacter>
    fun getCharacterById(characterId: Long): GotCharacter?

}

@Singleton
class BackendService : IBackendService {

    val okHttpClient = OkHttpClient.Builder().apply {
    //    ignoreAllSSLErrors()
    }.build()
    private val retrofitService by lazy {
        Retrofit.Builder()
            .baseUrl("https://anapioficeandfire.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(IRetrofitService::class.java)
    }

    override fun getCharactersByPaging(page: Int, pageSize: Int): List<GotCharacter> {
        val response = retrofitService.getCharactersWithPaging(page, pageSize).execute()
        return response.body() as List<GotCharacter>
    }

    override fun getCharacterById(characterId: Long): GotCharacter? {
        val response = retrofitService.getCharacterById(characterId).execute()
        return response.body() as GotCharacter
    }
}