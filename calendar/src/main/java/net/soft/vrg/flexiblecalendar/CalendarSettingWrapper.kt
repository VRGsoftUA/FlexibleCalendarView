package net.soft.vrg.flexiblecalendar

import android.graphics.drawable.Drawable
import net.soft.vrg.flexiblecalendar.calendar_listeners.*
import java.util.ArrayList

class CalendarSettingWrapper constructor() {

    var onCalendarClickListener: OnCalendarClickListener? = null
    var onCalendarLongClickListener: OnCalendarLongClickListener? = null
    var calendarMonthCallback: FlexibleCalendarMonthCallback? = null
    var calendarCustomViewCallback: FlexibleCalendarCustomViewCallback? = null
    private var flexibleCalendarUpdateListeners: MutableList<FlexibleCalendarUpdateListener>? = null

    var currentDayTextColor: Int = 0
    var currentMonthTextColor: Int = 0
    var otherMonthTextColor: Int = 0
    var currentMonthOtherDayTextColor: Int = 0
    var currentDayBackgroundColor: Int = 0
    var currentMonthBackgroundColor: Int = 0
    var otherMonthBackgroundColor: Int = 0
    var currentMonthOtherDayBackgroundColor: Int = 0

    var currentDayTextStyle: Int = 0
        set(value) {
            checkStyles("CurrentDayTextStyle", value)
            field = value
        }

    var currentMonthTextStyle: Int = 0
        set(value) {
            checkStyles("CurrentMonthTextStyle", value)
            field = value
        }

    var otherMonthTextStyle: Int = 0
        set(value) {
            checkStyles("OtherMonthTextStyle", value)
            field = value
        }

    var currentMonthOtherDayTextStyle: Int = 0
        set(value) {
            checkStyles("CurrentMonthOtherDayTextStyle", value)
            field = value
        }

    var chosenTextStyle: Int = 0
        set(value) {
            checkStyles("ChosenTextStyle", value)
            field = value
        }

    var chosenBackgroundColor: Int = 0
    var chosenTextColor: Int = 0
    var dayTextSize: Int = 0

    var currentDayBackgroundDrawable: Drawable? = null
    var currentMonthBackgroundDrawable: Drawable? = null
    var otherMonthBackgroundDrawable: Drawable? = null
    var currentMonthOtherDayBackgroundDrawable: Drawable? = null
    var chosenBackgroundDrawable: Drawable? = null

    fun checkStyles(fieldName: String, value: Int) {
        if (value > 2 || value < 0)
            throw IllegalArgumentException("$fieldName can be >= 0 or <=2")
    }

    internal fun setSettingWrapper(builder: Settings) {
        onCalendarClickListener = builder.mOnCalendarClickListener
        onCalendarLongClickListener = builder.mOnCalendarLongClickListener
        currentDayTextColor = builder.mCurrentDayTextColor
        currentMonthTextColor = builder.mCurrentMonthTextColor
        otherMonthTextColor = builder.mOtherMonthTextColor
        currentMonthOtherDayTextColor = builder.mCurrentMonthOtherDayTextColor
        currentDayBackgroundColor = builder.mCurrentDayBackgroundColor
        currentMonthBackgroundColor = builder.mCurrentMonthBackgroundColor
        otherMonthBackgroundColor = builder.mOtherMonthBackgroundColor
        currentMonthOtherDayBackgroundColor = builder.mCurrentMonthOtherDayBackgroundColor
        currentDayTextStyle = builder.mCurrentDayTextStyle
        currentMonthTextStyle = builder.mCurrentMonthTextStyle
        otherMonthTextStyle = builder.mOtherMonthTextStyle
        currentMonthOtherDayTextStyle = builder.mCurrentMonthOtherDayTextStyle
        calendarMonthCallback = builder.mVRCalendarMonthCallback
        chosenBackgroundColor = builder.mChosenBackgroundColor
        chosenTextStyle = builder.mChosenTextStyle
        chosenTextColor = builder.mChosenTextColor
        currentDayBackgroundDrawable = builder.mCurrentDayBackgroundDrawable
        currentMonthBackgroundDrawable = builder.mCurrentMonthBackgroundDrawable
        otherMonthBackgroundDrawable = builder.mOtherMonthBackgroundDrawable
        currentMonthOtherDayBackgroundDrawable = builder.mCurrentMonthOtherDayBackgroundDrawable
        chosenBackgroundDrawable = builder.mChosenBackgroundDrawable
        dayTextSize = builder.mDayTextSize
    }

    internal fun addVrCalendarUpdateListener(mVrCalendarUpdateListener: FlexibleCalendarUpdateListener) {
        if (flexibleCalendarUpdateListeners == null){
            flexibleCalendarUpdateListeners = ArrayList<FlexibleCalendarUpdateListener>()
        }
        flexibleCalendarUpdateListeners?.add(mVrCalendarUpdateListener)
    }

    internal fun removeVrCalendarUpdateListener(mVrCalendarUpdateListener: FlexibleCalendarUpdateListener) {
        flexibleCalendarUpdateListeners?.remove(mVrCalendarUpdateListener)
    }

    fun setCalendarMonthCallback(callback: FlexibleCalendarMonthCallback): CalendarSettingWrapper {
        this.calendarMonthCallback = callback
        return this
    }

    fun updateCalendarDay(day: CalendarDay, hasToSelect: Boolean) {
        val listeners = flexibleCalendarUpdateListeners
        if (listeners != null) {
            for (listener in listeners) {
                listener.updateItem(day, hasToSelect)
            }
        }
    }

