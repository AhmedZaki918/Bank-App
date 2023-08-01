package com.ahmed.bankapp.ui.client

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.data.log.LogLogin
import com.ahmed.bankapp.databinding.ActivityAddClientBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class AddClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddClientBinding
    private val views = ArrayList<EditText>()
    private var accountNumber = ""
    private var pinCode = ""
    private var firstName = ""
    private var lastName = ""
    private var accountBalance = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityAddClientBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Create list of edittext
        binding.apply {
            views.apply {
                add(etAccountNumber)
                add(etPincode)
                add(etFirstName)
                add(etLastName)
                add(etAccountBalance)
            }

            btnAddClient.click {
                getUserInput()
            }
            header.drawScreenHeader("Add Client",this@AddClientActivity)
        }
    }


    private fun getUserInput() {
        binding.apply {
            accountNumber = etAccountNumber.text.toString().trim()
            pinCode = etPincode.text.toString().trim()
            firstName = etFirstName.text.toString().trim()
            lastName = etLastName.text.toString().trim()
            accountBalance = etAccountBalance.text.toString().trim()
        }
        addNewClient()
    }


    private fun addNewClient() {
        val client = BankClient.findClientByAccountNumber(accountNumber)
        if (!client.isClientExist()) {
            BankClient.addClient(
                accountNumber,
                pinCode.toShort(),
                accountBalance.toDouble(),
                firstName,
                lastName
            )
            toast("Client has been added")
            finish()
        } else toast("Account number already exists")
    }
}