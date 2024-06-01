package by.gorik.newsapp.ui.headlines.offline

import androidx.lifecycle.viewModelScope
import by.gorik.newsapp.data.local.entity.Article
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.repository.CountryRepo
import by.gorik.newsapp.data.repository.NewArticlesRepo
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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NewsOfflineVm(
    val newsRepo: NewArticlesRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState


    init {
        if (networkHelper.isNetworkActive()){
            fetchNews()
        }else{
            fetchNewsFromDb()
        }
    }

     fun fetchNewsFromDb() {
         viewModelScope.launch {





             _uiState.value = UiState.Loading
             newsRepo.getArticlesFromDb()
                 .flowOn(dispatcherProvider.io)
                 .catch { e ->
                     _uiState.value = UiState.Error(e.toString())
                     logger.d(TAG, e.message.toString())

                 }.collect {
                     _uiState.value = UiState.Success(it)
                     logger.d(TAG, "articles(off) recieved :::: ${it.toString()}")



                 }
         }
    }

    fun fetchNews() {
        viewModelScope.launch {





                _uiState.value = UiState.Loading
                newsRepo.getArticlesOffline(Constants.DEFAULT_COUNTRY)
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

    companion object {
        const val TAG = "NewsOfflineListVm"
    }
}