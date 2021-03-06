package com.example.shopapp.const

class Constants {
    companion object {

        const val URL_QUERY = "&keywords"
        const val URL_ENDPOINT_GET = "v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=MaksZank-ShopAPp-PRD-9dc58052e-86b9d239&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD"
        const val URL_BASE = "https://svcs.ebay.com/services/search/FindingService/"
//svcs.ebay.com/services/search/FindingService/
        const val URL_BASE_CURRENCY = "https://www.nbrb.by/api/exrates/"
        const val CURRENCY_QUERY = "rates?periodicity=0"

    }
}