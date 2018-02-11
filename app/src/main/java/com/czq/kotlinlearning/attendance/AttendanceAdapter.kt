package com.czq.kotlinlearning.msg

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.R
import com.czq.kotlinlearning.util.ItemFlyInHelper

/**
 * Created by czq on 2018/1/29.
 */

class AttendanceAdapter : RecyclerView.Adapter<AttendanceAdapter.ViewHodler>() {

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_attendance, parent, false)
        return AttendanceAdapter.ViewHodler(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHodler(view: View) : RecyclerView.ViewHolder(view)

    var lastPosition: Int = 0
    override fun onViewAttachedToWindow(holder: AttendanceAdapter.ViewHodler?) {
        super.onViewAttachedToWindow(holder)
        if (holder?.adapterPosition as Int > lastPosition) {
            lastPosition = holder.adapterPosition

            ItemFlyInHelper.flyIn(holder.itemView)
        }
    }

    override fun onViewDetachedFromWindow(holder: AttendanceAdapter.ViewHodler?) {
        super.onViewDetachedFromWindow(holder)
        holder?.itemView?.clearAnimation()
    }
}