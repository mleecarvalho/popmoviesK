package com.example.marcio.popmoviesk.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import com.example.marcio.popmoviesk.R

/**
 * Created by marcio on 18/01/2018.
 */
object NetConnection {

    fun hasInternetConnection(context: Context) : Boolean{
        val cm: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        return (netInfo != null && netInfo.isConnectedOrConnecting)
    }

    fun showConnectionError(activity: Activity){
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.no_internet_msg)
                .setTitle(R.string.connection_error_title)
                .setPositiveButton(R.string.ok_button, alertListener() )
    }

    private fun alertListener() : DialogInterface.OnClickListener{
        return DialogInterface.OnClickListener(){ dialog: DialogInterface, i: Int ->
            fun onClick(){
                dialog.dismiss()
            }
        }
    }
}