package pdf.reader.happiness.data.core

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.models.Type

@KoinApiExtension
class DataRepositoryTest :KoinComponent {

    private val repository:DataRepository by inject()

    /**
     *  checked when actual size of data was 105
     */

    @Test
    fun check_is_repo_returns_data() {
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchAllTypes().asLiveData().observeForever {
                assertEquals(105,it.size)
            }
        }
    }


    @Test
    fun check_repo_return_only_happy_type_data(){
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchHappy().asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(Type.HAPPY,it.type)
                }
                //03.10.2021 was 13 size
                assertEquals(13,it.size)
            }
        }
    }


    @Test
    fun check_repo_return_only_life_type_data(){
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchLife().asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(Type.LIFE,it.type)
                }
                //03.10.2021 was 13 size
                assertEquals(38,it.size)
            }
        }
    }

    @Test
    fun check_repo_return_only_love_type_data(){
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchLove().asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(Type.LOVE,it.type)
                }
                //03.10.2021 was 13 size
                assertEquals(25,it.size)
            }
        }
    }

    @Test
    fun check_repo_return_only_success_type_data(){
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchSuccess().asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(Type.SUCCESS,it.type)
                }
                //03.10.2021 was 13 size
                assertEquals(29,it.size)
            }
        }
    }
}