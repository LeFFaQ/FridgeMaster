package ru.lffq.fmaster.feature_fakedata

import androidx.annotation.StringRes
import ru.lffq.fmaster.R

object FoodInFridge {

    val foods = mutableListOf(
        Food(
            type = Food.FoodType.DRINK,
            name = "Добрый Cola",
            image = "https://irecommend.ru/sites/default/files/imagecache/copyright1/user-images/1144350/0vZxW5GI0tG7USxEtIPsNg.jpeg",
            amount = 2,
            stringForSpoonApi = "coke",
            amountMeasure = Food.MeasureType.PCS
        ),
        Food(
            type = Food.FoodType.VEGETABLE,
            name = "Картофель",
            stringForSpoonApi = "potato",
            image = "https://static.mk.ru/upload/entities/2021/03/31/09/articles/facebookPicture/6c/9f/07/6c/2b4a4dda788c5fff91e3bcfc447f3f13.jpg",
            amount = 1.6,
            amountMeasure = Food.MeasureType.KILOGRAM
        ),
        Food(
            type = Food.FoodType.VEGETABLE,
            name = "Морковь",
            stringForSpoonApi = "carrot",
            image = "https://mebellka.ru/wp-content/uploads/6/2/d/62d8efd01058343bc875254b98a48b4e.jpeg",
            amount = 700,
            amountMeasure = Food.MeasureType.GRAM //"Гр."
        ),
        Food(
            type = Food.FoodType.VEGETABLE,
            name = "Лук репчатый",
            stringForSpoonApi = "Onion",
            image = "https://www.topnews.ru/upload/img/d19d692961.jpg",
            amount = 140,
            amountMeasure = Food.MeasureType.GRAM //"Гр."
        ),
        Food(
            type = Food.FoodType.MILK,
            name = "Сметана 20%",
            stringForSpoonApi = "sour cream",
            image = "https://static.price.ru/images/models/-/smetana/prostokvashino-smetana-20-315-g/49b645c6d056bfbb413ae26d78432b53.JPEG",
            amount = 1,
            amountMeasure = Food.MeasureType.PCS //"Шт."
        ),
        Food(
            type = Food.FoodType.MILK,
            name = "Молоко Отборное",
            stringForSpoonApi = "milk",
            image = "https://rskrf.ru/upload/iblock/937/937a8d72af96c5f37170c5328c8d4b8e.jpg",
            amount = 1,
            amountMeasure = Food.MeasureType.PCS //"Шт."
        ),
        Food(
            type = Food.FoodType.FRUIT,
            name = "Яблоки",
            stringForSpoonApi = "apples",
            image = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/apples-royalty-free-image-164084111-1537885595.jpg",
            amount = 12,
            amountMeasure = Food.MeasureType.PCS //"Шт."
        ),
        Food(
            type = Food.FoodType.MEAT,
            name = "Свиное филе",
            stringForSpoonApi = "pork",
            image = "https://fb.ru/misc/i/gallery/59230/2558414.jpg",
            amount = 500,
            amountMeasure = Food.MeasureType.GRAM //"Гр."
        ),
        Food(
            type = Food.FoodType.FISH,
            name = "Красная икра",
            stringForSpoonApi = "caviar",
            image = "https://catherineasquithgallery.com/uploads/posts/2021-02/1612938655_61-p-fon-krasnoi-ikri-73.jpg",
            amount = 100,
            amountMeasure = Food.MeasureType.GRAM //"Гр."
        ),

        )

}

data class Food(
    val type: FoodType,
    val name: String,
    val stringForSpoonApi: String,
    val image: String,
    var amount: Number,
    val amountMeasure: MeasureType,
) {
    enum class FoodType(@StringRes val title: Int) {
        FRUIT(R.string.inventory_fruit_title),
        VEGETABLE(R.string.inventory_vegetable_title),
        DRINK(R.string.inventory_drink_title),
        MEAT(R.string.inventory_meat_title),
        FISH(R.string.inventory_fish_title),
        MILK(R.string.inventory_milk_title),
    }

    enum class MeasureType(@StringRes val title: Int) {
        GRAM(R.string.inventory_measure_gr),
        KILOGRAM(R.string.inventory_measure_kg),
        LITER(R.string.inventory_measure_liter),
        PCS(R.string.inventory_measure_pcs),
    }
}
