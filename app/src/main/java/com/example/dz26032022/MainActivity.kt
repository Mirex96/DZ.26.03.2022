package com.example.dz26032022

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dz10032022.R
import com.example.dz26032022.IdGenerator.generateId
import kotlinx.android.parcel.Parcelize


const val KEY_PRODUCT = "KEY_PRODUCT"
const val KEY_EDIT_PRODUCT = "KEY_EDIT_PRODUCT"
const val KEY_ADD_PRODUCT = "KEY_ADD_PRODUCT"
const val REQUEST_ADD = 101
const val REQUEST_EDIT = 100
const val SP_NAME = "User_SP"
const val KEY_SP_PRODUCT = "KEY_SP_PRODUCT"


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

    private val adapter = ProductAdapter(::onSelect, ::onMore,::onReplacement)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ProductPreferences.init(this)


        val add = findViewById<View>(R.id.onAdd)
        add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)
        }

        val productRecycler = findViewById<RecyclerView>(R.id.productsList)
        productRecycler.adapter = adapter
        adapter.setData(defaultProductList)

        val saveProductName = findViewById<View>(R.id.saveProductName)
        saveProductName.setOnClickListener {

            val intent = Intent(this, SaveProductNameActivity::class.java)
            startActivity(intent)

        }



// Сделать , чтобы при открытии приложения показывало последний сохраненный в корзину товар !!!!!!!!

    }


    private fun onSelect(product: Product) {
        val intent = Intent(this, InformationOnProduct::class.java)
        intent.putExtra(KEY_PRODUCT, product)
        startActivity(intent)

    }

    private fun onMore(product: Product) {
        BottomSheetDialog.TAG
        BottomSheetDialog.show(supportFragmentManager)


        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_REMOTE, this) { _, bundle ->
                bundle.getString(BUNDLE_KEY_REMOTE)
                defaultProductList.remove(product)
                adapter.neMoved(defaultProductList)
                Toast.makeText(this, "Продукт ${product.name} удален", LENGTH_SHORT).show()
            }
        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_EDIT, this) {_, bundle ->
                bundle.getString(BUNDLE_KEY_EDIT)
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra(KEY_EDIT_PRODUCT, product)
                startActivityForResult(intent, REQUEST_EDIT)
            }
        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_CLONE, this) {_,bundle ->
                bundle.getString(BUNDLE_KEY_CLONE)
                defaultProductList.add(product)
                adapter.setData(defaultProductList)
                Toast.makeText(this, "Продукт ${product.name} клонирован", LENGTH_SHORT).show()
            }
        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_SAVE, this) {_, bundle ->
                bundle.getString(BUNDLE_KEY_SAVE)
                ProductPreferences.name = product.name
                Toast.makeText(this, "Продукт ${product.name} сохранен", LENGTH_SHORT).show()
            }
        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_NUMBER_1, this) {_, bundle ->
                bundle.getString(BUNDLE_KEY_NUMBER_1)


            }

        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_NUMBER_2, this) {_, bundle ->
                bundle.getString(BUNDLE_KEY_NUMBER_2)


            }

        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_APPLY, this) {_, bundle ->
                bundle.getString(BUNDLE_KEY_APPLY)


            }



    }

    private fun onReplacement(product: Product) {
        val firstProduct = defaultProductList[0]
        val lastProduct = defaultProductList[3]
        defaultProductList[0] = lastProduct
        defaultProductList[3] = firstProduct
        adapter.moved(defaultProductList, 0, 3)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            val editProduct = data.getParcelableExtra<Product>(KEY_EDIT_PRODUCT) ?: return
            val productInList = defaultProductList.find { listProduct ->
                listProduct.id == editProduct.id
            }
            val position = defaultProductList.indexOf(productInList)
            defaultProductList[position] = editProduct
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