package com.example.shopapp.const

class Constants {
    companion object {
//        const val URL_ENDPOINT_GET = "/products"
//        const val URL_BASE = "https://fakestoreapi.com"
        private const val count = 10
        const val URL_COUNT = "&paginationInput.entriesPerPage=$count"
        const val URL_QUERY = "&keywords"
        const val URL_ENDPOINT_GET = "v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=MaksZank-ShopAPp-PRD-9dc58052e-86b9d239&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD"
        const val URL_BASE = " https://svcs.ebay.com/services/search/FindingService/"

    }
}