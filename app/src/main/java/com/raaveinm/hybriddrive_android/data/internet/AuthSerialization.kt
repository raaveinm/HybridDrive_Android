package com.raaveinm.hybriddrive_android.data.internet

import kotlinx.serialization.Serializable

@Serializable
data class AuthSerialization(
    val username: String,
    val password: String
)

/**
 * {
 *   "username": "your_username",
 *   "password": "your_password"
 * }
 */