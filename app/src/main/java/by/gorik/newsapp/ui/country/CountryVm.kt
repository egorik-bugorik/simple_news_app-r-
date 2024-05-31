package by.gorik.newsapp.ui.country

import androidx.lifecycle.viewModelScope
import by.gorik.newsapp.data.model.Country
import by.gorik.newsapp.data.repository.CountryRepo
import by.gorik.newsapp.ui.base.BaseVm
import by.gorik.newsapp.ui.base.UiState
import by.gorik.newsapp.utils.DispatcherProvider
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.network.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CountryVm(
    val countryRepo: CountryRepo,
    val logger: Logger,
    val networkHelper: NetworkHelper,
    val dispatcherProvider: DispatcherProvider,
    val resourceProvider: ResourceProvider
) : BaseVm() {

    val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>> = _uiState


    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {


            if (!networkHelper.isNetworkActive()) {
                val msg = resourceProvider.getStringInternetNotAvailable()
                _uiState.value = UiState.Error(msg)
                return@launch
            } else {


                _uiState.value = UiState.Loading
                countryRepo.getCountry()
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, e.message.toString())

                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, "country recieved :::: ${it.toString()}")
                    }


            }
        }
    }

    companion object {
        const val TAG = "CountryListVm"
    }
}