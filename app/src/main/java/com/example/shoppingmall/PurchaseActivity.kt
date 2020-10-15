package com.example.shoppingmall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        setTitle("주문서 작성")
    }
}