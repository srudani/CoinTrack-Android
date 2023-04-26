package com.example.cointrack

class CoinListRepository constructor(private val retrofitService: CoinTrackService) {
    suspend fun getCoinList() = retrofitService.getCoinList()
}
