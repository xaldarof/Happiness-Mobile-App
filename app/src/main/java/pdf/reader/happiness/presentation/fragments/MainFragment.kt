package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.KonfettiView
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.settings_cache.CongratulationController
import pdf.reader.happiness.data.cache.settings_cache.ThemeController
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.MainFragmentPresenter
import pdf.reader.happiness.presentation.adapter.ChapterItemAdapter
import pdf.reader.happiness.tools.*
import pdf.reader.happiness.vm.MainFragmentViewModel

@KoinApiExtension
class MainFragment : Fragment(), KoinComponent, ChapterItemAdapter.OnClick,
    MainFragmentPresenter.MyView {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel = get()
    private lateinit var chapterItemAdapter: ChapterItemAdapter
    private val achievementUpdater:AchievementUpdater by inject()
    private val congratulationController:CongratulationController by inject()
    private val presenter = MainFragmentPresenter(this,achievementUpdater,congratulationController)
    private lateinit var konfettiView: KonfettiView
    private val themeController:ThemeController by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        konfettiView = requireActivity().findViewById(R.id.congratulationView)
        chapterItemAdapter = ChapterItemAdapter(this,themeController)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = chapterItemAdapter
        binding.rv.isNestedScrollingEnabled = false

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            update()
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).launch {
            updateCore()
        }
    }

    private suspend fun updateCore() {
        viewModel.fetchAll().observeForever {
            presenter.updateCoreProgress(it)
        }
    }

    private suspend fun update() {
        while (true) {
            viewModel.fetchChapters().observeForever {
                chapterItemAdapter.update(it)
            }
            delay(1000)
        }
    }

    override fun updateCoreProgress(percent: Float) {
        binding.progressCore.progress = percent
        binding.progressCore.setEndProgress(percent)
        binding.progressCore.startProgressAnimation()
    }

    override fun allChaptersFinished() {
        CongratulationView(konfettiView).show()
    }

    override fun onClick(chapter: ChapterModel) {
        ChaptersFragmentLocator(this, chapter).locateFragment(chapter.fragmentName)
    }
}