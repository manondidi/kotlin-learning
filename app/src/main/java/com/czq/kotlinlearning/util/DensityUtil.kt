package com.czq.kotlinlearning.util

import android.content.Context

/**
 * Created by czq on 2018/1/31.
 */
class DensityUtil {

    companion object {
        fun px2dp(context: Context, pxValue: Float): Int {
            val scale = context.getResources().getDisplayMetrics().density
            return (pxValue / scale + 0.5f).toInt()
        }


        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.getResources().getDisplayMetrics().density
            return (dipValue * scale + 0.5f).toInt()
        }
    }

}
