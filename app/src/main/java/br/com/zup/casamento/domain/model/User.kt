package br.com.zup.casamento.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmationPassword: String = ""
) : Parcelable