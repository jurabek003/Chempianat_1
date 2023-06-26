package com.jurabek888.chepianat

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import com.jurabek888.chepianat.DataBace.MyDataBace
import com.jurabek888.chepianat.adapter.My_adapter
import com.jurabek888.chepianat.databinding.ActivityMainBinding
import com.jurabek888.chepianat.databinding.ItemAlertBinding
import com.jurabek888.chepianat.databinding.NavigationItemBinding
import com.jurabek888.chepianat.madels.USer

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var myAdapter: My_adapter
    private lateinit var myDataBace: MyDataBace

    private lateinit var list: ArrayList<USer>

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()

        binding.myMenuIc.setOnClickListener {
        binding.myDrewer.drawerElevation
            binding.myDrewer.open()

        }
        binding.mySwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (binding.mySwitch.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.include.setBackgroundColor(R.color.purple_700)
                val navigationItemBinding=NavigationItemBinding.inflate(layoutInflater)
                navigationItemBinding.root.setBackgroundColor(R.color.purple_700)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })

 binding.myNavigation.setNavigationItemSelectedListener {
       when(it.itemId){
           R.id.mene_add ->{
               val dialog=AlertDialog.Builder(this).create()
               val itemAlertBinding=ItemAlertBinding.inflate(layoutInflater)
               dialog.setView(itemAlertBinding.root)
               dialog.show()
               itemAlertBinding.btnSave.setOnClickListener {
                   val uSer= USer(itemAlertBinding.edtName.text.toString(),itemAlertBinding.edtNumber.text.toString())
                   myDataBace.add(uSer)
                   loadData()
                   dialog.cancel()
               }

           }
           R.id.menu_alif->{

               myAdapter.sortList()


           }
           R.id.mene_id->{
               myAdapter.sortID()

           }
       }
       true
   }


    }

    private fun loadData() {
        myDataBace= MyDataBace(this)
        list= ArrayList()
        list.addAll(myDataBace.getall())
        myAdapter=My_adapter(list,object :My_adapter.RvAction{
            override fun deletUser(user: USer, position: Int) {
                myDataBace.deletUSer(user)
                list.remove(user)
                myAdapter.notifyItemRemoved(position)
                myAdapter.notifyDataSetChanged()
                Toast.makeText(this@MainActivity,"deleted",Toast.LENGTH_SHORT).show()

            }

        })
        binding.myRecakle.adapter=myAdapter
    }
}