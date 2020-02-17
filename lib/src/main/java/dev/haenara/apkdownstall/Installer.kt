package dev.haenara.apkdownstall

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class Installer {
    fun install(context: Context, file: File) {
        // 다운로드 완료후 패키지 인스톨
        val i = Intent(Intent.ACTION_VIEW)
        // apk 파일 경로와 mime-type을 지정
        i.data = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".DownstallerFileProvider", file
        )
        i.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val pi = PendingIntent.getActivity(context, 0, i, 0)

        try {
            // PackagerInstaller Activity 실행
            pi.send()
            // Receiver 제거
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}