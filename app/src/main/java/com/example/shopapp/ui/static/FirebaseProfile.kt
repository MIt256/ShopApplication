package com.example.shopapp.ui.static

import com.example.shopapp.ui.model.AppItem

data class FirebaseProfile(var profileImage: String="",
                      var profileFirstName: String="",
                      var profileSecondName: String="",
                      var profileLastName: String="",
                      var profileEmail: String="",
                      var profileCurrency: String="",
                      var profileTheme: String="",
                      var profileCartList: ArrayList<AppItem> = ArrayList<AppItem>()
)