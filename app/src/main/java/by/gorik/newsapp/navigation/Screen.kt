package by.gorik.newsapp.navigation

sealed class Screen(val route: String,val name:String = "") {

    object NewsOnline : Screen(route = "newsonline",name = "NEWS ONLINE")
    object NewsOffline : Screen(route = "newsoffline",name = "NEWS OFFLINE")
    object NewsSearch : Screen(route = "newssearch",name = "NEWS SEARCH")
    object NewsBySource :
        Screen(route = "newsbysource?sourceId={sourceId}&countryCode={countryCode}&languageId={languageId}",name = "NEWS BY SOURCES") {
        fun passData(
            sourceId: String = "",
            countryCode: String = "",
            languageId: String = "",
        ): String {
            return "newsbysource?sourceId=$sourceId&countryCode=$countryCode&languageId=$languageId"
        }
    }

    object Sources : Screen(route = "sources",name = "SOURCES")
    object Language : Screen(route = "language",name = "LANGUAGES")
    object Country : Screen(route = "country",name = "COUNTRYS")
    object HomeScreen : Screen(route = "homescreen",name = "HOMESCREEN")
    object NewsPagination : Screen(route = "newspagination",name = "NEWS PAGINATION")


    companion object{

        val all_screen = listOf(
            NewsOnline,
            NewsOffline,
            NewsSearch,
            Sources,
            Language,
            Country,
            NewsPagination
        )
    }
}
