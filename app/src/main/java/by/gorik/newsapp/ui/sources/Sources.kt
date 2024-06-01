package by.gorik.newsapp.ui.sources

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.model.ApiSource
import by.gorik.newsapp.navigation.Screen
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants

@Composable
fun SourcesRoute(
    navController: NavHostController,
    vm: SourcesVm = hiltViewModel()
) {

    val sourcess = vm.uiState.collectAsStateWithLifecycle()
    val uiStat = sourcess.value




    Scaffold(
        topBar = {
            GorikAppBar(
                title = Constants.SOURCES_CHOOSE,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        }
    ) { padding ->
        SourcesScreen(
            padding = padding,
            state = uiStat,
            onSourcesClick = { sources: ApiSource ->
                navController.navigate(route = Screen.NewsBySource.passData(sourceId = sources.id!!))
            })


    }
}

@Composable
fun SourcesScreen(
    padding: PaddingValues,
    onSourcesClick: (ApiSource) -> Unit,
    state: UiState<List<ApiSource>>
) {


    Column(modifier = Modifier.padding(padding)) {

        when (state) {
            is UiState.Error -> ShowError(state.mesg)
            UiState.Loading -> ShowLoading()
            is UiState.Success -> SourcesList(
                countries = state.data,
                onSourcesClick = onSourcesClick
            )
        }

    }


}

@Composable
fun SourcesList(countries: List<ApiSource> , onSourcesClick: (ApiSource) -> Unit={}) {

    LazyColumn {
        items(countries.size) { index ->
            SourcesItem(countries[index], onSourcesClick)

        }

    }

}

@Composable
fun SourcesItem(sources: ApiSource, onSourcesClick: (ApiSource) -> Unit) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSourcesClick(sources) }
                .padding(10.dp)

        ) {
            Text(
                text = sources.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)

            )

        }


    }

}


