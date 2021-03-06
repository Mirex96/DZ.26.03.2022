package com.example.dz26032022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import com.example.dz10032022.R

class InformationOnProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_on_product)


        val informationProduct = findViewById<TextView>(R.id.informationProduct)
        val button = findViewById<Button>(R.id.buttonBack)

        val product = intent.getParcelableExtra<Product>(KEY_PRODUCT) ?: return
        val name = product.name
        val price = product.price
        val productQuantity = product.productQuantity
        val quality = product.quality
        informationProduct.text =
            "Name: $name \nPrice: $price \nQuantity: $productQuantity \nQuality: $quality"

        button.setOnClickListener {
            finish()
        }


    }
}