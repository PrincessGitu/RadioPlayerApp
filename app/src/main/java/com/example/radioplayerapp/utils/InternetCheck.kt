package com.example.radioplayerapp.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Gitanjali Ghangale on 7/14/2021.
 */
class InternetCheck {
    companion object{

        fun  isNetworkAvailable(context: Context):Boolean{
            val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val internetInfo =conManager.activeNetworkInfo
            return internetInfo!=null && internetInfo.isConnected
        }


        private fun checkNetwork(context: Context): Boolean {
            val connManager =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return true
        }
    }
}