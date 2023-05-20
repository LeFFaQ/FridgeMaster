package ru.lffq.fmaster.feature_detail.ui

import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_feed.ui.feed.DetailInformation

fun provideDetailChips(
    healthy: Boolean,
    popular: Boolean,
    vegetarian: Boolean,
    glutenFree: Boolean,
    dairyFree: Boolean,
    cheap: Boolean,
): Map<DetailInformation, Boolean> {
    return mapOf(
        DetailInformation(
            R.string.detail_chip_healty,
            R.drawable.chip_healthy
        ) to healthy,
        DetailInformation(
            R.string.detail_chip_cheap,
            R.drawable.chip_cheap
        ) to cheap,
        DetailInformation(
            R.string.detail_chip_dairyfree,
            R.drawable.chip_dairyfree
        ) to dairyFree,
        DetailInformation(
            R.string.detail_chip_glutenfree,
            R.drawable.chip_no_gluten
        ) to glutenFree,
        DetailInformation(
            R.string.detail_chip_vegatarian,
            R.drawable.chip_vegan
        ) to vegetarian,
        DetailInformation(
            R.string.detail_chip_verypopular,
            R.drawable.chip_popular
        ) to popular,
    )

}