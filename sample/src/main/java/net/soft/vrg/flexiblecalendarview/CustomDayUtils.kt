package net.soft.vrg.flexiblecalendarview

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import net.soft.vrg.flexiblecalendar.CalendarDay
import net.soft.vrg.flexiblecalendar.CalendarDaySettings
import net.soft.vrg.flexiblecalendar.CustomCalendarDayCallback
import net.soft.vrg.flexiblecalendar.FlexibleCalendarView
import net.soft.vrg.flexiblecalendar.calendar_listeners.FlexibleCalendarCustomViewCallback
import java.util.*


class CustomDayUtils {

    fun getCustomizeDayView(context: Context): List<CalendarDay> {
        val vrCalendarDays = ArrayList<CalendarDay>()

        vrCalendarDays.add(getToday(context))
        vrCalendarDays.add(getSomeDay(context))
        vrCalendarDays.add(getAnotherDay(context))
        vrCalendarDays.add(getOneMoreDay(context))
        vrCalendarDays.add(getDay0(context))
        vrCalendarDays.add(getDay1(context))
        vrCalendarDays.add(getDay2(context))
        vrCalendarDays.add(getDay3(context))

        return vrCalendarDays
    }

    private fun getAnotherDay(context: Context): CalendarDay {
        val tomorrow = CalendarDay()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 2)
        val d = cal.time
        tomorrow.date = d
        val vrtomorCalendarDaySettings = CalendarDaySettings()
        vrtomorCalendarDaySettings.dayTextStyle = FlexibleCalendarView.BOLD
        vrtomorCalendarDaySettings.dayBackgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
        vrtomorCalendarDaySettings.dayTextColor = ContextCompat.getColor(context, R.color.colorYellow)
        tomorrow.calendarDaySettings = vrtomorCalendarDaySettings

        return tomorrow
    }

    private fun getOneMoreDay(context: Context): CalendarDay {
        val tomorrow = CalendarDay()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val d = cal.time
        tomorrow.date = d
        tomorrow.customViewCallback = object : CustomCalendarDayCallback {
            override fun getNewCustomiseView(): View {
                return CustomView(context)
            }
        }
        val vrtomorCalendarDaySettings = CalendarDaySettings()
        vrtomorCalendarDaySettings.dayTextStyle = FlexibleCalendarView.BOLD
        vrtomorCalendarDaySettings.dayTextSize = 21
        vrtomorCalendarDaySettings.dayTextColor = ContextCompat.getColor(context, R.color.colorDark)
        tomorrow.calendarDaySettings = vrtomorCalendarDaySettings

        return tomorrow
    }

    private fun getSomeDay(context: Context): CalendarDay {

        val someDay = CalendarDay()

        val someDaySettings = CalendarDaySettings()
        someDaySettings.dayTextColor = Color.CYAN
        someDay.calendarDaySettings = someDaySettings
        someDay.customViewCallback = object : CustomCalendarDayCallback {
            override fun getNewCustomiseView(): View {
                val imageView = ImageView(context)
                imageView.setImageResource(R.drawable.simple_ex)
                return imageView
            }
        }

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -3)
        someDay.date = cal.time
        someDay.calendarDaySettings = someDaySettings
        return someDay
    }

    private fun getToday(context: Context): CalendarDay {
        val today = CalendarDay()
        today.date = Date()

        val todaySettings = CalendarDaySettings()
        todaySettings.dayTextColor = Color.TRANSPARENT
        today.calendarDaySettings = todaySettings
        today.customViewCallback = object : CustomCalendarDayCallback {
            override fun getNewCustomiseView(): View {
                val imageView = ImageView(context)
                imageView.setImageResource(R.drawable.ic_stat_name)
                return imageView
            }
        }
        return today
    }

    private fun getDay1(context: Context): CalendarDay {
        val today = CalendarDay()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 5)
        today.date = cal.time
        val todaySettings = CalendarDaySettings()
        todaySettings.dayTextColor = Color.RED
        today.calendarDaySettings = todaySettings
        return today
    }

    private fun getDay0(context: Context): CalendarDay {
        val today = CalendarDay()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 8)
        today.date = cal.time
        val todaySettings = CalendarDaySettings()
        todaySettings.dayTextColor = Color.BLACK
        todaySettings.dayTextSize = 10
        today.calendarDaySettings = todaySettings
        return today
    }

    private fun getDay2(context: Context): CalendarDay {
        val today = CalendarDay()

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 6)
        today.date = cal.time
        val todaySettings = CalendarDaySettings()
        todaySettings.dayTextColor = Color.GREEN
        todaySettings.dayBackgroundDrawable = ContextCompat.getDrawable(context, R.drawable.background_sample)
        today.calendarDaySettings = todaySettings
        return today
    }

    private fun getDay3(context: Context): CalendarDay {
        val today = CalendarDay()

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 4)
        today.date = cal.time
        val todaySettings = CalendarDaySettings()
        todaySettings.dayTextColor = Color.BLUE
        today.calendarDaySettings = todaySettings
        return today
    }


}
