package net.soft.vrg.flexiblecalendar

import android.graphics.drawable.Drawable
import net.soft.vrg.flexiblecalendar.calendar_listeners.FlexibleCalendarMonthCallback
import net.soft.vrg.flexiblecalendar.calendar_listeners.OnCalendarClickListener
import net.soft.vrg.flexiblecalendar.calendar_listeners.OnCalendarLongClickListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

internal class Settings {

    var mOnCalendarClickListener: OnCalendarClickListener? = null
    var mOnCalendarLongClickListener: OnCalendarLongClickListener? = null
    var mVRCalendarMonthCallback: FlexibleCalendarMonthCallback? = null
    var mDateFormat: DateFormat = SimpleDateFormat("yyyy-MMMM", Locale.getDefault())

    var mCurrentDayTextColor: Int = 0
    var mCurrentMonthTextColor: Int = 0
    var mOtherMonthTextColor: Int = 0
    var mCurrentMonthOtherDayTextColor: Int = 0
    var mChosenTextColor: Int = 0

    var mCurrentDayBackgroundColor: Int = 0
    var mCurrentMonthBackgroundColor: Int = 0
    var mOtherMonthBackgroundColor: Int = 0
    var mCurrentMonthOtherDayBackgroundColor: Int = 0
    var mChosenBackgroundColor: Int = 0

    var mCurrentDayTextStyle: Int = 0
    var mCurrentMonthTextStyle: Int = 0
    var mOtherMonthTextStyle: Int = 0
    var mCurrentMonthOtherDayTextStyle: Int = 0
    var mChosenTextStyle: Int = 0

    var mCurrentDayBackgroundDrawable: Drawable? = null
    var mCurrentMonthBackgroundDrawable: Drawable? = null
    var mOtherMonthBackgroundDrawable: Drawable? = null
    var mCurrentMonthOtherDayBackgroundDrawable: Drawable? = null
    var mChosenBackgroundDrawable: Drawable? = null

    var mNextMonthButton: Int = 0
    var mPreviousMonthButton: Int = 0

    var mDayTextSize: Int = 0
    var mTitleTextSize: Int = 0

    var mBackgroundColor: Int = 0
    var mTitleTextColor: Int = 0

    var mDayOfWeeksColor: Int = 0

}