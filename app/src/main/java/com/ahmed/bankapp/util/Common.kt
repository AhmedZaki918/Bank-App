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
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.HeaderBinding
import java.text.SimpleDateFormat
import java.util.Date


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
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
    screenTitle: String,
    activity: Activity
) {
    this.tvLoggedInUser.text = loginData.firstName
    this.tvScreenName.text = screenTitle
    this.tvDateTime.text = SimpleDateFormat("dd/MM/yyyy").format(Date())
    this.ivGoBack.click {
        activity.finish()
    }
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

fun Activity.installSplash() {
    installSplashScreen()
}

fun addFakeDataToTest() {
    BankClient.apply {
        addClient("105", 1234, 5000.0, "Ahmed", "Zaki")
        addClient("106", 1233, 3000.0, "Sara", "Zaki")
        addClient("107", 1252, 9000.0, "Adel", "Sabry")
        addClient("108", 4781, 3500.0, "Ehab", "Mohamed")
        addClient("109", 5515, 7500.0, "Waled", "Adham")
        addClient("110", 9844, 1500.0, "Ahmed", "Ali")
    }

    BankUser.apply {
        addUser("admin", "1234", 255, "Admin", "")
        addUser("ahmed", "1234", 3, "Ahmed", "Zaki")
        addUser("mohamed22adel", "1234", 19, "Mohamed", "Adel")
        addUser("ramy51ahmed", "1234", 3, "Ramy", "Ahmed")
    }

    Currency.getCurrencies().apply {
        add(Currency("United States of America", "USD", "US Dollar", 1.000000f))
        add(Currency("Saudi Arabian", "SAR", "Saudi Riyal", 3.75f))
        add(Currency("England", "GBP", "British Pound", 0.77f))
        add(Currency("Egypt", "EGP", "Egyptian Pound", 30.89f))
        add(Currency("Kuwaiti", "KWD", "Kuwaiti Dinar", 0.308f))
        add(Currency("France", "EUR", "Euro", 0.90f))
        add(Currency("United Arab Emirates", "AED", "Emirati Dirhams", 3.67f))
        add(Currency("Chinese", "CNY", "Chinese Yuan Renminbi", 7.14f))
    }
}

