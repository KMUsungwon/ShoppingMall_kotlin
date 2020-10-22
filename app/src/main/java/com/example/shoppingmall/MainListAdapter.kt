package com.example.shoppingmall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

// Adapter를 만들기 위한 Custom 어댑터
class MainListAdapter (
    val context: Context,
    val myItem: ArrayList<Product>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_product, null)

        // product 레이아웃의 각 뷰 들을 가져온다.
        val itemName = view.findViewById<TextView>(R.id.item_title)
        val itemPrice = view.findViewById<TextView>(R.id.item_price)
        val itemImg = view.findViewById<ImageView>(R.id.item_img)
        val check = view.findViewById<CheckBox>(R.id.delete_check)

        //Product 클래스 형태의 값을 가지고 있는 myItem 리스트를 불러온다.
        //리스트 안의 데이터 값들을 리스트뷰 각각의 뷰에 값을 할당해준다.
        val iList = myItem[position]
        check.isChecked = false
        itemName.text = iList.itemName
        itemPrice.text = iList.itemPrice
        itemImg.setImageResource(context.resources.getIdentifier(myItem[position].photo, "drawable", context.packageName))

        return view
    }

    override fun getItem(position: Int): Any {
        return myItem[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return myItem.size
    }

}