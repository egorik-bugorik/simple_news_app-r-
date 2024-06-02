package by.gorik.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.gorik.newsapp.data.model.ApiArticle
import com.google.gson.annotations.SerializedName


@Entity(tableName = "article")
data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val Id:Int=0,

    @ColumnInfo("title")
    val title:String="",

    @ColumnInfo("description")
    val description:String?=null,

    @ColumnInfo("url")
    val url:String="",

    @ColumnInfo("urlToImage")
    val urlToImage:String?=null,


     @Embedded val source:Source
) {
    fun toApiArticle():ApiArticle{
        return  ApiArticle(
            title = title,
            description = description.let{it.toString()},
            url = url,


            urlToImage = urlToImage.let{it.toString()},
            source = source.toApiSource()
        )


    }



}
