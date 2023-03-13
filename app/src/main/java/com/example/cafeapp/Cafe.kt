package com.example.cafeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cafe(
    val gambarCafe: String,
    val namaCafe : String,
    val alamatCafe: String,
    val deskripsiCafe: String,
    val gambarMenuCafe: String
) : Parcelable
