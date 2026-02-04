package com.example.githubsearch.model

sealed class UIEvent {
    data class showMessage(val message: String) : UIEvent()
}