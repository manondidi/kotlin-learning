package com.czq.kotlinlearning.competition

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czq.kotlinlearning.R
import com.czq.kotlinlearning.msg.CompetitionAdapter
import com.czq.kotlinlearning.util.DensityUtil
import kotlinx.android.synthetic.main.fragment_competition.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CompetitionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CompetitionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompetitionFragment : Fragment() {

    var totalDy = 0
    var isScrollToTop=false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = CompetitionAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    val intent = Intent()
                    intent.putExtra("from","competition")
                    intent.action = "com.czq.kotlinlearning.mainTop"
                    if (isScrollToTop) {
                        intent.putExtra("toTop", true)
                        activity.sendBroadcast(intent)

                        Log.d("competition-onScrolled",""+totalDy+"    "+isScrollToTop)
                    } else  if (totalDy >DensityUtil.dp2px(activity, -60.0f)&&!isScrollToTop){
                        intent.putExtra("toTop", false)
                        adapter.lastPosition=0
                        activity.sendBroadcast(intent)

                        Log.d("competition-onScrolled",""+totalDy+"    "+isScrollToTop)
                    }

                }

            }
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var oldTotalDy=totalDy
                totalDy-=dy
                isScrollToTop=oldTotalDy>totalDy
            }

        })

    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_competition, container, false)

    }

}