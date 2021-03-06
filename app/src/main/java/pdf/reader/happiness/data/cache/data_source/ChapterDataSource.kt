package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.data.cache.dao.ChaptersDao

interface ChapterDataSource {

    fun fetchChapter():Flow<List<ChapterModel>>
    fun updateChapterFinishedState(isFinished:Boolean,chapterName:String)
    fun updateChapterProgress(progress:Float,chapterName:String)
    fun updateAllChapterFinished(isFinished: Boolean,chapterName:String)
    fun updateChapterCongratulated(isCongratulated: Boolean,chapterName:String)


    class Base(private val chaptersDao: ChaptersDao): ChapterDataSource {
        override fun fetchChapter(): Flow<List<ChapterModel>> =
            chaptersDao.fetchChapters().map { it.map { it.mapToChapter() } }

        override fun updateChapterFinishedState(isFinished: Boolean, chapterName: String) {
            chaptersDao.updateChapterFinishedState(isFinished,chapterName)
        }

        override fun updateChapterProgress(progress: Float, chapterName: String) {
            chaptersDao.updateChapterProgress(progress,chapterName)
        }

        override fun updateAllChapterFinished(isFinished: Boolean, chapterName: String) {
            chaptersDao.updateAllChapterFinished(isFinished,chapterName)
        }

        override fun updateChapterCongratulated(isCongratulated: Boolean, chapterName: String) {
            chaptersDao.updateChapterCongratulated(isCongratulated,chapterName)
        }
    }
}