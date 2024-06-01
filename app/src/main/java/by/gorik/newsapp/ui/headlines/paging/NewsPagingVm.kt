package by.gorik.newsapp.ui.headlines.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.repository.CountryRepo
import by.gorik.newsapp.data.repository.NewArticlesRepo
import by.gorik.newsapp.ui.base.BaseVm
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.DispatcherProvider
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.network.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NewsPagingVm(
    val newsRepo: NewArticlesRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    private val _uiState = MutableStateFlow<PagingData<ApiArticle>>(PagingData.empty())
    val uiState: StateFlow<PagingData<ApiArticle>> = _uiState


    init {
        fetchNewsPaging()
    }

    fun fetchNewsPaging() {
        viewModelScope.launch {


            if (!networkHelper.isNetworkActive()) {
                val msg = resourceProvider.getStringInternetNotAvailable()
                return@launch
            } else {


                newsRepo.getPagingArticle()
                    .flowOn(dispatcherProvider.io)
                    .collect {
                        _uiState.value = it
                        logger.d(TAG, "articles recieved :::: ${it.toString()}")
                    }


            }
        }
    }

    companion object {
        const val TAG = "PagingNewsVm"
    }
}