package com.example.shoppingmall

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.ContextThemeWrapper
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    // database 연동하기 위한 변수 설정
    // itemList 이름을 가진 경로를 myRef 변수에 설정
    val database = Firebase.database
    val myRef = database.getReference("itemList")

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


        var price: Int = 0 ; // 처음에 제품 가격의 합을 0으로 초기화

        // 구매 버튼 클릭 시 기능 구현
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

            Toast.makeText(this, "총합: "+price, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PurchaseActivity::class.java)
            startActivityForResult(intent, 0)

            price = 0
        }

        // 장바구니 버튼 클릭 시 기능 구현
        btnBucket.setOnClickListener {

            //장바구니 버튼 클릭 시 장바구니 페이지로 넘어갈 것인지 확인하는 다이얼로그 창 보여주기
            val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.Theme_AppCompat_Light_Dialog))
            builder.setTitle("장바구니 페이지로 이동하시겠습니까?")
            builder.setMessage("이동 시 선택된 상품이 장바구니에 저장됩니다.")

            builder.setPositiveButton("확인") { _, _ ->
                //데이터 베이스에 체크되어 있는 상품의 이름과 가격을 key : value 형태로 저장
                if(iPad.isChecked == true) {
                    myRef.child("iPad").setValue("1190000")
                }
                if(appleWatch.isChecked == true) {
                    myRef.child("appleWatch").setValue("359000")
                }
                if(macBook.isChecked == true) {
                    myRef.child("macBook").setValue("1750000")
                }
                if(airPods.isChecked == true) {
                    myRef.child("airPods").setValue("150000")
                }

                // 장바구니 페이지로 이동하기
                val intent = Intent(this, BucketActivity::class.java)
                startActivityForResult(intent, 0)
            }
            builder.setNegativeButton("취소") { _, _ ->
                //취소 버튼 눌렀을 때에는 액션을 하지 않음
            }
            builder.show()

        }

    }
}