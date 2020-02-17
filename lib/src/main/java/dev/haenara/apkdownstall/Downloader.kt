package dev.haenara.apkdownstall

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import java.io.File

class Downloader {
    fun download(context: Context, url: String, fileName: String, listener: OnDownloadedListener) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val completeReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                val sDownloadFile =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        .path + "/" + fileName
                // 다운로드 경로로 파일 생성
                val f = File(sDownloadFile)

                if (f.exists()) {
                    listener.onDownloadFinished(f)
                } else {
                    listener.onDownloadFailed()
                }
            }
        }


        val completeFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        context.registerReceiver(completeReceiver, completeFilter)
        try { Thread.sleep(1000) } catch (e: InterruptedException) { }


        val urlToDownload = Uri.parse(url)
        val pathSegments = urlToDownload.pathSegments
        val request = DownloadManager.Request(urlToDownload)
//        request.addRequestHeader("Authorization", "Basic aaa")
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//        request.setTitle("Title")
//        request.setDescription("description")
        request.setDestinationUri(urlToDownload)

        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
//            pathSegments[pathSegments.size - 1]
            fileName
        )
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs()
        downloadManager.enqueue(request)
    }
}