package com.jurabek888.chepianat.DataBace

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.BaseAdapter
import com.jurabek888.chepianat.Interface.MyDbInterface
import com.jurabek888.chepianat.adapter.My_adapter
import com.jurabek888.chepianat.madels.USer
import java.util.Collections

class MyDataBace(context: Context):SQLiteOpenHelper(context,"my_databace",null,1)
,MyDbInterface
{
    override fun onCreate(p0: SQLiteDatabase?) {
        val query="create table MyData (id integer not null primary key autoincrement,name text not null, number text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun add(user: USer) {
        val database = this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put("name",user.name)
        contentValues.put("number",user.number)
        database.insert("MyData",null,contentValues)
        database.close()
    }

    override fun getall(): List<USer> {
        val list=ArrayList<USer>()
        val query = "select *from MyData"
        val database=this.readableDatabase
        val cursor=database.rawQuery(query, null)

        if (cursor.moveToFirst()){
            do {
                val user=USer(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(user)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun deletUSer(user: USer) {
        val database=this.writableDatabase
        database.delete("MyData","id=?", arrayOf(user.id.toString()))
        database.close()
    }

}