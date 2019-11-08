package dev.haenara.apkdownstall

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Downstaller {
    private lateinit var mContext: Context
    public fun init(context: Context){
        mContext = context
    }


}