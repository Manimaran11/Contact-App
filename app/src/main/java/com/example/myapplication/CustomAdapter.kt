package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val context: Context,
                    private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataitem = dataList[position]

        val rowView = inflater.inflate(R.layout.list_row, parent, false)
        rowView.findViewById<TextView>(R.id.fname).text = dataitem.get("first_name")
        rowView.findViewById<TextView>(R.id.lname).text = dataitem.get("last_name")
        rowView.findViewById<TextView>(R.id.phnum).text = dataitem.get("work_number")
//        rowView.findViewById<TextView>(R.id.fname).text = "hi"
//        rowView.findViewById<TextView>(R.id.lname).text =  "heio"
//        rowView.findViewById<TextView>(R.id.phnum).text = "98765456789"

        rowView.tag = position
        return rowView
    }
}