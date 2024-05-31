package by.gorik.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsArticlesPagingSource(val net: NetworkService) : PagingSource<Int, ApiArticle>() {

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {

        return state.anchorPosition?.let {
            p->
            state.closestPageToPosition(p)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(p)?.nextKey?.minus(1)
        }



    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {

            val page = params.key ?: Constants.INITIAL_PAGE

            val response = net.getTopPaging(
                country = Constants.DEFAULT_COUNTRY,
                page = page,
                pageSize = Constants.PAGE_SIZE
            )

            LoadResult.Page(
                response.articles,
                prevKey = if (page == Constants.INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1)

            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}