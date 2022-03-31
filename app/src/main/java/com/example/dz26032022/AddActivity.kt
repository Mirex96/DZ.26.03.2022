package com.example.dz26032022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.dz10032022.R

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val name = findViewById<EditText>(R.id.nameEditTextAdd)
        val price = findViewById<EditText>(R.id.priceEditTextAdd)
        val quantity = findViewById<EditText>(R.id.quantityEditTextAdd)
        val quality = findViewById<EditText>(R.id.qualityEditTextAdd)

        val intent = Intent()



        val button = findViewById<Button>(R.id.buttonSaveAdd)
        button.setOnClickListener() {

            val product = Product(
                name.text.toString(),
                price.text.toString(),
                quantity.text.toString(),
                quality.text.toString(),
                IdGenerator.generateId()
            )

            intent.putExtra(KEY_ADD_PRODUCT, product)
            setResult(RESULT_OK, intent)
            finish()
            Toast.makeText(this, "Продукт ${product.name} добавлен", LENGTH_SHORT).show()

        }
    }
}