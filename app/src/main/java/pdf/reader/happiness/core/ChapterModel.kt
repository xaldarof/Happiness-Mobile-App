package pdf.reader.happiness.core

import java.io.Serializable

data class ChapterModel(
    val name:String,
    val size:Int,
    val image:Int,
    val progress:Float,
    val isFinished:Boolean=false,
    val isCongratulated:Boolean=false,
    val fragmentName: FragmentName,
    val colorLight: String,
    val colorNight:String
):Serializable