package com.example.shoppingmall

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_purchase.*

class PurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        setTitle("주문서 작성")

        val intent = intent // 값을 가져오기 위해 인텐트 변수 선언


        var totalPrice: Int = 0 // 총 결재 금액 변수이다.

        /* xml layout 에서 visibility = gone 으로 초기화 되어있던 TextView 가
        intent 에서 넘어온 상품 이름이 존재하면 그 이름에 맞는 TextView 의 Visibility 를 보이게 한다.
        */
        if (intent.hasExtra("macBook")) {
            firstItem.visibility = View.VISIBLE
            firstItem_price.visibility = View.VISIBLE
            totalPrice += intent.getIntExtra("macBook",0)
        }
        if (intent.hasExtra("airPods")) {
            secondItem.visibility = View.VISIBLE
            secondItem_price.visibility = View.VISIBLE
            totalPrice += intent.getIntExtra("airPods",0)
        }
        if (intent.hasExtra("appleWatch")) {
            thirdItem.visibility = View.VISIBLE
            thirdItem_price.visibility = View.VISIBLE
            totalPrice += intent.getIntExtra("appleWatch",0)
        }
        if (intent.hasExtra("iPad")) {
            fourthItem.visibility = View.VISIBLE
            fourthItem_price.visibility = View.VISIBLE
            totalPrice += intent.getIntExtra("iPad",0)
        }

        total.text = totalPrice.toString()+"원" // 총 결재 금액을 화면에 보여준다.

        getItem.setOnClickListener {
            // 주소 또는 연락처의 값이 비어있을 때 구매 버튼 클릭 시 경고 메시지를 출력한다.
            if(get_Address.text.toString() == "" || get_Phone.text.toString() == "") {
                Toast.makeText(this,"올바른 고객 정보를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
            // 구매 확정 메시지와 함께 첫번째 페이지로 이동한다.
            else {
                Toast.makeText(this,"구매가 완료되었습니다!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}