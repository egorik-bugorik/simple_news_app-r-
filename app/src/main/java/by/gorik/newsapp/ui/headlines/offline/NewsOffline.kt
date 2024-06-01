package by.gorik.newsapp.ui.headlines.offline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.local.entity.Article
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.ui.base.ArticleList
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants

@Composable
fun NewsOfflineRoute(
    navController: NavHostController,
    onNewsClick: (String) -> Unit,
    vm: NewsOfflineVm
) {

    val uiStat = vm.uiState.collectAsStateWithLifecycle().value




    Scaffold(
        topBar = {
            GorikAppBar(
                title = Constants.NEWS_ONLINE,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        }
    ) { padding ->
        NewsOnlineScreen(
            padding = padding,
            state = uiStat,
            onNewsClick = onNewsClick
        ) { vm.fetchNews() }


    }
}


@Composable
fun NewsOnlineScreen(
    padding: PaddingValues,
    state: UiState<List<Article>>,
    onNewsClick: (String) -> Unit,
    onRetryClick: () -> Unit
) {


    Column(modifier = Modifier.padding(padding)) {

        when (state) {
            is UiState.Error -> ShowError(state.mesg,enabled = true){
                onRetryClick()
            }
            UiState.Loading -> ShowLoading()
            is UiState.Success -> {


                val apiArticles =  state.data.map { it.toApiArticle() }
                ArticleList(apiArticles,onNewsClick)
            }
        }

    }


}

