package com.example.dz10032022

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.dz26032022.KEY_EDIT_PRODUCT
import com.example.dz26032022.Product

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


        val product = intent.getParcelableExtra<Product>(KEY_EDIT_PRODUCT) ?:return
        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(KEY_EDIT_PRODUCT, imageEdit.toString())
            intent.putExtra(KEY_EDIT_PRODUCT, nameEditText.toString())
            intent.putExtra(KEY_EDIT_PRODUCT, priceEditText.toString())
            intent.putExtra(KEY_EDIT_PRODUCT, quantityEditText.toString())
            intent.putExtra(KEY_EDIT_PRODUCT, qualityEditText.toString())
            setResult(RESULT_OK, intent)
            finish()

        }

    }
}