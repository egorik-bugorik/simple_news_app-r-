package by.gorik.newsapp.ui.country

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import by.gorik.newsapp.data.model.Country
import by.gorik.newsapp.navigation.Screen
import by.gorik.newsapp.ui.base.GorikAppBar
import by.gorik.newsapp.ui.base.ShowError
import by.gorik.newsapp.ui.base.ShowLoading
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants
import kotlin.random.Random

@Composable
fun CountryRoute(
    navController: NavHostController,
    vm: CountryVm = hiltViewModel()
) {

    val countries = vm.uiState.collectAsStateWithLifecycle()
    val uiStat = countries.value




    Scaffold(
        topBar = {
            GorikAppBar(
                title = Constants.COUNTRY_CHOOSE,
                showBackArrow = true,
                onBackArrowClick = { navController.popBackStack() })
        }
    ) { padding ->
        CountryScreen(
            padding = padding,
            state = uiStat,
            navController = navController,
            onCountryClick = { country: Country ->
                navController.navigate(route = Screen.NewsBySource.passData(countryCode = country.code))
            })


    }
}

@Composable
fun CountryScreen(
    padding: PaddingValues,
    navController: NavHostController,
    onCountryClick: (Country) -> Unit,
    state: UiState<List<Country>>
) {


    Column(modifier = Modifier.padding(padding)) {

        when (state) {
            is UiState.Error -> ShowError(state.mesg)

            UiState.Loading -> ShowLoading()
            is UiState.Success -> CountryList(
                countries = state.data,
                onCountryClick = onCountryClick
            )
        }

    }


}

val countryName = "Belarus germany russia belguim spain italy".split(" ")
val countryCode = countryName.map { it.substring(0, 3) }

fun countries(num:Int):List<Country>{

    val li = mutableListOf<Country>()
    repeat(num){
        li.add(RandomCntr())
    }
    return li

}

fun RandomCntr(): Country {

    return Country(
        name = countryName[Random.nextInt(0, countryName.size)],
        code = countryCode[Random.nextInt(0, countryCode.size)],
    )
}

@Composable
@Preview(showBackground = true)
fun CountryList(countries: List<Country> = countries(10), onCountryClick: (Country) -> Unit={}) {

    LazyColumn {
        items(countries.size) { index ->
            CountryItem(countries[index], onCountryClick)

        }

    }

}

@Composable
fun CountryItem(country: Country, onCountryClick: (Country) -> Unit) {


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
                .clickable { onCountryClick(country) }
                .padding(10.dp)

        ) {
            Text(
                text = country.name,
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


