package net.soft.vrg.flexiblecalendar.calendar_listeners

import android.view.View
import net.soft.vrg.flexiblecalendar.CalendarDay

interface FlexibleCalendarCustomViewCallback {

    fun getCustomView(day: CalendarDay): View

}
