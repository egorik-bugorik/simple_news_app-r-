package by.gorik.newsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.gorik.newsapp.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn {
            items(Screen.all_screen.size) { index ->

                val screen = Screen.all_screen[index]

                CustomButton(
                    text = screen.name,
                    onClick = { navController.navigate(screen.route) })


            }
        }

    }


}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 20.sp,
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(10.dp)
    ) {

        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(horizontal = 30.dp)

        ) {
            Text(text = text, fontSize = fontSize)

        }


    }


}
