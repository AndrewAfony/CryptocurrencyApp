package com.myapp.cryptocurrencyapp.domain.use_case.get_coin

import com.myapp.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
