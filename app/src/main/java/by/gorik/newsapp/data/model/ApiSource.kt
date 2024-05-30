package by.gorik.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("name")
    val name :String = "",
    @SerializedName("id")
    val id :String? = null
) {
}