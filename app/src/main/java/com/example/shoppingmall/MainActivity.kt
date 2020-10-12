package com.example.shoppingmall

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPurchase: Button = findViewById(R.id.purchase);
        val iPad: CheckBox = findViewById(R.id.img1_check);
        val appleWatch: CheckBox = findViewById(R.id.img2_check);
        val macBook: CheckBox = findViewById(R.id.img3_check);
        val airPods: CheckBox = findViewById(R.id.img4_check);

        var price: Int = 0 ;
        btnPurchase.setOnClickListener {
            if(iPad.isChecked == true) {
                price += 1190000
            }
            if(appleWatch.isChecked == true) {
                price += 359000
            }
            if(macBook.isChecked == true) {
                price += 1750000
            }
            if(airPods.isChecked == true) {
                price += 150000;
            }

//            val intent = Intent(this, MainActivity::class.java);
//            intent.putExtra("prices", Integer.parseInt(price.toString()))
            Toast.makeText(this, "총합: "+price, Toast.LENGTH_SHORT).show()
            price = 0;

        }
    }
}