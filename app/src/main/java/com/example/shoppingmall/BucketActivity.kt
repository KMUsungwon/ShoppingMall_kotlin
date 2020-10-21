package com.example.shoppingmall

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.lang.UScript.getName
import android.icu.util.ULocale.getName
import android.media.Image
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.util.SparseBooleanArray
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getName
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bucket.*
import kotlinx.android.synthetic.main.main_layout_item.view.*

class BucketActivity : AppCompatActivity() {
    private val database = Firebase.database
    val myRef = database.getReference("itemList")


    private val myItem = arrayListOf<ItemList>()
    private lateinit var itemAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bucket)
        setTitle("나의 장바구니")

        //구매 페이지, 메인 페이지로 이동하기 위한 버튼 변수
        val btnHome: Button = findViewById(R.id.goHome)
        val btnPurchase: Button = findViewById(R.id.goPurchase)
        val btnDelete: Button = findViewById(R.id.btnDelete)


        var sumText = findViewById<TextView>(R.id.sumPrice)
        var sumPrice: Int = 0


        myRef.addValueEventListener(object : ValueEventListener {


            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myItem.clear()
                for (snapshot in dataSnapshot.children) {
                    // 키 값을 비교해서 일치하면 ArrayList에 값을 차례대로 할당하여 추가한다.
                    when {
                        snapshot.key.toString() == "iPad" -> {
                            myItem.add(
                                ItemList(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "ipad",true
                                )
                            )
                            sumPrice += 1190000
                        }
                        snapshot.key.toString() == "macBook" -> {
                            myItem.add(
                                ItemList(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "macbook",true
                                )
                            )
                            sumPrice += 1750000
                        }

                        snapshot.key.toString() == "airPods" -> {
                            myItem.add(
                                ItemList(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "airpods",true
                                )
                            )
                            sumPrice += 150000
                        }
                        snapshot.key.toString() == "appleWatch" -> {
                            myItem.add(
                                ItemList(
                                    snapshot.key.toString(),
                                    snapshot.value.toString(),
                                    "watch", true
                                )
                            )
                            sumPrice += 359000
                        }
                    }

                }
                //리스트뷰에 어댑터를 연결 후 값을 렌더링 한다.
                itemAdapter = MainListAdapter(this@BucketActivity, myItem)
                view_item.adapter = itemAdapter
                view_item.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                sumText.text = "총 금액 : $sumPrice"
                sumPrice = 0



                btnDelete.setOnClickListener {

//
//                    view_item.clearChoices()
//                    itemAdapter.notifyDataSetChanged()
                }
//
//                    var check = listview.checkedItemPosition
//                    Log.d("선택된 놈", check.toString())
//
//                    if(check >=0 && check < itemAdapter.count){
//                        myRef.child(myItem[check].itemName).removeValue()
//                        itemAdapter.notifyDataSetChanged()
//                    }
//
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

class ItemList(itemName: String,itemPrice: String,photo: String, selected: Boolean) {
    var itemName: String = itemName
    var itemPrice: String = itemPrice
    var photo: String = photo
    var selected: Boolean = selected

    fun getName(): String {
        return itemName
    }
    fun setName(itemName: String) {
        this.itemName = itemName
    }
    fun getPrice(): String {
        return itemPrice
    }
    fun setPrice(itemPrice: String) {
        this.itemPrice = itemPrice
    }
    fun getImg(): String {
        return photo
    }
    fun setImg(photo: String) {
        this.photo = photo
    }
    fun isSelected(): Boolean {
        return selected
    }
    fun setSelect(selected: Boolean) {
        this.selected = selected
    }
}




class CheckableLayout(context: Context,attributeSet: AttributeSet) : LinearLayout(context,attributeSet),Checkable{

    override fun isChecked(): Boolean{

        return delete_check.isChecked
    }

    override fun toggle() {
        isChecked = !delete_check.isChecked
    }

    override fun setChecked(checked: Boolean) {
        if(delete_check.isChecked != checked ) delete_check.isChecked = checked
    }

    //checkBox.isChecked 는 레이아웃 안에 존재하는 체크박스의 체크여부를 말하고
    //isChecke 홀로 있는 것은 체크박스를 담고 있는 뷰그룹 전체를 가리킴
}