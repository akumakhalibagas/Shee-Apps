package com.she.apps.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.she.apps.data.source.remote.response.Users
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Shareds @Inject constructor(val sharedPreferences: SharedPreferences) {

    enum class Key {
        users
    }

    private val gson = Gson()
    private val sharedPreferencesEditor = sharedPreferences.edit()

    fun setUsers(key: Key, data: Users?) {
        val json = gson.toJson(data)
        sharedPreferencesEditor.putString(key.name, json).commit()
    }

    val users: Users?
        get() {
            val users = getString(Key.users)
            return gson.fromJson(users, Users::class.java)
        }

    fun getString(key: Key, defaultValue: String = ""): String {
        return sharedPreferences.getString(key.name, defaultValue)!!
    }
}