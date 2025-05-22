package com.example.personalprojectapp

data class Transaction(
    val description: String,
    val amount: Double,
    val isExpense: Boolean
)