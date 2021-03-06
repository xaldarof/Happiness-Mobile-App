package pdf.reader.happiness.core

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import pdf.reader.happiness.data.cache.models.*
import pdf.reader.happiness.data.cache.dao.*

@Database(
    entities = [InfoModelDb::class, AchievementModelDb::class, ChapterModelDb::class, CoinModelDb::class, TokenModelDb::class],
    version = 27, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun coreDaoProvider(): CoreDao
    abstract fun toolsDaoProvider(): ToolsDao
    abstract fun chaptersDaoProvider(): ChaptersDao
    abstract fun achievementDaoProvider(): AchievementDao

    companion object {
        private const val DATABASE_NAME = "DATABASE"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}