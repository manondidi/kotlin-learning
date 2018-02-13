package com.czq.kotlinlearning.util

import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.view.View

class ItemFlyInHelper {
    //静态方法写法 用companion object  包裹
    companion object {
        //列表飞入动画
        fun flyIn(itemView: View) {
            val spring = SpringForce(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            SpringAnimation(itemView, DynamicAnimation.TRANSLATION_X, 0f)
                    .setSpring(spring)
                    .setStartValue(1000f)
                    .setStartVelocity(100f)
                    .start()

        }
    }


}
