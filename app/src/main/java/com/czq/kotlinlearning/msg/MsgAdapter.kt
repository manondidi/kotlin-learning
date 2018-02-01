package com.czq.kotlinlearning.msg

import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.R

/**
 * Created by czq on 2018/1/29.
 */

class MsgAdapter : RecyclerView.Adapter<MsgAdapter.ViewHodler>() {

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_msg, parent, false)
        return ViewHodler(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHodler(view: View) : RecyclerView.ViewHolder(view)


    var lastPosition: Int = 0
    override fun onViewAttachedToWindow(holder: ViewHodler?) {
        super.onViewAttachedToWindow(holder)
        if (holder?.adapterPosition as Int > lastPosition) {
            lastPosition = holder.adapterPosition
//            holder?.itemView?.translationX = 1000f//相对于原始位置右方
//            holder?.itemView?.animate()
//                    ?.translationX(0f)//设置最终效果为完全不透明，并且在原来的位置
//                    ?.setStartDelay((30 * holder.adapterPosition).toLong())//根据item的位置设置延迟时间，达到依次动画一个接一个进行的效果
//                    ?.setInterpolator(DecelerateInterpolator(0.5f))//设置动画效果为在动画开始的地方快然后慢
//                    ?.setDuration(200)
//                    ?.start()

            val spring = SpringForce(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)

            SpringAnimation(holder.itemView, DynamicAnimation.TRANSLATION_X, 0f)
                    .setSpring(spring)
                    .setStartValue(1000f)
                    .setStartVelocity(100f)
                    .start()
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHodler?) {
        super.onViewDetachedFromWindow(holder)

        holder?.itemView?.clearAnimation()
    }
}