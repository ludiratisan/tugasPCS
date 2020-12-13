package com.example.appsallinone.ui.auth

import android.content.Context
import com.example.appsallinone.data.model.ActionState
import com.example.appsallinone.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AppsAllInOneAuth {
        fun logout(context: Context, callback: ((ActionState<Boolean>)->Unit)? = null ){
            val repo = AuthRepository(context)
            CoroutineScope(Dispatchers.IO).launch {
                val resp = repo.logout()
                withContext(Dispatchers.Main) {
                    if (callback != null) callback.invoke(resp)
                }
            }
        }
}