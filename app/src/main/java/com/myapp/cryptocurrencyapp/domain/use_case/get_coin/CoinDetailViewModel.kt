package com.myapp.cryptocurrencyapp.domain.use_case.get_coin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.cryptocurrencyapp.common.Constants
import com.myapp.cryptocurrencyapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state = mutableStateOf(CoinDetailState())
    private set

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId = coinId)
        }
    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId = coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    state.value = CoinDetailState(error = result.message ?: "An unexpected error")
                }
                is Resource.Loading -> {
                    state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}