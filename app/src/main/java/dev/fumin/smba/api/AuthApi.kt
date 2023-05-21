package dev.fumin.smba.api

import kotlinx.coroutines.delay

object AuthApi {

    suspend fun authenticate(id: String, password: String): Boolean {
        delay(2000)
        return id == "test" && password == "1234"
    }

}
