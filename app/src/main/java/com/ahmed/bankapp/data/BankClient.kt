package com.ahmed.bankapp.data

import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.data.log.LogTransfer
import java.text.SimpleDateFormat
import java.util.Date


data class BankClient(
    var accountNumber: String,
    var pinCode: Short,
    var accountBalance: Double,
    override var firstName: String,
    override var lastName: String
) : Person(firstName, lastName) {


    private fun saveTransferLogs(
        amount: Double,
        toClient: BankClient
    ) {
        val dateAndTime = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(Date())
        val sourceBalanceAfterTransfer = findClientByAccountNumber(accountNumber)
        val destinationBalanceAfterTransfer = findClientByAccountNumber(toClient.accountNumber)

        transferLogs.add(
            LogTransfer(
                dateAndTime,
                accountNumber,
                toClient.accountNumber,
                amount,
                sourceBalanceAfterTransfer.accountBalance,
                destinationBalanceAfterTransfer.accountBalance,
                loginData.firstName + " " + loginData.lastName
            )
        )
    }


    fun isClientExist(): Boolean {
        return accountNumber != ""
    }


    fun deleteClient() {
        clients.remove(
            BankClient(
                accountNumber,
                pinCode,
                accountBalance,
                firstName,
                lastName
            )
        )
    }


    fun deposit(amount: String) {
        for (i in clients.indices) {
            if (accountNumber == clients[i].accountNumber) {
                clients[i].apply {
                    val item = BankClient(
                        accountNumber,
                        pinCode,
                        accountBalance + amount.toDouble(),
                        firstName,
                        lastName
                    )
                    clients.set(i, item)
                }
                break
            }
        }
    }


    fun withdraw(amount: Double): String {
        if (amount > accountBalance) {
            return Status.ERROR.value
        } else {
            for (i in clients.indices) {
                if (accountNumber == clients[i].accountNumber) {
                    clients[i].apply {
                        val item = BankClient(
                            accountNumber,
                            pinCode,
                            accountBalance - amount,
                            firstName,
                            lastName
                        )
                        clients.set(i, item)
                    }
                    break
                }
            }
            return Status.SUCCESS.value
        }
    }


    fun transferMoney(
        amount: Double,
        toClient: BankClient
    ): String {
        val withdrawOperation = withdraw(amount)

        return if (withdrawOperation == Status.SUCCESS.value) {
            toClient.deposit(amount.toString())
            saveTransferLogs(amount, toClient)
            Status.SUCCESS.value
        } else Status.ERROR.value
    }


    companion object {
        private val transferLogs = ArrayList<LogTransfer>()
        private var clients = ArrayList<BankClient>()
        private fun getEmptyClient(): BankClient {
            return BankClient("", 0, 0.00, "", "")
        }


        fun displayTransferLogs(): ArrayList<LogTransfer> {
            return transferLogs
        }


        fun getTotalBalances(): Double {
            var totalBalance = 0.0
            if (clients.isNotEmpty()) {
                for (x in clients.indices) {
                    totalBalance += clients[x].accountBalance
                }
            }
            return totalBalance
        }


        fun findClientByAccountNumber(
            accountNumber: String
        ): BankClient {
            for (i in clients.indices) {
                if (accountNumber == clients[i].accountNumber) {
                    clients[i].apply {
                        return BankClient(
                            accountNumber,
                            pinCode,
                            accountBalance,
                            firstName,
                            lastName
                        )
                    }
                }
            }
            // No client has been found
            return getEmptyClient()
        }


        fun updateClient(
            accountNumber: String,
            pinCode: String,
            firstName: String,
            lastName: String,
            accountBalance: String
        ): String {

            for (i in clients.indices) {
                if (accountNumber == clients[i].accountNumber) {
                    clients[i].apply {
                        val item = BankClient(
                            accountNumber,
                            pinCode.toShort(),
                            accountBalance.toDouble(),
                            firstName,
                            lastName
                        )
                        clients.set(i, item)
                    }
                    break
                }
            }
            return "Client has been updated"
        }


        fun addClient(
            accountNumber: String,
            pinCode: Short,
            accountBalance: Double,
            firstName: String,
            lastName: String
        ) {
            val record = BankClient(
                accountNumber,
                pinCode,
                accountBalance,
                firstName,
                lastName
            )
            clients.add(record)
        }


        fun displayClients(): ArrayList<BankClient> {
            return clients
        }
    }


    override fun toString(): String {
        return "   Account Number  :    " + accountNumber + "\n" +
                "   Pin Code                :    " + pinCode + "\n" +
                "   Name                      :    " + firstName + " " + lastName + "\n" +
                "   Account Balance  :    " + accountBalance + "\n\n"
    }
}