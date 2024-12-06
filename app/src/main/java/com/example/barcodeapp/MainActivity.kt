package com.example.barcodeapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    lateinit var btnScan: Button

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Scanner : ${result.contents}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan = findViewById(R.id.btn_scan)
        btnScan.setOnClickListener {
            val option: ScanOptions = ScanOptions()
            option.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                .setPrompt("Scan a Barcode")
                .setCameraId(0)
                .setBeepEnabled(true)
                .setBarcodeImageEnabled(true)
                .setOrientationLocked(false)
                .setCaptureActivity(CaptureAct::class.java)
            barcodeLauncher.launch(option)

        }


    }

}