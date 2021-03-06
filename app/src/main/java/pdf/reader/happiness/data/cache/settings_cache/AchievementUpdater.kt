package pdf.reader.happiness.data.cache.settings_cache

import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.cache.core.AchievementRepository

interface AchievementUpdater {

    fun addAchievementAllFinished()
    fun addAchievementAllSuccessFinished()
    fun addAchievementAllLifeFinished()
    fun addAchievementAllLoveFinished()
    fun addAchievementAllHappyFinished()

    class Base(private val achievementRepository: AchievementRepository,private val badgeController: BadgeController) :
        AchievementUpdater {
        override fun addAchievementAllFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили все разделы",
                    System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
            badgeController.updateBadge()
        }

        override fun addAchievementAllSuccessFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Успех'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
            badgeController.updateBadge()
        }

        override fun addAchievementAllLifeFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Жизнь'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
            badgeController.updateBadge()
        }

        override fun addAchievementAllLoveFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Любовь'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
            badgeController.updateBadge()
        }

        override fun addAchievementAllHappyFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Счастье'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
            badgeController.updateBadge()
        }
    }
}