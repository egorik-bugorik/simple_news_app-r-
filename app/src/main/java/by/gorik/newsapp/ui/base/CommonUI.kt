@file:OptIn(ExperimentalMaterial3Api::class)

package by.gorik.newsapp.ui.base

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import by.gorik.newsapp.data.model.ApiArticle

fun openCustomChromeTab(context: Context, it: String) {
    TODO()

}


@Composable
fun GorikAppBar(
    title: String,
    showBackArrow: Boolean,
    onBackArrowClick: () -> Unit = {}
) {


    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (showBackArrow){
                IconButton(onClick = { onBackArrowClick() }) {
                    Icon(Icons.Default.ArrowBack,contentDescription = null, tint = Color.Red )
                    
                }
            }
        }
    )


}

@Composable
fun ShowError(mesg: String, enabled: Boolean = false, function: () -> Unit = {}) {

}

@Composable
fun ShowLoading() {
    TODO("Not yet implemented")
}

@Composable
fun ArticleList(data: List<ApiArticle>, onNewsClick: (String) -> Unit) {

}

@Composable
fun Article(apiArticle: ApiArticle, onNewsClick: (String) -> Unit) {

}