package com.example.dz26032022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.dz10032022.R

class SaveProductNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_product_name)


        val saveNameProduct = findViewById<TextView>(R.id.nameSaveInformation)

        saveNameProduct.text = ProductPreferences.name
        val button = findViewById<Button>(R.id.buttonBackSaveName)
        button.setOnClickListener {
            finish()
        }

    }


}