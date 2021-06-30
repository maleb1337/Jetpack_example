package cl.maleb.jetpackexample.util

import com.google.gson.Gson

inline fun <reified T> String.deserializeObject(): T? = Gson().fromJson(this, T::class.java)
fun <T> T.serializeObject(): String? = Gson().toJson(this)