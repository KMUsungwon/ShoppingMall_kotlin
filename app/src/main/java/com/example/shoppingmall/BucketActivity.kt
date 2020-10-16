package com.example.shoppingmall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bucket.*


// 데이터 베이스 접근을 위한 변수 설정




class BucketActivity : AppCompatActivity() {
    val database = Firebase.database
    var myRef = database.getReference("itemList")

    val mRootDatabaseReference = FirebaseDatabase.getInstance().reference
    val mItemListDataSet =
        mRootDatabaseReference.child("itemList") //닉네임 담아놀 하위 데이터베이스


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bucket)
        setTitle("나의 장바구니")

        //구매 페이지, 메인 페이지로 이동하기 위한 버튼 변수
        val btnHome: Button = findViewById(R.id.goHome)
        val btnPurchase: Button = findViewById(R.id.goPurchase)
        val btnDelete: Button = findViewById(R.id.deleteItem)

        var myItem = ArrayList<ItemList>()


        mItemListDataSet.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myItem.clear()
                for(snapshot in dataSnapshot.children) {
                    // 키 값을 비교해서 일치하면 ArrayList에 값을 차례대로 할당하여 추가한다.
                    when {
                        snapshot.key.toString() == "iPad" -> myItem.add(ItemList(snapshot.key.toString(),snapshot.value.toString(),"ipad"))
                        snapshot.key.toString() == "macBook" -> myItem.add(ItemList(snapshot.key.toString(),snapshot.value.toString(),"macbook"))
                        snapshot.key.toString() == "airPods" -> myItem.add(ItemList(snapshot.key.toString(),snapshot.value.toString(),"airpods"))
                        snapshot.key.toString() == "appleWatch" -> myItem.add(ItemList(snapshot.key.toString(),snapshot.value.toString(),"watch"))
                    }

                }
                //리스트뷰에 어댑터를 연결 후 값을 렌더링 한다.
                val itemAdapter = MainListAdapter(this@BucketActivity,myItem)
                view_item.adapter = itemAdapter
//                btnDelete.setOnClickListener {
//                    for(snapshot in myItem) {
//
//                    }
//                }
            }

        })



        //메인 페이지로 이동
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}



//리스트뷰 안의 내용을 구성할 변수를 담는 클래스
class ItemList(val itemName: String, val itemPrice: String, val photo: String)

// Adapter를 만들기 위한 클래스
class MainListAdapter (val context: Context, val myItem: ArrayList<ItemList>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //item을 Adapter에서 사용할 View형태로 부풀려주는 역할
        val view: View = LayoutInflater.from(context).inflate(R.layout.main_layout_item, null)

        // 위에서 생성된 View를 layout_item.xml 파일의 각 뷰와 연결
        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemPrice = view.findViewById<TextView>(R.id.item_price)
        val itemImg = view.findViewById<ImageView>(R.id.item_img)

        // 이미지, 상품 데이터를 ImageView, TextView 안에 담는다.
        val items = myItem[position]
        val resourceID = context.resources.getIdentifier(items.photo, "drawable", context.packageName)
        itemImg.setImageResource(resourceID)
        itemTitle.text = items.itemName
        itemPrice.text = items.itemPrice

        return view
    }

    override fun getItem(position: Int): Any {
        return myItem[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return myItem.size
    }

}