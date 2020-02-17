package dev.haenara.apkdownstall.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.haenara.apkdownstall.Downstaller
import dev.haenara.apkdownstall.OnDownloadedListener
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Downstaller().downloadAndInstall(
            this,
            "https://download.apkpure.com/b/apk/Y29tLmdvb2dsZS5hbmRyb2lkLmNhbGN1bGF0b3JfNzgwMDAzMDNfODVlZmExMWY?_fn=Q2FsY3VsYXRvcl92Ny44ICgyNzEyNDEyNzcpX2Fwa3B1cmUuY29tLmFwaw&k=f9999fa746fe8ffcfd42262c117f773d5e4cf951&as=e1d57c3f644e70b0f3e48afce74415eb5e4a56c9&_p=Y29tLmdvb2dsZS5hbmRyb2lkLmNhbGN1bGF0b3I&c=1%7CTOOLS%7CZGV2PUdvb2dsZSUyMExMQyZ0PWFwayZzPTI3MDA2NTgmdm49Ny44JTIwKDI3MTI0MTI3NykmdmM9NzgwMDAzMDM&hot=1",
            object : OnDownloadedListener {
                override fun onDownloadFailed() {
                }

                override fun onDownloadFinished(file: File) {
                }

            })
    }
}
