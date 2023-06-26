package com.jurabek888.chepianat.Interface

import com.jurabek888.chepianat.madels.USer

interface MyDbInterface {

    fun add(user:USer)
    fun getall():List<USer>
    fun deletUSer(user: USer)

}