package com.example.shoppingmall

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("쇼핑몰 홈")



        val btnPurchase: Button = findViewById(R.id.purchase) // 구매 버튼 변수
        val btnBucket: Button = findViewById(R.id.bucket) // 장바구니 버튼 변수

        // 각각의 상품에 따른 체크박스 변수
        val iPad: CheckBox = findViewById(R.id.img1_check)
        val appleWatch: CheckBox = findViewById(R.id.img2_check)
        val macBook: CheckBox = findViewById(R.id.img3_check)
        val airPods: CheckBox = findViewById(R.id.img4_check)

        val listItems = mapOf<String, Int>("iPad" to 1190000, "appleWatch" to 359000, "macBook" to 1750000, "airPods" to 150000)

        var price: Int = 0 ; // 처음에 제품 가격의 합을 0으로 초기화
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

            val intent = Intent(this, PurchaseActivity::class.java)
            startActivityForResult(intent, 0)

        }
        btnBucket.setOnClickListener {
            val intent = Intent(this, BucketActivity::class.java)

            startActivityForResult(intent, 0)
        }

    }
}