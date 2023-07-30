package com.ahmed.bankapp.data

enum class Permissions(val value: Int) {
    GET_CLIENTS(1),
    ADD_CLIENT(2),
    DELETE_CLIENT(4),
    UPDATE_CLIENT(8),
    FIND_CLIENT(16),
    TRANSACTIONS(32),
    MANAGE_USERS(64),
    REGISTER_LOGIN(128),
}