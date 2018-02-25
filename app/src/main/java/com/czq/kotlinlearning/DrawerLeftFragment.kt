package com.czq.kotlinlearning


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.model.MenuModel
import com.czq.kotlinlearning.util.DensityUtil
import kotlinx.android.synthetic.main.drawer_left.*


/**
 * A simple [Fragment] subclass.
 */
class DrawerLeftFragment : Fragment() {

    var adapter: DrawerLeftMenuAdapter? = null


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter=DrawerLeftMenuAdapter()
        adapter?.list = listOf(
                MenuModel(R.mipmap.data, "我的资料"),
                MenuModel(R.mipmap.family, "我的家人"),
                MenuModel(R.mipmap.history_attendance, "历史考勤"),
                MenuModel(R.mipmap.join, "我的参与"),
                MenuModel(R.mipmap.health, "我的健康"),
                MenuModel(R.mipmap.my_sport, "我的运动"),
                MenuModel(R.mipmap.phone_book, "通讯录"),
                MenuModel(R.mipmap.schedule, "课程表"))
        menuRecyclerView.adapter=adapter



    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.drawer_left, container, false)
    }

}
