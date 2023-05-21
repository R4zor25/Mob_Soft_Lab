package hu.bme.aut.mob_soft_lab

import hu.bme.aut.mob_soft_lab.network.MockBackendService
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BackendServiceTest {

    private lateinit var mockBackendService : MockBackendService

    @Before
    fun setup(){
        mockBackendService = MockBackendService()
    }

    @Test
    fun testPaging(){
        val list = mockBackendService.getCharactersByPaging(page = 0, pageSize = 1).toMutableList()
        assert(list.size == 1)
        list.addAll(mockBackendService.getCharactersByPaging(page = 1, pageSize = 1))
        assert(list.size == 2)
    }

    @Test
    fun testGetCharacterById(){
        val character1 = mockBackendService.getCharacterById(1)
        assert(character1 != null && character1.name == "1")

        val character2 = mockBackendService.getCharacterById(2)
        assert(character2 != null && character2.name == "2")

        val character3 = mockBackendService.getCharacterById(3)
        assert(character3 == null)
    }
}