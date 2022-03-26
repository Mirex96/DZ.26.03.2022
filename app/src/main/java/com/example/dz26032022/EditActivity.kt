package com.example.dz26032022

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dz10032022.R

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val imageEdit = findViewById<View>(R.id.image)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val priceEditText = findViewById<EditText>(R.id.priceEditText)
        val quantityEditText = findViewById<EditText>(R.id.quantityEditText)
        val qualityEditText = findViewById<EditText>(R.id.qualityEditText)
        val button = findViewById<Button>(R.id.buttonSave)


        val product = intent.getParcelableExtra<Product>(KEY_EDIT_PRODUCT) ?: return

        nameEditText.setText(product.name)
        priceEditText.setText(product.price)
        quantityEditText.setText(product.productQuantity)
        qualityEditText.setText(product.quality)


        button.setOnClickListener {
            val intent = Intent()
            val newEditProduct = Product(
                nameEditText.text.toString(),
                priceEditText.text.toString(),
                quantityEditText.text.toString(),
                qualityEditText.text.toString(),
                product.id
            )

            intent.putExtra(KEY_EDIT_PRODUCT, newEditProduct)
            setResult(RESULT_OK, intent)
            finish()
            if(product != newEditProduct) {
                Toast.makeText(this, "Продукт ${product.name} изменен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Продукт не изменен", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
