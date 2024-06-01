package by.gorik.newsapp.ui.search

import android.icu.text.IDNA
import androidx.lifecycle.viewModelScope
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.repository.SearchRepo
import by.gorik.newsapp.ui.base.BaseVm
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.Constants
import by.gorik.newsapp.utils.DispatcherProvider
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.network.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchVm(
    val newsRepo: SearchRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    val query = MutableStateFlow("")

    init {
        setupSearchStateFlow()
    }

    private fun setupSearchStateFlow() {
        viewModelScope.launch {

            if (!networkHelper.isNetworkActive()) {
                val msg = resourceProvider.getStringInternetNotAvailable()
                logger.d(TAG,"search news :::"+ msg)
                return@launch


            }else{
                query.debounce(1000)
                    .filter { query->
                        if(query.isNotEmpty()&&query.length>= Constants.MIN_SEARCH_LEN){
                            return@filter true

                        }else{
                            _uiState.value = UiState.Success(emptyList())
                            return@filter false

                        }
                    }
                    .distinctUntilChanged()
                    .flatMapLatest {
_uiState.value = UiState.Loading
                        return@flatMapLatest newsRepo.getNewsSearch(it)
                            .catch {
                            e->
                            _uiState.value = UiState.Error(e.toString())
                            logger.d(TAG,"Error in search vm :::"+e.message.toString())
                        }
                    }
                    .flowOn(dispatcherProvider.io)
                    .collect{
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG,"recieved articles ::: $it")
                    }
            }
        }

    }
    fun searchNews(q: String) {
        query.value = q
    }

    companion object {
        const val TAG = "NewsOnlineListVm"
    }
}