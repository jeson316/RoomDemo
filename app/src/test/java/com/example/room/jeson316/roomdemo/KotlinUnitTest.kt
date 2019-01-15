package com.example.room.jeson316.roomdemo

import org.junit.Before
import org.junit.Test

/**
 * Created by Jeson316 on 2019/1/9.
 */
class KotlinUnitTest {


    @Test
    fun emnuTest() {

        val dong = DemoEnum.Dong
        println(dong.ordinal)
        println(dong.name)
    }


    enum class DemoEnum(var price: Int) {
        Dong(12),
        Xi(15),
        Nan(18),
        Bei(11);

        fun ddd(): Int {
            return price
        }
    }


}