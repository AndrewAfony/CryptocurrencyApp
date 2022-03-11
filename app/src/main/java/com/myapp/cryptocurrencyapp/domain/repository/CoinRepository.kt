package com.myapp.cryptocurrencyapp.domain.repository

import com.myapp.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.myapp.cryptocurrencyapp.data.remote.dto.CoinDto

// The reason why we have this interface is that it will be very useful during testing
interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}