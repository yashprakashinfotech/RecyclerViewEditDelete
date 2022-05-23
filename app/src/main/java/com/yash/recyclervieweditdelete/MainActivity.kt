package com.yash.recyclervieweditdelete

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yash.recyclervieweditdelete.adapter.UserAdapter
import com.yash.recyclervieweditdelete.model.UserData

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd : FloatingActionButton
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = userAdapter

        btnAdd.setOnClickListener {
            addInfo()
        }
    }

    private fun initView(){
        userList = ArrayList()
        btnAdd = findViewById(R.id.btnAdd)
        mRecyclerView = findViewById(R.id.mRecycler)
        userAdapter = UserAdapter(this,userList)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo(){

        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item,null)

        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData(names,number))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()

    }
}