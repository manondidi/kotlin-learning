package com.czq.kotlinlearning.util

import android.content.Context

/**
 * Created by czq on 2018/1/31.
 */

//静态方法写法   object 修饰类名 这样里面的方法全是静态  object修饰的 类是单例
object  DensityUtil {

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    }


    fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density
        return (dipValue * scale + 0.5f).toInt()
    }


}
