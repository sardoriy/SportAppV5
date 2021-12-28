package uz.mufassal.apihomework.network.data

data class FruitResponse(
    val genus: String,
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val nutritions: FruitNutritions,
)

data class FruitNutritions(
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double,
)