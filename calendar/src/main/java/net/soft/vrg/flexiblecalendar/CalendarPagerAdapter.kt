package net.soft.vrg.flexiblecalendar

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import net.soft.vrg.flexiblecalendar.calendar_listeners.FlexibleCalendarUpdateListener

class CalendarPagerAdapter constructor(
        private val context: Context
        , private var mCalendarSettingWrapper: CalendarSettingWrapper
) : PagerAdapter() {

    companion object {
        var MONTH_QUANTITY = 2400

    }

    private val pos = MONTH_QUANTITY / 2
    private var calendarRecyclerView: CalendarRecyclerView? = null

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        calendarRecyclerView = CalendarRecyclerView(context, -(pos - position), mCalendarSettingWrapper)
        val view = calendarRecyclerView
        mCalendarSettingWrapper.addVrCalendarUpdateListener(calendarRecyclerView!!)
        collection.addView(view)
        return view!!
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        mCalendarSettingWrapper.removeVrCalendarUpdateListener(view as FlexibleCalendarUpdateListener)
        collection.removeView(view as LinearLayout)
    }

    override fun getCount(): Int {
        return MONTH_QUANTITY
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


}