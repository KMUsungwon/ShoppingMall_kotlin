package com.example.shoppingmall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bucket.*
import kotlinx.android.synthetic.main.activity_product.view.*


class BucketActivity : AppCompatActivity() {
    //파이어베이스 RealTime Database 연동을 위한 변수 설정
    private val database = Firebase.database
    val myRef = database.getReference("itemList")

    // Product Class 형태의 값을 가지는 배열을 선언
    // ListView 사용을 위해 Adapter를 설정하는 변수 선언
    val myItem = arrayListOf<Product>()
    val itemAdapter = MainListAdapter(this, myItem)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bucket)
        setTitle("나의 장바구니")

        //구매 페이지, 메인 페이지로 이동하기 위한 버튼 변수
        val btnHome: Button = findViewById(R.id.goHome)
        val btnPurchase: Button = findViewById(R.id.goPurchase)
        val btnDelete: Button = findViewById(R.id.btnDelete) //제품 삭제 버튼


        // 장바구니에 담긴 상품 총 액수를 화면에 보여주기 위한 변수
        var sumText = findViewById<TextView>(R.id.sumPrice)
        var sumPrice: Int = 0

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            // 데이터가 변경될 때마다 새롭게 값을 렌더링한다.
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myItem.clear()
                for (snapshot in dataSnapshot.children) {
                    // 키 값을 비교해서 일치하면 ArrayList에 값을 차례대로 할당하여 추가한다.
                    when {
                        snapshot.key.toString() == "iPad" -> {
                            myItem.add(
                                Product(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "ipad"
                                )
                            )
                            sumPrice += 1190000
                        }
                        snapshot.key.toString() == "macBook" -> {
                            myItem.add(
                                Product(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "macbook"
                                )
                            )
                            sumPrice += 1750000
                        }

                        snapshot.key.toString() == "airPods" -> {
                            myItem.add(
                                Product(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "airpods"
                                )
                            )
                            sumPrice += 150000
                        }
                        snapshot.key.toString() == "appleWatch" -> {
                            myItem.add(
                                Product(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "watch"
                                )
                            )
                            sumPrice += 359000
                        }
                    }

                }

                //장바구니 내에 있는 상품의 총 금액을 화면에 표시한다.
                //값이 누적이 되지 않도록 값을 0으로 초기화한다.
                sumText.text = "총 금액 : $sumPrice"+"원"
                sumPrice = 0

                //리스트뷰와 어댑터를 연결한다.
                itemList.adapter = itemAdapter
                itemList.choiceMode = ListView.CHOICE_MODE_MULTIPLE // 여러 개 체크 가능하게

                //체크되어 있는 아이템 리스트를 삭제하는 메서드
                btnDelete.setOnClickListener {
                    for(i in 0 until itemList.count) {
                        val itemContent = itemList[i]
                        val bool_Check = itemContent.delete_check

                        if(bool_Check.isChecked) {
                            myRef.child(itemContent.item_title.text.toString()).removeValue()
                        }
                    }
                }
            }

        })

        //체크되어 있는 상품을 구매 페이지로 넘기기 위한 Intent 타입의 변수 선언
        val intent = Intent(this, PurchaseActivity::class.java)


        btnPurchase.setOnClickListener {
            var tmp: Boolean = false
            for(i in 0 until itemList.count) {
                val emptyCheck = itemList[i]
                if(emptyCheck.delete_check.isChecked) {
                    tmp = true
                }
            }
            if(!tmp) { // 장바구니에서 선택된 물품이 없으면 구매 페이지로 이동하지 않고 경고 메시지를 출력
                Toast.makeText(this, "선택한 물품이 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                for(i in 0 until itemList.count) {
                    val itemContent = itemList[i]
                    val bool_Check = itemContent.delete_check

                    if(bool_Check.isChecked) {
                        intent.putExtra(itemContent.item_title.text.toString(), Integer.parseInt(itemContent.item_price.text.toString()))
                    }
                }
                startActivityForResult(intent, 0)
            }

        }


        //메인 페이지로 이동
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}