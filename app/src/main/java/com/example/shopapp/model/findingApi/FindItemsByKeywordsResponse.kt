package com.example.shopapp.model.findingApi

data class FindItemsByKeywordsResponse(
    val paginationOutput: List<PaginationOutput>,
    val searchResult: List<SearchResult>,
)