package by.gorik.newsapp.data.model

import com.google.gson.annotations.SerializedName

class NewsSourcesResponse(

    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<ApiSource> = ArrayList(),
)