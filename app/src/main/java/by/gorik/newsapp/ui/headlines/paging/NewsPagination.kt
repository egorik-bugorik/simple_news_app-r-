package by.gorik.newsapp.ui.headlines.paging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.ui.base.Article
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.utils.Constants

@Composable
fun NewsPaginationRoute(
    navController: NavHostController,
    onNewsClick: (String) -> Unit,
    vm: NewsPagingVm = hiltViewModel()
) {

    val articlesPag = vm.uiState.collectAsLazyPagingItems()




    Scaffold(
        topBar = {
            GorikAppBar(
                title = Constants.NEWS_PAGING,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        }
    ) { padding ->
        NewsOnlineScreen(
            padding = padding,
            articlesPag = articlesPag,
            onNewsClick = onNewsClick
        )


    }
}


@Composable
fun NewsOnlineScreen(
    padding: PaddingValues,
    onNewsClick: (String) -> Unit,
    articlesPag: LazyPagingItems<ApiArticle>
) {


    Column(modifier = Modifier.padding(padding)) {
        ArticlesListPaginar(articlesPag, onNewsClick)
        articlesPag.apply {

            when {
                articlesPag.loadState.refresh is LoadState.Error -> {
                    val error = articlesPag.loadState.refresh as LoadState.Error
                    ShowError(mesg = error.error.localizedMessage)
                }

                articlesPag.loadState.refresh is LoadState.Loading -> {
                    ShowLoading()
                }

                articlesPag.loadState.append is LoadState.Error -> {
                    val error = articlesPag.loadState.append as LoadState.Error
                    ShowError(mesg = error.error.localizedMessage)
                }

                articlesPag.loadState.append is LoadState.Loading -> {
                    ShowLoading()
                }
            }
        }

    }


}

@Composable
fun ArticlesListPaginar(articlesPag: LazyPagingItems<ApiArticle>, onNewsClick: (String) -> Unit) {
    LazyColumn {
        items(count = articlesPag.itemCount, key = { index -> articlesPag[index]!!.url }) { index ->
            Article(articlesPag[index]!!, onNewsClick)
        }
    }
}

