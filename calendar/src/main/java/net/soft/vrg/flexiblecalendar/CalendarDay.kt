package net.soft.vrg.flexiblecalendar

import java.util.*

class CalendarDay {

    var customViewCallback: CustomCalendarDayCallback? = null

     var date: Date? = null
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Date cannot be null")
            }
            field = value
        }

    var calendarDaySettings: CalendarDaySettings? = null

    internal var isChoosen: Boolean = false
    fun isChoosen(): Boolean {
        return isChoosen
    }



}