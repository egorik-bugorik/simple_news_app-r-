package by.gorik.newsapp.data.model

import androidx.room.Entity
import by.gorik.newsapp.data.local.entity.Article
import com.google.gson.annotations.SerializedName


@Entity(tableName = "article")
data class ApiArticle(

    @SerializedName("title")
    val title:String="",

    @SerializedName("description")
    val description:String="",

    @SerializedName("url")
    val url:String="",

    @SerializedName("urlToImage")
    val urlToImage:String="",

    @SerializedName("source")
    val source:ApiSource
) {
    fun toArticle(): Article {
        return  Article(
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            source = source.

            toSource()
        )


    }





}