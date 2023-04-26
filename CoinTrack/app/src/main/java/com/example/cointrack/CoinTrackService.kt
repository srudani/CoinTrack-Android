package com.example.cointrack

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoinTrackService {
    @GET("markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
    suspend fun getCoinList() : Response<CoinListResponse>

    companion object {
        private var coinTrackService: CoinTrackService? = null

        fun getInstance() : CoinTrackService {
            if (coinTrackService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coingecko.com/api/v3/coins/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                coinTrackService = retrofit.create(CoinTrackService::class.java)
            }
            return coinTrackService!!
        }
    }
}