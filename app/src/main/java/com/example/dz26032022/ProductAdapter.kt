package com.example.dz26032022

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dz10032022.R


class ProductAdapter(
    private val onSelect: (Product) -> Unit,
    private val onMore: (Product) -> Unit,

    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    private var productList = listOf<Product>()
    fun setData(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

    }

    override fun getItemCount(): Int {
        return productList.size

    }


    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            itemView.findViewById<TextView>(R.id.name).text = product.name
            itemView.findViewById<TextView>(R.id.price).text = product.price
            itemView.findViewById<TextView>(R.id.productQuantity).text = product.productQuantity
            itemView.findViewById<TextView>(R.id.quality).text = product.quality

            itemView.setOnClickListener {
                onSelect(product)
            }

            itemView.findViewById<ImageView>(R.id.onMore).setOnClickListener {
                onMore(product)
            }


        }


    }
}