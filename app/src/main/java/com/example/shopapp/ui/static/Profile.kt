package com.example.shopapp.ui.static

import android.net.Uri
import com.example.shopapp.ui.model.AppItem

class Profile {

    companion object {

        var image: String = ""

        var firstName = ""
        var secondName = ""
        var lastName = ""

        var email = ""

        var currency = "USD"
        var theme = "light"

        var cartList = ArrayList<AppItem>()

        fun getFirebaseProfile() = FirebaseProfile(
            Profile.image,
            Profile.firstName,
            Profile.secondName,
            Profile.lastName,
            Profile.email,
            Profile.currency,
            Profile.theme,
            Profile.cartList
        )

        fun setProfile(fbProfile: FirebaseProfile){
            Profile.image = fbProfile.profileImage
            Profile.firstName = fbProfile.profileFirstName
            Profile.secondName = fbProfile.profileSecondName
            Profile.lastName = fbProfile.profileLastName
            Profile.email = fbProfile.profileEmail
            Profile.currency = fbProfile.profileCurrency
            Profile.theme = fbProfile.profileTheme
            Profile.cartList = fbProfile.profileCartList
        }

    }

}
