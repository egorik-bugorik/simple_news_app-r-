package by.gorik.newsapp.ui.news_by_sources

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.ui.base.ArticleList
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState

@Composable
fun NewsBySourceRoute(
    navController: NavHostController,
    vm: NewsByVm = hiltViewModel(),
    onNewsClick: (String) -> Unit,
    sourceId: String? = null,
    languageId: String? = null,
    countryCode: String? = null,
) {


    LaunchedEffect(key1 = Unit, block = {
            if(!sourceId.isNullOrEmpty()) {
                vm.fetchBySource(sourceId)

            }else if(!languageId.isNullOrEmpty()) {
                vm.fetchByLanguage(languageId)
            }else if(!countryCode.isNullOrEmpty()) {
                vm.fetchByCountry(countryCode)
            }



    })


    val state = vm.uiState.collectAsStateWithLifecycle().value

    Column {
        NewsByScreen(onNewsClick, state)
    }


}

@Composable
fun NewsByScreen(onNewsClick: (String) -> Unit, state: UiState<List<ApiArticle>>) {


    when (state) {
        is UiState.Error -> {
            val errMsg = state.mesg
                ShowError(mesg =errMsg)
        }

        UiState.Loading -> {
            ShowLoading()
        }
        is UiState.Success -> {
            ArticleList(articles = state.data, onNewsClick =onNewsClick )
        }
    }

}
