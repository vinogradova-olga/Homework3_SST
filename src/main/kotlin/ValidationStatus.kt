package org.example

sealed class ValidationStatus {
    data object Accepted : ValidationStatus()
    data class Rejected(val errorCode: ValidationError) : ValidationStatus()
}