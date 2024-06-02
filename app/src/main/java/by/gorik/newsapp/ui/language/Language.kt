package by.gorik.newsapp.ui.language

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
import by.gorik.newsapp.data.model.Language
import by.gorik.newsapp.navigation.Screen
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants

@Composable
fun LanguageRoute(
    navController: NavHostController,
    vm: LanguageVm = hiltViewModel()
) {

    val languages = vm.uiState.collectAsStateWithLifecycle()
    val uiStat = languages.value




    Scaffold(
        topBar = {
            GorikAppBar(
                title = Constants.LANGUAGE_CHOOSE,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        }
    ) { padding ->
        LanguageScreen(
            padding = padding,
            state = uiStat,
            navController = navController,
            onLanguageClick = { language: Language ->
                navController.navigate(route = Screen.NewsBySource.passData(languageId = language.id))
            })


    }
}

@Composable
fun LanguageScreen(
    padding: PaddingValues,
    navController: NavHostController,
    onLanguageClick: (Language) -> Unit,
    state: UiState<List<Language>>
) {


    Column(modifier = Modifier.padding(padding)) {

        when (state) {
            is UiState.Error -> ShowError(state.mesg)

            UiState.Loading -> ShowLoading()
            is UiState.Success -> LanguageList(
                countries = state.data,
                onLanguageClick = onLanguageClick
            )
        }

    }


}

@Composable
fun LanguageList(countries: List<Language> , onLanguageClick: (Language) -> Unit={}) {

    LazyColumn {
        items(countries.size) { index ->
            LanguageItem(countries[index], onLanguageClick)

        }

    }

}

@Composable
fun LanguageItem(language: Language, onLanguageClick: (Language) -> Unit) {


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
                .clickable { onLanguageClick(language) }
                .padding(10.dp)

        ) {
            Text(
                text = language.name,
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


