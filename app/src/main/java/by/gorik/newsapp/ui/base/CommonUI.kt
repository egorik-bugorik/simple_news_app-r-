@file:OptIn(ExperimentalMaterial3Api::class)

package by.gorik.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import by.gorik.newsapp.R
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.model.ApiSource
import coil.compose.AsyncImage

fun openCustomChromeTab(context: Context, url: String) {
    val bilder =CustomTabsIntent.Builder()
    val intent = bilder.build()
    intent.launchUrl(context, Uri.parse(url))

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
            if (showBackArrow) {
                IconButton(onClick = { onBackArrowClick() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Red)

                }
            }
        }
    )


}

@Composable
fun ShowError(
    mesg: String,
    enabled: Boolean = false,
    modifier:Modifier = Modifier,

    onRetryClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = R.drawable.cancel), contentDescription =null,modifier = Modifier
            .width(120.dp)
            .height(120.dp))
Text(text = mesg, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis,modifier = Modifier.padding(10.dp))

        if(enabled){
            Button(onClick = { onRetryClick() }) {

                Text(text = stringResource(id = R.string.retry ))
            }
        }
    }


}

@Composable
fun ShowLoading() {
Box(modifier = Modifier.fillMaxSize()){
val loadingString = stringResource(id = R.string.please_wait)
CircularProgressIndicator(modifier = Modifier
    .align(Alignment.Center)
    .semantics { contentDescription = loadingString })
}
}

@Composable
fun ArticleList(articles: List<ApiArticle>, onNewsClick: (String) -> Unit) {

    LazyColumn {items(articles, key = { article->article.url}){
        article ->

Article(article = article, onNewsClick = onNewsClick)


    } }
}

@Composable
fun Article(article: ApiArticle, onNewsClick: (String) -> Unit) {

Column(
    modifier = Modifier.fillMaxWidth()
        .clickable {
            if(article.url.isNotEmpty()){
                onNewsClick(article.url)
            }
        }

) {

NewsBannerImage(article)
NewsDescription(article.description?:"")
NewsTitle(article.title)
NewsSource(article.source)

}

}

@Composable
fun NewsSource(source: ApiSource) {
    if (source != null) {
        Text(
            text = source.name,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            maxLines = 1,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
        )
    }

}

@Composable
fun NewsTitle(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }

}

@Composable
fun NewsDescription(description: String) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }

}

@Composable
fun NewsBannerImage(article: ApiArticle) {
    AsyncImage(
        model = article.urlToImage,
        contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        error = painterResource(id = R.drawable.error_loading)
    )

}




