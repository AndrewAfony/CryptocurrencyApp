package com.myapp.cryptocurrencyapp.domain.use_case.get_coins

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.cryptocurrencyapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    var state = mutableStateOf(CoinListState())
    private set

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    state.value = CoinListState(error = result.message ?: "An unexpected error")
                }
                is Resource.Loading -> {
                    state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}