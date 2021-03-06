package com.example.shoppingmall

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    // database 연동하기 위한 변수 설정
    // itemList 이름을 가진 경로를 myRef 변수에 설정
    var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    var myRef = database!!.getReference("itemList")

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


        var clicked: Boolean = false

        // 구매 버튼 클릭 시 기능 구현
        btnPurchase.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            if(iPad.isChecked == true) {
                intent.putExtra("iPad", 1190000)
                clicked = true
            }
            if(appleWatch.isChecked == true) {
                intent.putExtra("appleWatch", 359000)
                clicked = true
            }
            if(macBook.isChecked == true) {
                intent.putExtra("macBook", 1750000)
                clicked = true
            }
            if(airPods.isChecked == true) {
                intent.putExtra("airPods", 150000)
                clicked = true
            }

            if(clicked) {
                startActivityForResult(intent, 0)
                clicked = false
            }
            else {
                Toast.makeText(this,"선택된 제품이 없습니다.",Toast.LENGTH_LONG).show()
            }


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