package com.rafael.moviedbapp.data.repositories

import com.rafael.moviedbapp.data.models.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository() {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

//    fun login(username: String, password: String): LoginResult<LoggedInUser> {
//        // handle login
//        val result = dataSource.login(username, password)
//
//        if (result is LoginResult.Success) {
//            setLoggedInUser(result.data)
//        }
//
//        return result
//    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}