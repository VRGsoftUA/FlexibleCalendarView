package net.soft.vrg.flexiblecalendar.calendar_listeners

import net.soft.vrg.flexiblecalendar.CalendarDay

internal interface FlexibleCalendarUpdateListener {

    fun updateItem(day: CalendarDay, hasToSelect: Boolean)
    fun updateAll()

}