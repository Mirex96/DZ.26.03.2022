package com.example.dz26032022

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dz10032022.R

class CustomViewLayout(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    init {
        inflate(context, R.layout.custom_view_layout, this)

        val imageView = findViewById<ImageView>(R.id.image)

        val set = context.obtainStyledAttributes(R.styleable.CustomViewLayout)

        val image = set.getDrawable(R.styleable.CustomViewLayout_image)

        imageView.setImageDrawable(image)
    }

}