package by.gorik.newsapp.ui.headlines.online

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.ui.base.ArticleList
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants

@Composable
fun NewsOnlineRoute(
    navController: NavHostController,
    onNewsClick: (String) -> Unit,
    vm: NewsOnlineVm = hiltViewModel()
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
        ) { vm.fetchNewsOnline() }


    }
}


@Composable
fun NewsOnlineScreen(
    padding: PaddingValues,
    state: UiState<List<ApiArticle>>,
    onNewsClick: (String) -> Unit,
    onRetryClick: () -> Unit
) {


    Column(modifier = Modifier.padding(padding)) {

        when (state) {
            is UiState.Error -> ShowError(state.mesg,enabled = true){
                onRetryClick()
            }
            UiState.Loading -> ShowLoading()
            is UiState.Success -> ArticleList(state.data,onNewsClick)
        }

    }


}

