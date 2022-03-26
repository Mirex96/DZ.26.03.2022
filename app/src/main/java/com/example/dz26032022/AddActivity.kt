package com.example.dz26032022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.dz10032022.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val name =findViewById<EditText>(R.id.nameEditTextAdd)
        val price = findViewById<EditText>(R.id.priceEditTextAdd)
        val quantity = findViewById<EditText>(R.id.quantityEditTextAdd)
        val quality = findViewById<EditText>(R.id.qualityEditTextAdd)


        val intent = Intent()
        val product = intent.getParcelableExtra<Product>(KEY_ADD_PRODUCT)

        val button = findViewById<Button>(R.id.buttonSaveAdd)
        button.setOnClickListener() {
            finish()
        }



    }
}