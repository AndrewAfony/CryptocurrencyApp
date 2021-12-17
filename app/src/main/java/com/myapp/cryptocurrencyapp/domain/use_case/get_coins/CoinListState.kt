package com.myapp.cryptocurrencyapp.domain.use_case.get_coins

import com.myapp.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
