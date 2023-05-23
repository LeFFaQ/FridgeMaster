package ru.lffq.fmaster.feature_spoonacular.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ru.lffq.fmaster.feature_feed.domain.GenericDetailContent

@Keep
data class Information(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int? = null,
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<Any?>? = null,
    @SerializedName("cheap")
    val cheap: Boolean? = null,
    @SerializedName("creditsText")
    val creditsText: String? = null,
    @SerializedName("cuisines")
    val cuisines: List<Any?>? = null,
    @SerializedName("dairyFree")
    val dairyFree: Boolean? = null,
    @SerializedName("diets")
    val diets: List<Any?>? = null,
    @SerializedName("dishTypes")
    val dishTypes: List<String?>? = null,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    @SerializedName("gaps")
    val gaps: String? = null,
    @SerializedName("glutenFree")
    val glutenFree: Boolean? = null,
    @SerializedName("healthScore")
    val healthScore: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("imageType")
    val imageType: String? = null,
    @SerializedName("instructions")
    val instructions: String? = null,
    @SerializedName("ketogenic")
    val ketogenic: Boolean? = null,
    @SerializedName("license")
    val license: String? = null,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean? = null,
    @SerializedName("occasions")
    val occasions: List<Any?>? = null,
    @SerializedName("pricePerServing")
    val pricePerServing: Double? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerializedName("servings")
    val servings: Int? = null,
    @SerializedName("sourceName")
    val sourceName: String? = null,
    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,
    @SerializedName("spoonacularScore")
    val spoonacularScore: Double? = null,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("sustainable")
    val sustainable: Boolean? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vegan")
    val vegan: Boolean? = null,
    @SerializedName("vegetarian")
    val vegetarian: Boolean? = null,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean? = null,
    @SerializedName("veryPopular")
    val veryPopular: Boolean? = null,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null,
    @SerializedName("whole30")
    val whole30: Boolean? = null,
    @SerializedName("winePairing")
    val winePairing: WinePairing? = null
) : GenericDetailContent {
    @Keep
    data class ExtendedIngredient(
        @SerializedName("aisle")
        val aisle: String? = null,
        @SerializedName("amount")
        val amount: Double? = null,
        @SerializedName("consitency")
        val consitency: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image")
        val image: String? = null,
        @SerializedName("measures")
        val measures: Measures? = null,
        @SerializedName("meta")
        val meta: List<String?>? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("original")
        val original: String? = null,
        @SerializedName("originalName")
        val originalName: String? = null,
        @SerializedName("unit")
        val unit: String? = null
    ) {
        @Keep
        data class Measures(
            @SerializedName("metric")
            val metric: Metric? = null,
            @SerializedName("us")
            val us: Us? = null
        ) {
            @Keep
            data class Metric(
                @SerializedName("amount")
                val amount: Double? = null,
                @SerializedName("unitLong")
                val unitLong: String? = null,
                @SerializedName("unitShort")
                val unitShort: String? = null
            )

            @Keep
            data class Us(
                @SerializedName("amount")
                val amount: Double? = null,
                @SerializedName("unitLong")
                val unitLong: String? = null,
                @SerializedName("unitShort")
                val unitShort: String? = null
            )
        }
    }

    @Keep
    data class WinePairing(
        @SerializedName("pairedWines")
        val pairedWines: List<String?>? = null,
        @SerializedName("pairingText")
        val pairingText: String? = null,
        @SerializedName("productMatches")
        val productMatches: List<ProductMatche?>? = null
    ) {
        @Keep
        data class ProductMatche(
            @SerializedName("averageRating")
            val averageRating: Double? = null,
            @SerializedName("description")
            val description: String? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("imageUrl")
            val imageUrl: String? = null,
            @SerializedName("link")
            val link: String? = null,
            @SerializedName("price")
            val price: String? = null,
            @SerializedName("ratingCount")
            val ratingCount: Double? = null,
            @SerializedName("score")
            val score: Double? = null,
            @SerializedName("title")
            val title: String? = null
        )
    }
}