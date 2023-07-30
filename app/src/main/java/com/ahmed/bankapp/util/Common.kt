package com.ahmed.bankapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.databinding.HeaderBinding
import java.text.SimpleDateFormat
import java.util.Date


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun View.click(block: () -> Unit) {
    this.setOnClickListener {
        block()
    }
}


fun <T : Any?> Context.openActivity(activity: Class<T>) {
    startActivity(Intent(this, activity))
}


fun HeaderBinding.drawScreenHeader(
    screenTitle: String
) {
    this.tvLoggedInUser.text = loginData.firstName
    this.tvScreenName.text = screenTitle
    this.tvDateTime.text = SimpleDateFormat("dd/MM/yyyy").format(Date())
}


fun encryptText(text: String, encryptKey: Int = 2): String {
    var encryptedPassword = ""
    for (x in text.indices) {
        encryptedPassword += Char(((text[x] + encryptKey).code))
    }
    return encryptedPassword
}


fun decryptText(text: String, encryptKey: Int = 2): String {
    var decryptedPassword = ""
    for (x in text.indices) {
        decryptedPassword += Char(((text[x] - encryptKey).code))
    }
    return decryptedPassword
}


fun EditText.changeToDefaultColor(context: Context) {
    this.setTextColor(
        ContextCompat.getColor(context, R.color.zeroWhite)
    )
}

fun Activity.installSplash(){
    installSplashScreen()
}

