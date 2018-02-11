package com.czq.kotlinlearning

import android.content.Context
import android.content.Intent
import com.czq.kotlinlearning.util.DensityUtil

/**
 * Created by czq on 2018/2/11.
 */
//用于管理首页下方tab的滚动监听计算器
class HomeScrollerCalculator {

    private var isScrollToTop = false
    private var totalDy = 0
    private var lastPosition = 0

    fun caculateDistance(dy: Int) {
        var oldTotalDy = totalDy
        totalDy -= dy
        isScrollToTop = oldTotalDy > totalDy

    }

    fun dealWhenStopDrag(context: Context) {
        val intent = Intent()
        intent.action = "com.czq.kotlinlearning.mainTop"
        intent.putExtra("from", "msg")
        if (isScrollToTop) {
            intent.putExtra("toTop", true)
            context.sendBroadcast(intent)

        } else if (totalDy > DensityUtil.dp2px(context, -60.0f) && !isScrollToTop) {
            intent.putExtra("toTop", false)
            lastPosition = 0
            context.sendBroadcast(intent)
        }
    }

}