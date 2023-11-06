package com.example.qrtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import com.example.qrtest.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var textData: String
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //パーミッションランチャー呼び出し
        permissionLauncher.launch(android.Manifest.permission.CAMERA)
        binding.button.setOnClickListener {
            //オプション指定
            val options = ScanOptions().apply {
                setOrientationLocked(false)
                //読み取る対象をQRのみに指定
                setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            }
            //カメラ起動
            barcodeLauncher.launch(options)
        }
    }
    //パーミッション確認
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){_ ->}
    //カメラアプリ起動、読み取り後の処理
    val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents == null) {
            // ここでQRコードを読み取れなかった場合の処理を書く
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG)
                .show()
        } else {
            // ここでQRコードを読み取れた場合の処理を書く
        }
    }
}