package net.soft.vrg.flexiblecalendar

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import net.soft.vrg.flexiblecalendar.calendar_listeners.FlexibleCalendarUpdateListener
import java.util.*

@SuppressLint("ViewConstructor")
class CalendarRecyclerView constructor(
        context: Context
        , private var mPosition: Int
        , private val mCalendarSettingWrapper: CalendarSettingWrapper) : LinearLayout(context), FlexibleCalendarUpdateListener {

    private val DAYS_COUNT = 42
    private var mAdapter: CalendarAdapter? = null
    private var vrCalendarUtils: CalendarUtils? = null

    init {
        setupViews(mPosition)
    }

    private fun setupViews(position: Int) {
        vrCalendarUtils = CalendarUtils()
        mPosition = position

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_calendar, this)
        val calendarRv: RecyclerView = view.findViewById(R.id.calendar_recycler)

        mAdapter = CalendarAdapter(getDays(), mCalendarSettingWrapper)

        calendarRv.layoutManager = GridLayoutManager(context, 7)
        calendarRv.itemAnimator = null
        calendarRv.adapter = mAdapter

    }

    override fun updateItem(day: CalendarDay, hasToSelect: Boolean) {
        if (mAdapter != null) {
            mAdapter!!.updateDay(vrCalendarUtils!!.transfer(day, mCalendarSettingWrapper, mPosition), hasToSelect)
        }
    }

    override fun updateAll() {
        if (mAdapter != null) {
            mAdapter!!.updateAll(getDays())
        }
    }

    private fun getDays(): MutableList<CalendarDay> {
        val cells = ArrayList<CalendarDay>()
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.MONTH, mPosition)
        val calendar = currentDate.clone() as Calendar

        var customDays: List<CalendarDay>? = null
        if (mCalendarSettingWrapper.calendarMonthCallback != null) {
            customDays = mCalendarSettingWrapper.calendarMonthCallback!!.getCustomizeDayView(calendar)
        }

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        var monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 2
        if (monthBeginningCell < 0) {
            monthBeginningCell = 6
        }
        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        // fill cells
        while (cells.size < DAYS_COUNT) {
            val vrCalendarDay = CalendarDay()
            vrCalendarDay.date = calendar.time
            if (customDays != null) {
                for (i in customDays.indices) {
                    if (CalendarUtils.isSameDay(customDays[i].date, vrCalendarDay.date)) {
                        vrCalendarDay.customViewCallback = customDays[i].customViewCallback
                        vrCalendarDay.calendarDaySettings = vrCalendarUtils?.getDaySettings(currentDate, vrCalendarDay.date, mCalendarSettingWrapper, customDays[i].calendarDaySettings)
                        break
                    }
                }
            }
            if (vrCalendarDay.calendarDaySettings == null) {
                vrCalendarDay.calendarDaySettings = vrCalendarUtils!!.getDaySettings(currentDate, vrCalendarDay.date, mCalendarSettingWrapper, null)
            }

            cells.add(vrCalendarDay)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return cells
    }
}