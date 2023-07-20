package app.suhasdissa.foode.backend.models

data class NutritionTableData(
    val heading: Triple<String, String, String>,
    val data: List<Triple<String, String, String>>
)
