package by.gorik.newsapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants

@Composable
fun NewsSearchRoute(
    navController: NavHostController,
    searchVm: SearchVm = hiltViewModel(),
    onNewsClick: (String) -> Unit
) {
    val uiState = searchVm.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            GorikAppBar(title = Constants.SEARCH_NEWS,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        },
        content = { padding ->

            Column(Modifier.padding(padding)) {

                SearchScreen(searchVm,onNewsClick,uiState)

            }



        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vm: SearchVm,
    onNewsClick: (String) -> Unit,
    uiState: UiState<List<ApiArticle>>
) {
var text by rememberSaveable {
    mutableStateOf("")
}
var isActive by rememberSaveable {
    mutableStateOf(true)
}
    
    
    
    SearchBar(query = text, onQueryChange = {
                                            newQuery->
                                            text = newQuery
        vm.searchNews(newQuery)
    }, onSearch ={

                 isActive = false

    } , active = isActive, onActiveChange = {
        isActive = it
    }, placeholder = {
        Text(text = "SERACH for news ...")
    }, leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription =null,tint = MaterialTheme.colorScheme.secondary )
    }, content = {


TODO()


    })
}
