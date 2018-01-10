package net.soft.vrg.flexiblecalendar

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FlexibleCalendarView : LinearLayout {

    companion object {
        val NORMAL = 0
        val BOLD = 1
        val ITALIC = 2

        val MONDAY = 0
        val TUESDAY = 1
        val WEDNESDAY = 2
        val THURSDAY = 3
        val FRIDAY = 4
        val SATURDAY = 5
        val SUNDAY = 6
    }

    private lateinit var mCalendarPagerAdapter: CalendarPagerAdapter
    private lateinit var viewPager: ViewPager
    private var df: DateFormat = SimpleDateFormat("yyyy-MMMM", Locale.getDefault())
    private lateinit var tvCalendarDate: TextView
    private lateinit var mPrevious: ImageButton
    private lateinit var mNext: ImageButton
    private lateinit var mCalendarSettingWrapper: CalendarSettingWrapper
    private lateinit var mTvWeekDays: Array<TextView>

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
    }

    private fun initAttributes(attributeSet: AttributeSet?) {
        val typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FlexibleCalendarView, 0, 0)
        val settings: Settings
        try {
            var color = ContextCompat.getColor(context, R.color.colorText)
            settings = Settings()
            settings.mCurrentDayTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_day_text_color, color)
            settings.mCurrentMonthOtherDayTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_month_other_days_text_color, color)
            settings.mCurrentMonthTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_month_text_color, color)
            settings.mOtherMonthTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_other_month_text_color, color)
            settings.mChosenTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_chosen_day_text_color, Color.WHITE)

            color = ContextCompat.getColor(context, R.color.colorBackGround)
            settings.mCurrentDayBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_day_background_color, color)
            settings.mCurrentMonthOtherDayBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_month_other_days_background_color, color)
            settings.mCurrentMonthBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_current_month_background_color, color)
            settings.mOtherMonthBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_other_month_background_color, color)
            settings.mChosenBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_chosen_day_background_color, ContextCompat.getColor(context, R.color.colorText))

            settings.mCurrentDayTextStyle = typedArray.getInt(R.styleable.FlexibleCalendarView_fcv_current_day_text_style, BOLD)
            settings.mCurrentMonthOtherDayTextStyle = typedArray.getInt(R.styleable.FlexibleCalendarView_fcv_current_month_other_days_text_style, NORMAL)
            settings.mCurrentMonthTextStyle = typedArray.getInt(R.styleable.FlexibleCalendarView_fcv_current_month_text_style, NORMAL)
            settings.mOtherMonthTextStyle = typedArray.getInt(R.styleable.FlexibleCalendarView_fcv_other_month_text_style, NORMAL)
            settings.mChosenTextStyle = typedArray.getInt(R.styleable.FlexibleCalendarView_fcv_chosen_day_text_style, NORMAL)

            val currentDayBackgroundDrawable = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_current_day_background_drawable, -1)
            if (currentDayBackgroundDrawable > 0) {
                settings.mCurrentDayBackgroundDrawable = ContextCompat.getDrawable(context, currentDayBackgroundDrawable)
            }
            val currentMonthBackgroundDrawable = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_current_month_background_drawable, -1)
            if (currentMonthBackgroundDrawable > 0) {
                settings.mCurrentMonthBackgroundDrawable = ContextCompat.getDrawable(context, currentMonthBackgroundDrawable)
            }
            val otherMonthBackgroundDrawable = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_other_month_background_drawable, -1)
            if (otherMonthBackgroundDrawable > 0) {
                settings.mOtherMonthBackgroundDrawable = ContextCompat.getDrawable(context, otherMonthBackgroundDrawable)
            }
            val currentMonthOtherDayBackgroundDrawable = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_current_month_other_days_background_drawable, -1)
            if (currentMonthOtherDayBackgroundDrawable > 0) {
                settings.mCurrentMonthOtherDayBackgroundDrawable = ContextCompat.getDrawable(context, currentMonthOtherDayBackgroundDrawable)
            }
            val chosenBackgroundDrawable = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_chosen_day_background_drawable, -1)
            if (chosenBackgroundDrawable > 0) {
                settings.mChosenBackgroundDrawable = ContextCompat.getDrawable(context, chosenBackgroundDrawable)
            }
            settings.mNextMonthButton = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_next_button, -1)
            settings.mPreviousMonthButton = typedArray.getResourceId(R.styleable.FlexibleCalendarView_fcv_previous_button, -1)

            val dayTextSize = typedArray.getDimensionPixelSize(R.styleable.FlexibleCalendarView_fcv_calendar_day_text_size, -1)
            settings.mDayTextSize = if (dayTextSize != -1) DensityUtils.convertPixelsToDp(dayTextSize.toFloat(), context).toInt() else 14
            settings.mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.FlexibleCalendarView_fcv_calendar_title_text_size, 14)

            settings.mBackgroundColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_background_color, Color.BLACK)
            settings.mTitleTextColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_title_text_color, Color.BLACK)
            settings.mDayOfWeeksColor = typedArray.getColor(R.styleable.FlexibleCalendarView_fcv_week_days_color, Color.BLACK)

        } finally {
            typedArray.recycle()
        }
        setupViews(settings)
    }

    private fun setupViews(settings: Settings) {
        isClickable = true
        View.inflate(context, R.layout.calendar_view, this)
        tvCalendarDate = findViewById(R.id.tv_calendar_date)
        viewPager = findViewById(R.id.view_pager)
        mPrevious = findViewById(R.id.calendar_prev_month)
        mTvWeekDays = arrayOf(findViewById(R.id.tvMonday), findViewById(R.id.tvTuesday), findViewById(R.id.tvWednesday), findViewById(R.id.tvThursday), findViewById(R.id.tvFriday), findViewById(R.id.tvSaturday), findViewById(R.id.tvSunday))
        for (textView in mTvWeekDays) {
            textView.setTextColor(settings.mDayOfWeeksColor)
        }
        val view: View = findViewById(R.id.container)
        view.setBackgroundColor(settings.mBackgroundColor)
        mPrevious.setOnClickListener { viewPager.currentItem = viewPager.currentItem - 1 }
        mNext = findViewById(R.id.calendar_next_month)
        mNext.setOnClickListener { viewPager.currentItem = viewPager.currentItem + 1 }
        if (settings.mNextMonthButton != -1) {
            mNext.setImageResource(settings.mNextMonthButton)
        }
        if (settings.mPreviousMonthButton != -1) {
            mPrevious.setImageResource(settings.mPreviousMonthButton)
        }

        tvCalendarDate.setTextColor(settings.mTitleTextColor)
        tvCalendarDate.textSize = settings.mTitleTextSize.toFloat()
        val date = Date()

        tvCalendarDate.text = df.format(date.time)

        mCalendarSettingWrapper = CalendarSettingWrapper()
        mCalendarSettingWrapper.setSettingWrapper(settings)

        mCalendarPagerAdapter = CalendarPagerAdapter(context, mCalendarSettingWrapper)
        viewPager.adapter = mCalendarPagerAdapter
        viewPager.currentItem = CalendarPagerAdapter.MONTH_QUANTITY / 2
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                handleDateFormate()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        df = settings.mDateFormat
    }


    fun moveToDate(date: Date) {
        val currentDate = Calendar.getInstance()
        val moveTo = Calendar.getInstance()
        moveTo.timeInMillis = date.time
        var scrollTo = 0
        if (currentDate.timeInMillis > moveTo.timeInMillis) {
            while (true) {
                scrollTo--
                currentDate.add(Calendar.MONTH, -1)
                if (currentDate.get(Calendar.YEAR) == moveTo.get(Calendar.YEAR) && currentDate.get(Calendar.MONTH) == moveTo.get(Calendar.MONTH)) {
                    break
                }
            }
        } else if (currentDate.timeInMillis < moveTo.timeInMillis) {
            while (true) {
                scrollTo++
                currentDate.add(Calendar.MONTH, 1)
                if (currentDate.get(Calendar.YEAR) == moveTo.get(Calendar.YEAR) && currentDate.get(Calendar.MONTH) == moveTo.get(Calendar.MONTH)) {
                    break
                }
            }
        }
        val pos = CalendarPagerAdapter.MONTH_QUANTITY / 2 + scrollTo
        viewPager.setCurrentItem(pos)
    }

    fun setDateFormat(dateFormat: DateFormat) {
        df = dateFormat
        handleDateFormate()
    }

    private fun handleDateFormate() {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.MONTH, -(CalendarPagerAdapter.MONTH_QUANTITY / 2 - viewPager.getCurrentItem()))
        tvCalendarDate.setText(df.format(currentDate.timeInMillis))
    }

    fun getSettings(): CalendarSettingWrapper {
        return mCalendarSettingWrapper
    }

    fun getNextMonthImageButton(): ImageButton {
        return mNext
    }

    fun getPreviousMonthImageButton(): ImageButton {
        return mPrevious
    }

    fun getTitleCalendarDate(): TextView {
        return tvCalendarDate
    }

    fun titleContainer(): View {
        val view: View = findViewById(R.id.rBarTitle)
        return view
    }

    fun weekContainer(): View {
        val view: View = findViewById(R.id.calendar_days)
        return view
    }

    fun getDayOfWeekTextView(dayOfWeek: Int): TextView {
        return mTvWeekDays[dayOfWeek]
    }

    fun getCalendarContainerView(): View {
        val view: View = findViewById(R.id.container)
        return view
    }


}