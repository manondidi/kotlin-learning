package com.czq.kotlinlearning.attendance

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.R
import com.czq.kotlinlearning.msg.AttendanceAdapter
import com.czq.kotlinlearning.util.DensityUtil
import kotlinx.android.synthetic.main.fragment_attendance.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AttendanceFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AttendanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AttendanceFragment : Fragment() {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AttendanceAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    val intent = Intent()
                    intent.action = "com.czq.kotlinlearning.mainTop"
                    if (getScollYDistance(recyclerView!!) >DensityUtil.dp2px(activity, 60.0f)) {
                        intent.putExtra("toTop", true)
                    } else {
                        intent.putExtra("toTop", false)
                        adapter.lastPosition=0
                    }
                    activity.sendBroadcast(intent)
                }

            }

        })

    }


    fun getScollYDistance(recyclerView:RecyclerView): Int {
        val layoutManager:LinearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        val firstVisiableChildView = layoutManager.findViewByPosition(position)
        val itemHeight = firstVisiableChildView.height
        return position * itemHeight - firstVisiableChildView.top
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_attendance, container, false)
    }


}