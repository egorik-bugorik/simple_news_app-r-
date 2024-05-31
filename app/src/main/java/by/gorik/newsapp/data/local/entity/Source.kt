package by.gorik.newsapp.data.local.entity

import androidx.room.ColumnInfo
import by.gorik.newsapp.data.model.ApiSource
import com.google.gson.annotations.SerializedName

data class Source(
    @ColumnInfo("name")
    val name :String = "",
    @ColumnInfo("id")
    val id :String? = null
) {

    fun toApiSource():ApiSource{
        return ApiSource(
            name = name,
            id = id
        )
    }


}