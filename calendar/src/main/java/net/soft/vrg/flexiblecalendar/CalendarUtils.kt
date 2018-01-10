package net.soft.vrg.flexiblecalendar

import android.graphics.drawable.Drawable
import java.util.*

class CalendarUtils {

    companion object {
        fun isSameDay(myDate1: Date?, myDate2: Date?): Boolean {
            val calendar1 = Calendar.getInstance()
            val calendar2 = Calendar.getInstance()
            calendar1.time = myDate1
            calendar2.time = myDate2

            return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                    calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
        }
    }

    private val calendar = Calendar.getInstance()
    private val calendarToday = Calendar.getInstance()

    fun getDaySettings(calendarMonth: Calendar?, date: Date?, mCalendarSettingWrapper: CalendarSettingWrapper, customSetting: CalendarDaySettings?): CalendarDaySettings {
        var customSettings = customSetting
        val vrCalendarDaySettings = CalendarDaySettings()
        val today = Date()

        calendar.timeInMillis = date!!.time
        calendarToday.timeInMillis = today.time

        if (customSettings == null) {
            customSettings = CalendarDaySettings()
        }

        if (calendar.get(Calendar.MONTH) == calendarToday.get(Calendar.MONTH) &&
                calendar.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_MONTH) != calendarToday.get(Calendar.DAY_OF_MONTH)) {
            setSettings(vrCalendarDaySettings, customSettings, mCalendarSettingWrapper.currentMonthTextStyle, mCalendarSettingWrapper.currentMonthBackgroundColor, mCalendarSettingWrapper.currentMonthTextColor, mCalendarSettingWrapper.currentMonthBackgroundDrawable)
        } else if (calendar.get(Calendar.MONTH) == calendarToday.get(Calendar.MONTH) &&
                calendar.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_MONTH) == calendarToday.get(Calendar.DAY_OF_MONTH)) {
            setSettings(vrCalendarDaySettings, customSettings, mCalendarSettingWrapper.currentDayTextStyle, mCalendarSettingWrapper.currentDayBackgroundColor, mCalendarSettingWrapper.currentDayTextColor, mCalendarSettingWrapper.currentDayBackgroundDrawable)
        } else if (calendar.get(Calendar.MONTH) != calendarToday.get(Calendar.MONTH) || calendar.get(Calendar.YEAR) != calendarToday.get(Calendar.YEAR)) {

            if (calendarMonth != null && calendarMonth.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                setSettings(vrCalendarDaySettings, customSettings, mCalendarSettingWrapper.otherMonthTextStyle, mCalendarSettingWrapper.otherMonthBackgroundColor, mCalendarSettingWrapper.otherMonthTextColor, mCalendarSettingWrapper.otherMonthBackgroundDrawable)
            } else {
                setSettings(vrCalendarDaySettings, customSettings, mCalendarSettingWrapper.currentMonthOtherDayTextStyle, mCalendarSettingWrapper.currentMonthOtherDayBackgroundColor, mCalendarSettingWrapper.currentMonthOtherDayTextColor, mCalendarSettingWrapper.currentMonthOtherDayBackgroundDrawable)
            }
        }

        vrCalendarDaySettings.day = calendar.get(Calendar.DAY_OF_MONTH)
        vrCalendarDaySettings.dayTextSize = if (customSettings.dayTextSize == 0) mCalendarSettingWrapper.dayTextSize else customSettings.dayTextSize

        return vrCalendarDaySettings
    }

    fun transfer(day: CalendarDay, calendarSettingWrapper: CalendarSettingWrapper, position: Int): CalendarDay {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.MONTH, position)
        val vrCalendarDay = CalendarDay()
        vrCalendarDay.isChoosen = day.isChoosen
        vrCalendarDay.date = day.date
        vrCalendarDay.customViewCallback = day.customViewCallback
        vrCalendarDay.calendarDaySettings = getDaySettings(currentDate, vrCalendarDay.date!!, calendarSettingWrapper, day.calendarDaySettings)
        return vrCalendarDay
    }

    private fun setSettings(settings: CalendarDaySettings, customSettings: CalendarDaySettings, style: Int, background: Int, text: Int, backgroundDrawable: Drawable?) {
        settings.dayBackgroundColor = if (customSettings.dayBackgroundColor == 0) background else customSettings.dayBackgroundColor
        settings.dayTextColor = if (customSettings.dayTextColor == 0) text else customSettings.dayTextColor
        settings.dayTextStyle = if (customSettings.dayTextStyle == 0) style else customSettings.dayTextStyle
        settings.dayBackgroundDrawable = if (customSettings.dayBackgroundDrawable == null) backgroundDrawable else customSettings.dayBackgroundDrawable
    }

}