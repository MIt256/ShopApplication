package com.example.shopapp.model.findingApi

data class SellingStatus(
    val currentPrice: List<CurrentPrice>,
    val sellingState: List<String>,
)
//        "sellingStatus":[{
//            "currentPrice":[{ "@currencyId":"USD", "__value__":"4.49" }],
//            "convertedCurrentPrice":[{ "@currencyId":"USD", "__value__":"4.49" }],
//            "sellingState":["Active"],
//            "timeLeft":["P2DT16H15M7S"]
//        }]