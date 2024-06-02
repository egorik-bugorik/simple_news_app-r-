package by.gorik.newsapp.ui.sources

import androidx.lifecycle.viewModelScope
import by.gorik.newsapp.data.model.ApiSource
import by.gorik.newsapp.data.repository.NewsSourcesRepo
import by.gorik.newsapp.ui.base.BaseVm
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesVm @Inject constructor(
    val sourceRepo: NewsSourcesRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    val _uiState = MutableStateFlow<UiState<List<ApiSource>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiSource>>> = _uiState


    init {
        fetchSources()
    }

    fun fetchSources() {
        viewModelScope.launch {


            if (!networkHelper.isNetworkActive()) {
                val msg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(msg)
                return@launch
            } else {


                _uiState.value = UiState.Loading
                sourceRepo.getNewsSources()
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, e.message.toString())

                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, "source recieved :::: ${it.toString()}")
                    }


            }
        }
    }

    companion object {
        const val TAG = "SourceListVm"
    }
}