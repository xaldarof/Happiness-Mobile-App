package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.data.cache.settings_cache.BadgeController

@SuppressLint("UseCompatLoadingForDrawables")
class FragmentController(
    private val activity: AppCompatActivity,
    private val fragments: List<Fragment>,
    private val badgeController: BadgeController
) : ViewPager2.OnPageChangeCallback(){

    private var viewPager2: ViewPager2 = activity.findViewById(R.id.pager)
    private var tabLayout: TabLayout

    private var fragmentAdapter: FragmentAdapter =
        FragmentAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)


    init {
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabLayout = activity.findViewById(R.id.tab_layout)
        viewPager2.adapter = fragmentAdapter
        viewPager2.offscreenPageLimit = fragmentAdapter.itemCount

        viewPager2.apply {
            registerOnPageChangeCallback(this@FragmentController)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        tabLayout.setOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.to_right_color), PorterDuff.Mode.SRC_IN)
                if (tab.position==3){
                    badgeController.clearBadge()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.defColor), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_home)
                }

                1 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_search_24)
                }

                2 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_heart)

                }
                3 ->{
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_local_florist_24)
                }

                4 ->{
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_settings_24)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                badgeObserver(tabLayout.getTabAt(3)!!.orCreateBadge)
            }

        }.attach()
    }

    private suspend fun badgeObserver(badgeDrawable: BadgeDrawable) {
        while (true){
            delay(1000)
            badgeDrawable.number = badgeController.getBadge()
            badgeDrawable.isVisible = badgeController.getBadge() != 0
        }
    }
}