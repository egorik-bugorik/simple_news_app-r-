package by.gorik.newsapp.ui.headlines.online

import androidx.lifecycle.viewModelScope
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

class NewsOnlineVm(
    val newsRepo: NewArticlesRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState


    init {
        fetchNewsOnline()
    }

    fun fetchNewsOnline() {
        viewModelScope.launch {


            if (!networkHelper.isNetworkActive()) {
                val msg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(msg)
                return@launch
            } else {


                _uiState.value = UiState.Loading
                newsRepo.getArticlesOnline()
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, e.message.toString())

                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, "articles recieved :::: ${it.toString()}")
                    }


            }
        }
    }

    companion object {
        const val TAG = "NewsOnlineListVm"
    }
}