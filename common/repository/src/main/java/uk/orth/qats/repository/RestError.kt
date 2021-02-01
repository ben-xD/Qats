package uk.orth.qats.repository

import com.google.gson.annotations.SerializedName

data class RestError(@SerializedName("code") val code: Int, @SerializedName("error") val text: String)