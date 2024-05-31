package by.gorik.newsapp.data.model

import com.google.gson.annotations.SerializedName

class TopHeadlinesResponse (
    @SerializedName("total")
    val total :Int = 0,
            @SerializedName("status")
    val status :String = "",
            @SerializedName("articles")
    val articles :List<ApiArticle> = ArrayList(),
)