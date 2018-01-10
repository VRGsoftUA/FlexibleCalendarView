package net.soft.vrg.flexiblecalendar.calendar_listeners

import net.soft.vrg.flexiblecalendar.CalendarDay
import java.util.*

interface FlexibleCalendarMonthCallback {

    fun getCustomizeDayView(calendar: Calendar): List<CalendarDay>

}