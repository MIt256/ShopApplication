package com.example.shopapp.ui.static

import android.net.Uri
import com.example.shopapp.ui.model.AppItem

class Profile {

    companion object{

        var image: Uri? = null

        var firstName = ""
        var secondName = ""
        var lastName = ""

        var email = ""

        var currency = "USD"
        var theme = "light"

        var cartList = ArrayList<AppItem>()

    }

}