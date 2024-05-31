package by.gorik.newsapp.navigation

sealed class Screen(val route :String) {

object NewsOnline:Screen(route = "newsonline")
object NewsOffline:Screen(route = "newsoffline")
object NewsSearch:Screen(route = "newssearch")
object NewsBySource:Screen(route = "newsbysource?sourceId={sourceId}&countryCode={countryCode}&languageId={languageId}"){
    fun passData(
        sourceId:String= "",
        countryCode:String= "",
        languageId:String= "",
    ):String{
        return "newsbysource?sourceId=$sourceId&countryCode=$countryCode&languageId=$languageId"
    }
}
object Sources:Screen(route = "sources")
object Language:Screen(route = "language")
    object Country:Screen(route = "country")
    object HomeScreen:Screen(route = "homescreen")
    object NewsPagination:Screen(route = "newspagination")




}