    fun updateCalendar() {
        val listeners = flexibleCalendarUpdateListeners
        if (listeners != null) {
            for (listener in listeners) {
                listener.updateAll()
            }
        }
    }

    fun setDayTextSize(dayTextSize: Int): CalendarSettingWrapper {
        this.dayTextSize = dayTextSize
        return this
    }

    fun setVRCalendarCustomViewCallback(calendarCustomViewCallback: FlexibleCalendarCustomViewCallback?): CalendarSettingWrapper {
        this.calendarCustomViewCallback = calendarCustomViewCallback
        return this
    }

    fun setOnCalendarClickListener(onCalendarClickListener: OnCalendarClickListener?): CalendarSettingWrapper {
        this.onCalendarClickListener = onCalendarClickListener
        return this
    }

    fun setOnCalendarLongClickListener(onCalendarLongClickListener: OnCalendarLongClickListener?): CalendarSettingWrapper {
        this.onCalendarLongClickListener = onCalendarLongClickListener
        return this
    }

    internal fun setVrCalendarUpdateListeners(calendarUpdateListeners: MutableList<FlexibleCalendarUpdateListener>?) {
        this.flexibleCalendarUpdateListeners = calendarUpdateListeners
    }

    fun setCurrentDayTextColor(currentDayTextColor: Int): CalendarSettingWrapper {
        this.currentDayTextColor = currentDayTextColor
        return this
    }

    fun setCurrentMonthTextColor(currentMonthTextColor: Int): CalendarSettingWrapper {
        this.currentMonthTextColor = currentMonthTextColor
        return this
    }

    fun setOtherMonthTextColor(otherMonthTextColor: Int): CalendarSettingWrapper {
        this.otherMonthTextColor = otherMonthTextColor
        return this
    }

    fun setCurrentMonthOtherDayTextColor(currentMonthOtherDayTextColor: Int): CalendarSettingWrapper {
        this.currentMonthOtherDayTextColor = currentMonthOtherDayTextColor
        return this
    }

    fun setCurrentDayBackgroundColor(currentDayBackgroundColor: Int): CalendarSettingWrapper {
        this.currentDayBackgroundColor = currentDayBackgroundColor
        return this
    }

    fun setCurrentMonthBackgroundColor(currentMonthBackgroundColor: Int): CalendarSettingWrapper {
        this.currentMonthBackgroundColor = currentMonthBackgroundColor
        return this
    }

    fun setOtherMonthBackgroundColor(otherMonthBackgroundColor: Int): CalendarSettingWrapper {
        this.otherMonthBackgroundColor = otherMonthBackgroundColor
        return this
    }

    fun setCurrentMonthOtherDayBackgroundColor(currentMonthOtherDayBackgroundColor: Int): CalendarSettingWrapper {
        this.currentMonthOtherDayBackgroundColor = currentMonthOtherDayBackgroundColor
        return this
    }

    fun setCurrentDayTextStyle(currentDayTextStyle: Int): CalendarSettingWrapper {
        this.currentDayTextStyle = currentDayTextStyle
        return this
    }

    fun setCurrentMonthTextStyle(currentMonthTextStyle: Int): CalendarSettingWrapper {
        this.currentMonthTextStyle = currentMonthTextStyle
        return this
    }

    fun setOtherMonthTextStyle(otherMonthTextStyle: Int): CalendarSettingWrapper {
        this.otherMonthTextStyle = otherMonthTextStyle
        return this
    }

    fun setCurrentMonthOtherDayTextStyle(currentMonthOtherDayTextStyle: Int): CalendarSettingWrapper {
        this.currentMonthOtherDayTextStyle = currentMonthOtherDayTextStyle
        return this
    }


    fun setChosenBackgroundColor(chosenBackgroundColor: Int): CalendarSettingWrapper {
        this.chosenBackgroundColor = chosenBackgroundColor
        return this
    }

    fun setChosenTextStyle(chosenTextStyle: Int): CalendarSettingWrapper {

        this.chosenTextStyle = chosenTextStyle
        return this
    }

    fun setChosenTextColor(chosenTextColor: Int): CalendarSettingWrapper {
        this.chosenTextColor = chosenTextColor
        return this
    }

    fun setCurrentDayBackgroundDrawable(currentDayBackgroundDrawable: Drawable): CalendarSettingWrapper {
        this.currentDayBackgroundDrawable = currentDayBackgroundDrawable
        return this
    }

    fun setCurrentMonthBackgroundDrawable(currentMonthBackgroundDrawable: Drawable): CalendarSettingWrapper {
        this.currentMonthBackgroundDrawable = currentMonthBackgroundDrawable
        return this
    }

    fun setOtherMonthBackgroundDrawable(otherMonthBackgroundDrawable: Drawable): CalendarSettingWrapper {
        this.otherMonthBackgroundDrawable = otherMonthBackgroundDrawable
        return this
    }

    fun setCurrentMonthOtherDayBackgroundDrawable(currentMonthOtherDayBackgroundDrawable: Drawable): CalendarSettingWrapper {
        this.currentMonthOtherDayBackgroundDrawable = currentMonthOtherDayBackgroundDrawable
        return this
    }

    fun setChosenBackgroundDrawable(chosenBackgroundDrawable: Drawable): CalendarSettingWrapper {
        this.chosenBackgroundDrawable = chosenBackgroundDrawable
        return this
    }


}