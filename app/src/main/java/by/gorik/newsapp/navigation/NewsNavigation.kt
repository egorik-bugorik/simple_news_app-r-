package by.gorik.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.gorik.newsapp.ui.HomeScreen
import by.gorik.newsapp.ui.base.openCustoChromeTab
import by.gorik.newsapp.ui.country.CountryRoute
import by.gorik.newsapp.ui.headlines.offline.NewsOfflineRoute
import by.gorik.newsapp.ui.headlines.online.NewsOnlineRoute
import by.gorik.newsapp.ui.headlines.paging.NewsPaginationRoute
import by.gorik.newsapp.ui.language.LanguageRoute
import by.gorik.newsapp.ui.news_by_sources.NewsBySourceRoute
import by.gorik.newsapp.ui.search.NewsSearchRoute
import by.gorik.newsapp.ui.sources.SourcesRoute


@Composable
fun SetupNavGraph(navController: NavHostController) {


    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route)
    {

        composable(route = Screen.HomeScreen.route) {

            HomeScreen(
                navController=navController,

            )

                 }
        composable(route = Screen.NewsOnline.route) {

            NewsOnlineRoute(
                navController=navController,
                onNewsClick={
                    openCustoChromeTab(context,it)
                }
            )

                 }
        composable(route = Screen.NewsOffline.route) {

            NewsOfflineRoute(
                navController=navController,
                onNewsClick={
                    openCustoChromeTab(context,it)
                }
            )

                 }
        composable(route = Screen.NewsPagination.route) {

            NewsPaginationRoute(
                navController=navController,
                onNewsClick={
                    openCustoChromeTab(context,it)
                }
            )

                 }
        composable(route = Screen.NewsSearch.route) {

            NewsSearchRoute(
                navController=navController,
                onNewsClick={
                    openCustoChromeTab(context,it)
                }
            )

                 }
        composable(route = Screen.NewsBySource.route) {

            NewsBySourceRoute(
                navController=navController,
                onNewsClick={
                    openCustoChromeTab(context,it)
                }
            )

                 }
        composable(route = Screen.Sources.route) {

            SourcesRoute(
                navController=navController
            )

                 }
        composable(route = Screen.Language.route) {

            LanguageRoute(
                navController=navController
            )

                 }
        composable(route = Screen.Country.route) {

            CountryRoute(
                navController=navController
            )

        }


    }


}

