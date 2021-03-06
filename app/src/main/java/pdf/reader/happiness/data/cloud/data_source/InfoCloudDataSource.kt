package pdf.reader.happiness.data.cloud.data_source

import com.google.firebase.database.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import java.lang.Exception
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface InfoCloudDataSource {

    suspend fun fetchInfoAsFlow(): Flow<CopyOnWriteArrayList<InfoCloudModel>>

    class Base(private val databaseReference: DatabaseReference) : InfoCloudDataSource {

        private val cloudInfoList = CopyOnWriteArrayList<InfoCloudModel>()

        override suspend fun fetchInfoAsFlow() = suspendCoroutine<Flow<CopyOnWriteArrayList<InfoCloudModel>>> {
            val list = CopyOnWriteArrayList<InfoCloudModel>()

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    try {
                        list.clear()
                        val child = p0.children
                        child.forEach {
                            val value = it.getValue(InfoCloudModel::class.java)
                            value?.let { model ->
                                if (model.access) {
                                    list.add(model)
                                }
                            }
                        }
                        cloudInfoList.clear()
                        cloudInfoList.addAll(list)
                        it.resume(flow { emit(cloudInfoList) })

                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }
                }
                override fun onCancelled(p0: DatabaseError) {

                }
            })
        }
    }
}