package cl.maleb.jetpackexample.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeViewData(
    val name: String? = null,
    val greet: String? = null
) : Parcelable
