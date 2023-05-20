package ru.lffq.fmaster.feature_spoonacular.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

class Instructions : ArrayList<Instructions.InstructionsItem>() {
    @Keep
    data class InstructionsItem(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("steps")
        val steps: List<Step?>? = null
    ) {
        @Keep
        data class Step(
            @SerializedName("equipment")
            val equipment: List<Equipment?>? = null,
            @SerializedName("ingredients")
            val ingredients: List<Ingredient?>? = null,
            @SerializedName("length")
            val length: Length? = null,
            @SerializedName("number")
            val number: Int? = null,
            @SerializedName("step")
            val step: String? = null
        ) {
            @Keep
            data class Equipment(
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("image")
                val image: String? = null,
                @SerializedName("localizedName")
                val localizedName: String? = null,
                @SerializedName("name")
                val name: String? = null,
                @SerializedName("temperature")
                val temperature: Temperature? = null
            ) {
                @Keep
                data class Temperature(
                    @SerializedName("number")
                    val number: Double? = null,
                    @SerializedName("unit")
                    val unit: String? = null
                )
            }

            @Keep
            data class Ingredient(
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("image")
                val image: String? = null,
                @SerializedName("localizedName")
                val localizedName: String? = null,
                @SerializedName("name")
                val name: String? = null
            )

            @Keep
            data class Length(
                @SerializedName("number")
                val number: Int? = null,
                @SerializedName("unit")
                val unit: String? = null
            )
        }
    }
}