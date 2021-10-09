package pdf.reader.happiness.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pdf.reader.happiness.core.InfoModel
import java.io.Serializable

@Entity(tableName = "db")
data class InfoModelDb (
    val title: String,
    @PrimaryKey
    val body: String,

    val favorite: Boolean=false,
    val finished: Boolean=false,
    val isOpened:Boolean=false,
    val type: Type
    ): Serializable {

    fun mapToInfoModel() : InfoModel {
        return InfoModel(title, body, favorite, finished,isOpened,type)
    }
}
enum class Type {
    SUCCESS,
    LIFE,
    LOVE,
    HAPPY,
    DEFAULT
}