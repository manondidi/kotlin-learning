package com.czq.kotlinlearning

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.model.MenuModel
import kotlinx.android.synthetic.main.item_left_menu.view.*



/**
 * Created by czq on 2018/2/1.
 */
class DrawerLeftMenuAdapter : RecyclerView.Adapter<DrawerLeftMenuAdapter.ViewHodler>() {

    var list: List<MenuModel>? = emptyList()
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {

        holder.setData(list!![position])
        holder.itemView.setOnClickListener({
            //do nothing
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_left_menu, parent, false)
        return DrawerLeftMenuAdapter.ViewHodler(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHodler(var view: View) : RecyclerView.ViewHolder(view) {

        fun setData(data: MenuModel) {
            view.menuText.text = data.text
            view.icon.setImageResource(data.leftIcon)

        }

    }
}
