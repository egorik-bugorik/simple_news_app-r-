package buildSrc.src.main.java



object Versions{
    val browser= "1.4.0"
    val coil = "2.6.0"
    val navigation ="2.7.7"
    val paging = "3.3.0"
    val dagHilt = "2.51.1"
    val retrofit  = "2.11.0"
    val room  = "2.6.1"



}


object Retrofit{
    val retrofitItself="com.squareup.retrofit2:retrofit:"+Versions.retrofit
    val gsonConvrt="com.squareup.retrofit2:converter-gson:" +Versions.retrofit



}
object Coil{
    val coilCompose="io.coil-kt:coil-compose:"+Versions.coil
}
object Browser{
    val browser="androidx.browser:browser:"+Versions.browser
}
object Paging{
    val pagingRuntime="androidx.paging:paging-runtime:"+Versions.paging
    val compose="androidx.paging:paging-compose:" +Versions.paging



}
object Navigation{
    val navigationUi="androidx.navigation:navigation-ui-ktx:"+Versions.navigation
    val navigationCompose="androidx.navigation:navigation-compose:" +Versions.navigation



}



object DaggerHilt{
    val hilt =   "com.google.dagger:hilt-android:"+Versions.dagHilt
    val hiltRoot =   "com.google.dagger.hilt.android"
    val hiltNavigatioCompose =   "androidx.hilt:hilt-navigation-compose:1.2.0"
    val compiler =   "com.google.dagger:hilt-android-compiler:"+Versions.dagHilt
}
object Room{
    val roomRun =   "androidx.room:room-ktx:"+Versions.room
    val roomRoot =   "androidx.room"
    val ktx =   "androidx.room:room-runtime:"+Versions.room
    val compiler =   "androidx.room:room-compiler:"+Versions.room


}