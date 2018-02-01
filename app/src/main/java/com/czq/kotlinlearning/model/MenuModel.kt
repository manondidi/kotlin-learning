package com.czq.kotlinlearning.model

/**
 * Created by czq on 2018/2/1.
 */

class MenuModel  {
    var leftIcon: Int = 0
    var text: String? = null

    constructor(leftIcon:Int,text:String){
        this.leftIcon=leftIcon
        this.text=text
    }
}
