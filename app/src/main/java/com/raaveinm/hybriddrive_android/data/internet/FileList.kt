package com.raaveinm.hybriddrive_android.data.internet

import kotlinx.serialization.Serializable

@Serializable
data class FileList(
    val id: Int,
    val name: String,
    val size: Int,
    val type: String,
    val created_at: String,
    val message: String?
)

/**
 * [
 *   {
 *     "id": "1",
 *     "name": "document.pdf",
 *     "size": "1024",
 *     "type": "application/pdf",
 *     "created_at": "2025-11-28 12:00:00"
 *   }
 * ]
 * or it will throw error message
 */