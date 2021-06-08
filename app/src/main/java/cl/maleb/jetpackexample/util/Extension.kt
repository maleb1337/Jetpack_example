package cl.maleb.jetpackexample.util

import com.google.gson.Gson

fun <T> String.deserializeObject(castType: Class<T>?): T? = Gson().fromJson(this, castType)
fun <T> T.serializeObject(): String? = Gson().toJson(this)