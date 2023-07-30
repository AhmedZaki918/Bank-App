package com.ahmed.bankapp.data.log

data class LogTransfer(
    var dateAndTime: String,
    var sourceAccount: String,
    var destinationAccount: String,
    var amount: Double,
    var sourceBalance: Double,
    var destinationBalance: Double,
    var username: String
)





