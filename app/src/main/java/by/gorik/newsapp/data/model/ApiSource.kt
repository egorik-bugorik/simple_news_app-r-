package by.gorik.newsapp.data.model

import by.gorik.newsapp.data.local.entity.Source
import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("name")
    val name :String = "",
    @SerializedName("id")
    val id :String? = null
) {
    fun toSource(): Source {
        return Source(
            name = name,
            id= id
        )
    }
}