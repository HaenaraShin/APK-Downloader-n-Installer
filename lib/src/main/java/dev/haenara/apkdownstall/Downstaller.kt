package dev.haenara.apkdownstall

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import dev.haenara.apkdownstall.utill.DownstallerUtill
import java.io.File

class Downstaller {
    fun downloadAndInstall(context: Context, url : String, listener : OnDownloadedListener) {
        val fileName = DownstallerUtill.getFileName(url)
        checkPermission(context, afterSuccess = {
            download(context, url, fileName, object : OnDownloadedListener {
                override fun onDownloadFinished(file: File) {
                    listener.onDownloadFinished(file)
                    install(context, file)
                }

                override fun onDownloadFailed() {
                    listener.onDownloadFailed()
                }
            })
        })
    }

    fun downloadOnly(context: Context, url : String, listener : OnDownloadedListener) {
        val fileName = DownstallerUtill.getFileName(url)
        download(context, url, fileName, listener)
    }

    fun installOnly(context: Context, file: File) {
        install(context, file)
    }

    private fun download(
        context: Context,
        url: String,
        fileName: String,
        listener: OnDownloadedListener
    ) {
        Downloader().download(context, url, fileName, listener)
    }
    private fun install(context : Context, file: File) {
        Installer().install(context, file)
    }
}

interface OnDownloadedListener {
    fun onDownloadFinished(file : File)
    fun onDownloadFailed()
}

fun checkPermission(context: Context, afterSuccess : ()->Unit) {
    TedPermission.with(context)
        .setPermissionListener(object : PermissionListener{
            override fun onPermissionGranted() {
                afterSuccess()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            }

        })
        .setDeniedMessage("설정에서 필수 권한을 허용해주시기 바랍니다.")
        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .check()
}