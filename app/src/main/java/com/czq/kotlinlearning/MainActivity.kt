package com.czq.kotlinlearning

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import com.czq.kotlinlearning.attendance.AttendanceFragment
import com.czq.kotlinlearning.competition.CompetitionFragment
import com.czq.kotlinlearning.msg.MsgFragment
import com.czq.kotlinlearning.util.DensityUtil.Companion.dp2px
import com.czq.kotlinlearning.util.DensityUtil.Companion.px2dp
import com.gyf.barlibrary.ImmersionBar
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import com.yalantis.contextmenu.lib.MenuObject
import com.yalantis.contextmenu.lib.MenuParams
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    var mainTopBroadcastReceiver: BroadcastReceiver? = null
    var mImmersionBar: ImmersionBar? = null

    var oldContainHeight: Int = 0
    var mMenuDialogFragment: ContextMenuDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oldContainHeight = contain.layoutParams.height
        val menuParams = MenuParams()
        menuParams.menuObjects = getMenuObjects()
        menuParams.isClosableOutside = true
        menuParams.actionBarSize = dp2px(this@MainActivity,60.0f)
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams)

        mainTopBroadcastReceiver = (object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {

                var containLp: ViewGroup.LayoutParams = contain.layoutParams
                if (intent!!.getBooleanExtra("toTop", false) && oldContainHeight == containLp.height) {



                    var va: ValueAnimator = ValueAnimator.ofInt(oldContainHeight, dp2px(this@MainActivity, 100.0f))
                    va.duration = 200
                    va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(p0: ValueAnimator?) {
                            containLp.height = p0!!.animatedValue as Int
                            contain.layoutParams = containLp
                        }
                    })
                    va.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationEnd(p0: Animator?) {

                            realTitle.visibility = View.VISIBLE
                        }

                        override fun onAnimationRepeat(p0: Animator?) {
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                        }

                        override fun onAnimationStart(p0: Animator?) {

                            coverBg.visibility = View.VISIBLE

                        }
                    })
                    va.start()

                } else if (dp2px(this@MainActivity, 100.0f) == containLp.height) {


                    var va: ValueAnimator = ValueAnimator.ofInt(dp2px(this@MainActivity, 100.0f), oldContainHeight)
                    va.duration = 200
                    va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(p0: ValueAnimator?) {
                            containLp.height = p0!!.animatedValue as Int
                            contain.layoutParams = containLp
                        }
                    })
                    va.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationEnd(p0: Animator?) {
                            coverBg.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(p0: Animator?) {
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                        }

                        override fun onAnimationStart(p0: Animator?) {

                            realTitle.visibility = View.GONE

                        }
                    })
                    va.start()
                }

            }

        })

        val intentFilter = IntentFilter()
        intentFilter.addAction("com.czq.kotlinlearning.mainTop")
        registerReceiver(mainTopBroadcastReceiver, intentFilter)

        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.init()
        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {

                when (position) {
                    0 -> return MsgFragment()
                    1 -> return AttendanceFragment()
                    2 -> return CompetitionFragment()
                    else -> return CompetitionFragment()
                }

            }

            override fun getCount(): Int = 3

        }

        msg.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                msg.viewTreeObserver.removeOnPreDrawListener(this)
                setIndctorMargin(dp2px(this@MainActivity, 32.0f) - getScreenWidth() / 2 - msg.measuredWidth * 2)
                return true
            }
        })


        order.setOnClickListener(this)
        other.setOnClickListener(this)
        hamburg.setOnClickListener(this)
        avtar.setOnClickListener(this)

        viewPager.currentItem = 0
        realTitle.text = "消息通知"
        realTitle.visibility = View.GONE
        msg.setImageResource(R.mipmap.msg_sel)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


                msg.setImageResource(R.mipmap.msg_unsel)
                attendance.setImageResource(R.mipmap.attendance_unsel)
                competition.setImageResource(R.mipmap.competition_unsel)

                when (position) {
                    0 -> {
                        realTitle.text = "消息通知"
                        msg.setImageResource(R.mipmap.msg_sel)
                        setIndctorMargin(dp2px(this@MainActivity, 32.0f) - getScreenWidth() / 2 - msg.measuredWidth * 2)
                    }

                    1 -> {

                        realTitle.text = "考勤记录"
                        attendance.setImageResource(R.mipmap.attendance_sel)
                        setIndctorMargin(0)
                    }

                    2 -> {

                        realTitle.text = "活动赛事"
                        competition.setImageResource(R.mipmap.competition_sel)
                        setIndctorMargin(getScreenWidth() / 2 - dp2px(this@MainActivity, 32.0f) + competition.measuredWidth * 2)
                    }

                }
            }

        })
        msg.setOnClickListener(this)
        attendance.setOnClickListener(this)
        competition.setOnClickListener(this)

        drawer.setScrimColor(Color.TRANSPARENT)



    }

    private fun getMenuObjects(): MutableList<MenuObject>? {
        var list: MutableList<MenuObject> = mutableListOf()

        var close = MenuObject()
        close.resource = R.mipmap.close


        var qrCode = MenuObject("二维码")
        qrCode.resource = R.mipmap.qr_code


        var scan = MenuObject("扫一扫")
        scan.resource = R.mipmap.scan


        var askLeave = MenuObject("请假申请")
        askLeave.resource = R.mipmap.ask_leave


        var orderMeet = MenuObject("访问预约")
        orderMeet.resource = R.mipmap.order_meet

        var help = MenuObject("帮助")
        help.resource = R.mipmap.help


        list.add(close)
        list.add(qrCode)
        list.add(scan)
        list.add(askLeave)
        list.add(orderMeet)
        list.add(help)
        return list


    }


    fun getScreenWidth(): Int {
        val resources = this.resources
        val dm = resources.displayMetrics
//        val density = dm.density
        val width = dm.widthPixels
//        val height = dm.heightPixels
        return width
    }

    fun setIndctorMargin(marginLeft: Int) {
        var lp: RelativeLayout.LayoutParams = indctor.layoutParams as RelativeLayout.LayoutParams
        var va: ValueAnimator = ValueAnimator.ofInt(lp.leftMargin, marginLeft)
        va.duration = 200
        va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(p0: ValueAnimator?) {
                lp.leftMargin = p0!!.animatedValue as Int
                indctor.layoutParams = lp
            }
        })
        va.start()


    }

    override fun onClick(view: View?) {
        when (view) {
            hamburg -> {
                mMenuDialogFragment?.show(supportFragmentManager, "mMenuDialogFragment")
            }

            msg -> viewPager.currentItem = 0

            attendance -> viewPager.currentItem = 1

            competition -> viewPager.currentItem = 2

            avtar->{
                drawer.openDrawer(Gravity.LEFT)
            }
            order -> {

                order.isSelected = !order.isSelected
                other.isSelected = false
                other.setImageResource(R.mipmap.other_unsel)


                if (order.isSelected) {

                    order.setImageResource(R.mipmap.order_sel)
                    subRow1.visibility = View.VISIBLE
                    subRow2.visibility = View.INVISIBLE
                    line1.visibility = View.INVISIBLE
                    openSubRow()
                    indctorSub.visibility = View.VISIBLE

                    var subIndcotrLp: RelativeLayout.LayoutParams = indctorSub.layoutParams as RelativeLayout.LayoutParams
                    var va: ValueAnimator = ValueAnimator.ofInt(subIndcotrLp.leftMargin, 0)
                    va.duration = 200
                    va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(p0: ValueAnimator?) {
                            subIndcotrLp.leftMargin = p0!!.animatedValue as Int
                        }
                    })
                    va.start()

                } else {

                    order.setImageResource(R.mipmap.order_unsel)
                    subRow1.visibility = View.INVISIBLE
                    subRow2.visibility = View.INVISIBLE
                    line1.visibility = View.VISIBLE
                    openSubRow()
                    indctorSub.visibility = View.INVISIBLE
                }

            }

            other -> {
                other.isSelected = !other.isSelected
                order.isSelected = false
                order.setImageResource(R.mipmap.order_unsel)
                if (other.isSelected) {


                    other.setImageResource(R.mipmap.other_sel)
                    subRow2.visibility = View.VISIBLE
                    subRow1.visibility = View.INVISIBLE
                    line1.visibility = View.INVISIBLE
                    openSubRow()
                    indctorSub.visibility = View.VISIBLE

                    var subIndcotrLp: RelativeLayout.LayoutParams = indctorSub.layoutParams as RelativeLayout.LayoutParams
                    var va: ValueAnimator = ValueAnimator.ofInt(subIndcotrLp.leftMargin, getScreenWidth() / 2 - dp2px(this@MainActivity, 32.0f) + competition.measuredWidth * 2)
                    va.duration = 200
                    va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(p0: ValueAnimator?) {
                            subIndcotrLp.leftMargin = p0!!.animatedValue as Int
                        }
                    })
                    va.start()

                } else {

                    other.setImageResource(R.mipmap.other_unsel)
                    subRow1.visibility = View.INVISIBLE
                    subRow2.visibility = View.INVISIBLE
                    line1.visibility = View.VISIBLE
                    openSubRow()
                    indctorSub.visibility = View.INVISIBLE
                }
            }
        }

    }

    fun openSubRow() {


        var subLp: ViewGroup.LayoutParams = sub.layoutParams
        var containLp: ViewGroup.LayoutParams = contain.layoutParams


        if (!other.isSelected && !order.isSelected) {//关闭


            if (subLp.height == 0)
                return

            var va: ValueAnimator = ValueAnimator.ofInt(px2dp(this, subLp.height.toFloat()), 0)
            va.duration = 200
            va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(p0: ValueAnimator?) {

                    subLp.height = dp2px(this@MainActivity, 1.0f * p0!!.animatedValue as Int)
                    containLp.height = dp2px(this@MainActivity, (260.0f + p0.animatedValue as Int))
                    sub.layoutParams = subLp
                    contain.layoutParams = containLp
                }
            })
            va.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(p0: Animator?) {
                    oldContainHeight = containLp.height
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {

                }
            })
            va.start()
        } else {//打开


            if (subLp.height == 60)
                return
            indctorSub.visibility = View.VISIBLE
            var va: ValueAnimator = ValueAnimator.ofInt(px2dp(this, subLp.height.toFloat()), 60)
            va.duration = 200
            va.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                override fun onAnimationUpdate(p0: ValueAnimator?) {

                    subLp.height = dp2px(this@MainActivity, 1.0f * p0!!.animatedValue as Int)
                    containLp.height = dp2px(this@MainActivity, (260.0f + p0.animatedValue as Int))
                    sub.layoutParams = subLp
                    contain.layoutParams = containLp

                }

            })

            va.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(p0: Animator?) {
                    oldContainHeight = containLp.height
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {

                }
            })
            va.start()

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar?.destroy()
    }
}
