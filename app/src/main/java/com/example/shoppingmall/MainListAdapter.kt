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
    context: Context,
    myItem: ArrayList<ItemList>
) : BaseAdapter() {
    private val mContext = context
    private val mItem = myItem
    private val mInflater = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

//        val view: View = LayoutInflater.from(context).inflate(R.layout.main_layout_item, null)
        var view = convertView
        lateinit var holder : ViewHolder

        if(view == null) {
            holder = ViewHolder()
            view = mInflater.inflate(R.layout.main_layout_item, null)

            holder.itemName = view.findViewById(R.id.item_title)
            holder.itemPrice = view.findViewById(R.id.item_price)
            holder.photo = view.findViewById(R.id.item_img)
            holder.check = view.findViewById(R.id.delete_check)

            view.tag = holder

            holder.itemName?.text = mItem[position].itemName
            holder.itemPrice?.text = mItem[position].itemPrice
            holder.photo?.setImageResource(mContext.resources.getIdentifier(mItem[position].photo, "drawable", mContext.packageName))
            return view

        } else {
            holder = view.tag as ViewHolder
        }


        // 이미지, 상품 데이터를 ImageView, TextView 안에 담는다.
        val items = mItem[position]
        val resourceID = mContext.resources.getIdentifier(items.photo, "drawable", mContext.packageName)
        holder.itemName?.text = items.itemName
        holder.itemPrice?.text = items.itemPrice
        holder.photo?.setImageResource(resourceID)

        return view
    }

    override fun getItem(position: Int): Any {
        return mItem[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mItem.size
    }

    inner class ViewHolder {
        lateinit var itemName: TextView
        lateinit var itemPrice: TextView
        lateinit var photo: ImageView
        lateinit var check: CheckBox
    }

}

