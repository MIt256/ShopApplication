package com.example.shopapp.model.findingApi

data class Item(
    val galleryURL: List<String>,
    val globalId: List<String>,
    val itemId: List<String>,
    val location: List<String>,
    val primaryCategory: List<PrimaryCategory>,
    val sellingStatus: List<SellingStatus>,
    val subtitle: List<String>,
    val title: List<String>,
    val viewItemURL: List<String>
)
//        "itemId":["302485416770"],
//        "title":["Harry Potter and the Order of the Phoenix (Book 5)"],
//        "globalId":["EBAY-US"],
//        "subtitle":["by J. K. Rowling | Hardcover"],
//        "primaryCategory":[{ "categoryId":["261186"], "categoryName":["Books"] }],
//        "galleryURL":["https:\/\/i.ebayimg.com\/thumbs\/images\/g\/a4MAAOSwJ5NZ30W1\/s-l140.jpg"],
//        "viewItemURL":["https:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Book-5-\/302485416770"],
//        "productId":[{ "@type":"ReferenceID", "__value__":"16038295329" }],
//        "autoPay":["false"],
//        "postalCode":["436**"],
//        "location":["Toledo,OH,USA"],
//        "country":["US"],
//        "shippingInfo":[{
//            "shippingServiceCost":[{ "@currencyId":"USD", "__value__":"0.0" }],
//            "shippingType":["Free"], "shipToLocations":["Worldwide"], "expeditedShipping":["false"],
//            "oneDayShippingAvailable":["false"], "handlingTime":["2"]
//        }],
//        "sellingStatus":[{
//            "currentPrice":[{ "@currencyId":"USD", "__value__":"4.49" }],
//            "convertedCurrentPrice":[{ "@currencyId":"USD", "__value__":"4.49" }],
//            "sellingState":["Active"], "timeLeft":["P2DT16H15M7S"]
//        }],
//        "listingInfo":[{
//            "bestOfferEnabled":["false"],
//            "buyItNowAvailable":["false"],
//            "startTime":["2017-10-12T10:36:32.000Z"],
//            "endTime":["2022-04-12T10:36:32.000Z"],
//            "listingType":["FixedPrice"],
//            "gift":["false"], "watchCount":["153"]
//        }],
//        "returnsAccepted":["true"],
//        "condition":[{
//            "conditionId":["5000"],
//            "conditionDisplayName":["Good"]
//        }],
//        "isMultiVariationListing":["false"],
//        "topRatedListing":["false"]