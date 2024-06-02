package by.gorik.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.gorik.newsapp.ui.HomeScreen
import by.gorik.newsapp.ui.base.openCustomChromeTab
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
                navController = navController,

                )

        }
        composable(route = Screen.NewsOnline.route) {

            NewsOnlineRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )

        }
        composable(route = Screen.NewsOffline.route) {

            NewsOfflineRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )

        }
        composable(route = Screen.NewsPagination.route) {

            NewsPaginationRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )

        }
        composable(route = Screen.NewsSearch.route) {

            NewsSearchRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )

        }
        composable(
            route = Screen.NewsBySource.route,
            arguments = listOf(
                navArgument(name = "sourceId"){
                           type = NavType.StringType
defaultValue=""
                },
                navArgument(name = "languageId"){
                           type = NavType.StringType
defaultValue=""
                },
                navArgument(name = "countryCode"){
                    type = NavType.StringType
defaultValue=""
                },
            )
        ) {

            val sourceId =it.arguments?.getString("sourceId").toString()
            val languageId =it.arguments?.getString("languageId").toString()
            val countryCode =it.arguments?.getString("countryCode").toString()


            NewsBySourceRoute(
                sourceId = sourceId,
                languageId = languageId,
                countryCode = countryCode,

                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )

        }
        composable(route = Screen.Sources.route) {

            SourcesRoute(
                navController = navController
            )

        }
        composable(route = Screen.Language.route) {

            LanguageRoute(
                navController = navController
            )

        }
        composable(route = Screen.Country.route) {

            CountryRoute(
                navController = navController
            )

        }


    }


}

