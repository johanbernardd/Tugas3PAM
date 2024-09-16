package com.example.tugas3pam

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var hasilTV: TextView
    private var currNum = ""
    private var operator = ""
    private var firstNum = ""
    private var secNum = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        hasilTV = findViewById(R.id.hasiltv)
        val tombol = listOf<Button>(
            findViewById(R.id.tombol0),
            findViewById(R.id.tombol1),
            findViewById(R.id.tombol2),
            findViewById(R.id.tombol3),
            findViewById(R.id.tombol4),
            findViewById(R.id.tombol5),
            findViewById(R.id.tombol6),
            findViewById(R.id.tombol7),
            findViewById(R.id.tombol8),
            findViewById(R.id.tombol9)
        )
        for (button in tombol) {
            button.setOnClickListener {
                gabungAngka(button.text.toString())
            }
        }

        findViewById<Button>(R.id.tombolAdd).setOnClickListener{jenisOperator("+")}
        findViewById<Button>(R.id.tombolMinus).setOnClickListener{jenisOperator("-")}
        findViewById<Button>(R.id.tombolMultiply).setOnClickListener{jenisOperator("*")}
        findViewById<Button>(R.id.tombolDivide).setOnClickListener{jenisOperator("/")}

        findViewById<Button>(R.id.tombolEqual).setOnClickListener{
            prosesKalkulasi()
        }
        findViewById<Button>(R.id.tombolClear).setOnClickListener {
            hilangkanKalkulasi()
        }
    }

    private fun gabungAngka(num:String) {
        currNum += num
        hasilTV.text = currNum
    }
    private fun jenisOperator(op: String) {
        firstNum = currNum
        operator = op
        currNum = ""
    }
    private fun prosesKalkulasi() {
        secNum = currNum

        if (firstNum.isNotEmpty() && secNum.isNotEmpty()) {
            val hasil = when(operator) {
                "+" -> firstNum.toDouble() + secNum.toDouble()
                "-" -> firstNum.toDouble() - secNum.toDouble()
                "*" -> firstNum.toDouble() * secNum.toDouble()
                "/" -> {
                    if (secNum.toDouble() == 0.0) {
                        "Undefined"
                    } else {
                        firstNum.toDouble() / secNum.toDouble()
                    }
                }
                else -> 0.0
            }
            hasilTV.text = hasil.toString()
            firstNum = ""
            secNum = ""
            currNum = ""
        }
    }
    private fun hilangkanKalkulasi() {
        currNum = ""
        firstNum = ""
        secNum = ""
        operator = ""
        hasilTV.text = "0"
    }
}