package ru.lffq.fmaster.feature_rskrf.domain.model

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.ProductGroupsModel

data class ProductGroups(
    val id: Int,
    val title: String,
    val groups: List<ProductGroup>,
    val totalReviewedProducts: Int,
) {
    data class ProductGroup(
        val id: Int,
        val title: String,
        val thumbnail: String,
        val qualityMarks: Int,
        val badQualityProducts: Int,
    )
}

fun ProductGroupsModel.toProductGroups(): ProductGroups {
    //как удобно пользоваться copilot'ом оказывается)
    return this.response.let {
        ProductGroups(
            id = it.id,
            title = it.title,
            groups = it.productGroups.map { productGroup ->
                ProductGroups.ProductGroup(
                    id = productGroup.id,
                    title = productGroup.title,
                    thumbnail = productGroup.thumbnail,
                    qualityMarks = productGroup.qualityMarks,
                    badQualityProducts = productGroup.badQualityProducts,
                )
            },
            totalReviewedProducts = it.totalReviewedProducts,
        )
    }
}