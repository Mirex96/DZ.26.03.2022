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
import com.example.dz10032022.InformationOnProduct
import com.example.dz10032022.R
import com.example.dz26032022.IdGenerator.generateId
import kotlinx.android.parcel.Parcelize


const val KEY_PRODUCT = "KEY_PRODUCT"
const val KEY_EDIT_PRODUCT = "KEY_EDIT_PRODUCT"
const val KEY_ADD_PRODUCT = "KEY_ADD_PRODUCT"

const val REQUEST_ADD = 101
const val REQUEST_EDIT = 100


@Parcelize
data class Product(
    val name: String,
    val price: String,
    val productQuantity: String,
    val quality: String,
    val id: Int

) : Parcelable


class MainActivity : AppCompatActivity() {



    private val defaultProductList by lazy {
        mutableListOf(
            Product("Свекла", "20 руб.", "100 штук", "Хорошее", generateId()),
            Product("Брокколи", "100 руб.", "120 штук", "Хорошее", generateId()),
            Product("Брюссельская капуста", "30 руб.", "12 штук", "Хорошее", generateId()),
            Product("Капуста", "204 руб.", "89 штук", "Хорошее", generateId()),
            Product("Морковь", "123 руб.", "54 штук", "Хорошее", generateId()),
            Product("Цветная капуста", "456 руб.", "78 штук", "Хорошее", generateId()),
            Product("Систовая свекла", "567 руб.", "14 штук", "Хорошее", generateId()),
            Product("Турецкий горох", "276 руб.", "58 штук", "Хорошее", generateId()),
            Product("Огурец", "32 руб.", "89 штук", "Хорошее", generateId()),
            Product("Баклажан", "54 руб.", "34 штук", "Хорошее", generateId()),
            Product("Чеснок", "564 руб.", "13 штук", "Хорошее", generateId()),
            Product("Кольраби", "867 руб.", "78 штук", "Хорошее", generateId()),
            Product("Лук-порей", "987 руб.", "89 штук", "Хорошее", generateId()),
            Product("Чечевица", "342 руб.", "43 штук", "Хорошее", generateId()),
            Product("Лук репчатый", "22 руб.", "32 штук", "Хорошее", generateId()),
            Product("Горох", "564 руб.", "98 штук", "Хорошее", generateId()),
            Product("Стручковый перец", "287 руб.", "266 штук", "Хорошее", generateId()),
            Product("Картофель", "231 руб.", "167 штук", "Хорошее", generateId()),
            Product("Зеленый лук", "345 руб.", "87 штук", "Хорошее", generateId()),
            Product("Шпинат", "567 руб.", "645 штук", "Хорошее", generateId()),
            Product("Тыква", "789 руб.", "65 штук", "Хорошее", generateId()),
            Product("Репа", "543 руб.", "87 штук", "Хорошее", generateId()),
        )
    }

    private val adapter = ProductAdapter(::onSelect, ::onMore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val add = findViewById<View>(R.id.onAdd)
        add.setOnClickListener() {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)

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
                startActivityForResult(intent, REQUEST_EDIT)


            }

            .setNeutralButton("Отмена")
            { p0, p1 ->
                Toast.makeText(this, "Диалог закрыт", LENGTH_SHORT).show()
            }


            .show()
    }

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            val editProduct = data.getParcelableExtra<Product>(KEY_EDIT_PRODUCT) ?:return
           val productInList = defaultProductList.find { listProduct ->
                listProduct.id == editProduct.id
            }

            val position = defaultProductList.indexOf(productInList)
            defaultProductList.set(position, editProduct)
            adapter.setData(defaultProductList)




        } else if (requestCode == REQUEST_ADD && resultCode == RESULT_OK && data != null) {
            val newProduct = data.getParcelableExtra<Product>(KEY_ADD_PRODUCT) ?: return
            defaultProductList.add(newProduct)
            adapter.setData(defaultProductList)


        }


    }


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