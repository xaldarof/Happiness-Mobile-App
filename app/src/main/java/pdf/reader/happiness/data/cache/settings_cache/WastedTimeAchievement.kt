package pdf.reader.happiness.data.cache.settings_cache

import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.cache.core.AchievementRepository

interface WastedTimeAchievement {

    fun addAchievementWasted1HourTime()
    fun addAchievementWasted2HourTime()
    fun addAchievementWasted3HourTime()

    class Base(private val achievementRepository: AchievementRepository,
               private val badgeController: BadgeController): WastedTimeAchievement {

        override fun addAchievementWasted1HourTime() {
                achievementRepository.insertAchievement(
                    AchievementModel("Вы получили достижение 'Мудрец'",
                        System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED)
                )
                badgeController.updateBadge()
        }

        override fun addAchievementWasted2HourTime() {
            achievementRepository.insertAchievement(
                AchievementModel("Вы получили достижение 'Повелитель'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED)
            )
            badgeController.updateBadge()
        }

        override fun addAchievementWasted3HourTime() {
            achievementRepository.insertAchievement(
                AchievementModel("Вы получили достижение 'Вундеркинд'",
                    System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED)
            )
            badgeController.updateBadge()
        }

    }
}