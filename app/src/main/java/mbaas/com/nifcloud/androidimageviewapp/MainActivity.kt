package mbaas.com.nifcloud.androidimageviewapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.nifcloud.mbaas.core.NCMB
import com.nifcloud.mbaas.core.NCMBFile


class MainActivity : AppCompatActivity() {
    lateinit var _btnShow: Button
    lateinit var _iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //**************** APIキーの設定 **************
        NCMB.initialize(this, "YOUR_APPLICATION_KEY", "YOUR_CLIENT_KEY")

        setContentView(R.layout.activity_main)

        _btnShow = findViewById<View>(R.id.btnShow) as Button
        _iv = findViewById<View>(R.id.imgShow) as ImageView
        _btnShow.setOnClickListener {
            // 画像ダウンロードする
            val file = NCMBFile("mBaaS_image.png")
            file.fetchInBackground { dataFetch, er ->
                if (er != null) {
                    //失敗処理
                    AlertDialog.Builder(this@MainActivity)
                            .setTitle("Notification from NIFCloud")
                            .setMessage("Error:" + er.message)
                            .setPositiveButton("OK", null)
                            .show()
                } else {
                    //成功処理
                    val bMap = BitmapFactory.decodeByteArray(dataFetch, 0, dataFetch.size)
                    _iv.setImageBitmap(bMap)
                }
            }
        }

    }

    companion object {

        private val TAG = "MainActivity"
        private val REQUEST_RESULT = 0
    }
}
