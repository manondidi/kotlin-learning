package com.czq.kotlinlearning.attendance

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.HomeScrollerCalculator
import com.czq.kotlinlearning.R
import com.czq.kotlinlearning.msg.AttendanceAdapter
import kotlinx.android.synthetic.main.fragment_attendance.*
class AttendanceFragment : Fragment() {

    val mHomeScrollerCalculator= HomeScrollerCalculator()
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AttendanceAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    mHomeScrollerCalculator.dealWhenStopDrag(activity)

                }

            }
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                mHomeScrollerCalculator.caculateDistance(dy)
            }

        })

    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_attendance, container, false)
    }


}