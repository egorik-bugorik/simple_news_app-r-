package by.gorik.newsapp.ui.news_by_sources

import androidx.lifecycle.viewModelScope
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.model.Language
import by.gorik.newsapp.data.repository.CountryRepo
import by.gorik.newsapp.data.repository.NewArticlesRepo
import by.gorik.newsapp.data.repository.NewsArticlesBYRepo
import by.gorik.newsapp.ui.base.BaseVm
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.DispatcherProvider
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsByVm @Inject constructor(
    val newsRepo: NewsArticlesBYRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState


    fun fetchBySource(source: String) {
        viewModelScope.launch {

            if (!networkHelper.isNetworkActive()) {
                val noInternetMsg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(noInternetMsg)
                logger.d(TAG, "No interne")
                return@launch

            } else {

                val sourceCorrect = source?.replace("{", "")
                    ?.replace("}", "")
                logger.d(TAG, "source  query ::: $sourceCorrect")
                sourceCorrect?.let { query ->
                    _uiState.value = UiState.Loading
                    newsRepo.getArticlesbySources(query)
                        .catch { e ->
                            _uiState.value = UiState.Error(e.toString())
                            logger.d(TAG, "error while fetching news ::: " + e.message.toString())

                        }.collect {
                            _uiState.value = UiState.Success(it)
                            logger.d(TAG, "by source articles ::: $it")
                        }
                }

            }
        }

    }

    fun fetchByCountry(country: String) {
        viewModelScope.launch {

            if (!networkHelper.isNetworkActive()) {
                val noInternetMsg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(noInternetMsg)
                logger.d(TAG, "No internet")
                return@launch

            } else {
                _uiState.value = UiState.Loading
                newsRepo.getArticlesbyCountry(country)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, "error while fetching news ::: " + e.message.toString())

                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, "by country articles ::: $it")
                    }
            }

        }
    }

    fun fetchByLanguage(language: String) {
        viewModelScope.launch {

            if (!networkHelper.isNetworkActive()) {
                val noInternetMsg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(noInternetMsg)
                logger.d(TAG, "No internet")
                return@launch

            } else {
                _uiState.value = UiState.Loading
                newsRepo.getArticlesbyLanguage(language)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, "error while fetching news ::: " + e.message.toString())

                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, "by language articles ::: $it")
                    }
            }

        }
    }


    companion object {
        const val TAG = "NewsOnlineListVm"
    }
}