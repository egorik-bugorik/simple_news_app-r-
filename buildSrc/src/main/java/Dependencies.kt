package buildSrc.src.main.java



object Versions{
    val dagHilt = "2.44"
    val retrofit  = "2.11.0"
    val dagger = ""



}


object Retrofit{
    val retrofitItself="com.squareup.retrofit2:retrofit:"+Versions.retrofit
    val gsonConvrt="com.squareup.retrofit2:converter-gson:" +Versions.retrofit



}
object Dagger{

    val dagger_lib = "com.google.dagger:dagger:"+Versions.dagger
}

object DaggerHilt{
    val hilt =   "com.google.dagger:hilt-android:"+Versions.dagHilt
    val compiler =   "com.google.dagger:hilt-android-compiler:"+Versions.dagHilt
}