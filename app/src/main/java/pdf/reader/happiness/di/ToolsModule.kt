package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.tools.AssetReader
import pdf.reader.happiness.tools.MusicPlayer
import pdf.reader.happiness.tools.PercentCalculator
import pdf.reader.happiness.tools.RewardedAdManager

val tools = module {
    factory<AssetReader> { AssetReader.Base(androidContext()) }
    factory<PercentCalculator> { PercentCalculator.Base() }
    factory<MusicPlayer> { MusicPlayer() }

}
