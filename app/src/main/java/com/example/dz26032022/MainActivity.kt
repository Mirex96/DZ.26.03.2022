package com.example.dz26032022

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dz10032022.EditActivity
import com.example.dz10032022.InformationOnProduct
import com.example.dz10032022.R
import kotlinx.android.parcel.Parcelize


const val KEY_PRODUCT = "KEY_PRODUCT"
const val KEY_EDIT_PRODUCT = "KEY_EDIT_PRODUCT"
const val KEY_ADD_PRODUCT = "KEY_ADD_PRODUCT"

@Parcelize
data class Product(
    val name: String,
    val price: String,
    val productQuantity: String,
    val quality: String,
    val id: String

) : Parcelable


class MainActivity : AppCompatActivity() {
    lateinit var product: Product


    private val defaultProductList = mutableListOf(
        Product("Свекла", "20 руб.", "100 штук", "Хорошее", "1"),
        Product("Брокколи", "100 руб.", "120 штук", "Хорошее", "2"),
        Product("Брюссельская капуста", "30 руб.", "12 штук", "Хорошее", "3"),
        Product("Капуста", "204 руб.", "89 штук", "Хорошее", "4"),
        Product("Морковь", "123 руб.", "54 штук", "Хорошее", "5"),
        Product("Цветная капуста", "456 руб.", "78 штук", "Хорошее", "6"),
        Product("Систовая свекла", "567 руб.", "14 штук", "Хорошее", "7"),
        Product("Турецкий горох", "276 руб.", "58 штук", "Хорошее", "8"),
        Product("Огурец", "32 руб.", "89 штук", "Хорошее", "9"),
        Product("Баклажан", "54 руб.", "34 штук", "Хорошее", "10"),
        Product("Чеснок", "564 руб.", "13 штук", "Хорошее", "11"),
        Product("Кольраби", "867 руб.", "78 штук", "Хорошее", "12"),
        Product("Лук-порей", "987 руб.", "89 штук", "Хорошее", "13"),
        Product("Чечевица", "342 руб.", "43 штук", "Хорошее", "14"),
        Product("Лук репчатый", "22 руб.", "32 штук", "Хорошее", "15"),
        Product("Горох", "564 руб.", "98 штук", "Хорошее", "16"),
        Product("Стручковый перец", "287 руб.", "266 штук", "Хорошее", "17"),
        Product("Картофель", "231 руб.", "167 штук", "Хорошее", "18"),
        Product("Зеленый лук", "345 руб.", "87 штук", "Хорошее", "19"),
        Product("Шпинат", "567 руб.", "645 штук", "Хорошее", "20"),
        Product("Тыква", "789 руб.", "65 штук", "Хорошее", "21"),
        Product("Репа", "543 руб.", "87 штук", "Хорошее", "22"),
    )

    private val adapter = ProductAdapter(::onSelect, ::onMore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product = Product(
            name = String(),
            price = String(),
            productQuantity = String(),
            quality = String(),
            id = String()
        )


        val add = findViewById<View>(R.id.onAdd)
        add.setOnClickListener() {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(KEY_ADD_PRODUCT, product)
            startActivityForResult(intent, 101)

        }
        val productRecycler = findViewById<RecyclerView>(R.id.productsList)
        productRecycler.adapter = adapter
        adapter.setData(defaultProductList)
    }

    private fun onSelect(product: Product) {
        val intent = Intent(this, InformationOnProduct::class.java)
        intent.putExtra(KEY_PRODUCT, product)
        startActivity(intent)
    }

    private fun onMore(product: Product) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("Выберите опцию")
            .setPositiveButton(
                "Удалить"
            ) { p0, p1 ->
                defaultProductList.remove(product)
                adapter.setData(defaultProductList)
                Toast.makeText(this, "Продукт ${product.name} удалён", LENGTH_SHORT).show()

            }

            .setNegativeButton("Редактировать")
            { p0, p1 ->
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra(KEY_EDIT_PRODUCT, product)
                startActivityForResult(intent, 100)


            }

            .setNeutralButton("Отмена")
            { p0, p1 ->
                Toast.makeText(this, "Диалог закрыт", LENGTH_SHORT).show()
            }

            .show()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
//            val editProduct = data.getStringExtra(KEY_EDIT_PRODUCT)
//            product.setText(editProduct)
//        }
//    }


}


//Написать приложение, по учету товаров.
//Использовать Activity, RecyclerView, Fragment (по желанию)
//
//Функционал приложения:
//+ Добавление товара
//+ Отображение товара в списке товаров
//+ Отображение деталей конкретного товара
//+ Удаление товара из списка товаров
//+ Изменение данных уже существующего товара