package net.soft.vrg.flexiblecalendarview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import net.soft.vrg.flexiblecalendar.CalendarDay
import net.soft.vrg.flexiblecalendar.CalendarDaySettings
import net.soft.vrg.flexiblecalendar.CustomCalendarDayCallback
import net.soft.vrg.flexiblecalendar.FlexibleCalendarView
import net.soft.vrg.flexiblecalendar.calendar_listeners.FlexibleCalendarMonthCallback
import net.soft.vrg.flexiblecalendar.calendar_listeners.OnCalendarClickListener
import net.soft.vrg.flexiblecalendar.calendar_listeners.OnCalendarLongClickListener
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnCalendarClickListener, OnCalendarLongClickListener, FlexibleCalendarMonthCallback {

    private lateinit var flexibleCalendarView: FlexibleCalendarView
    private val df = SimpleDateFormat("yyyy-MMMM-dd", Locale.getDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flexibleCalendarView = findViewById(R.id.calendar)
        flexibleCalendarView.getSettings()
                .setOnCalendarClickListener(this)
                .setOnCalendarLongClickListener(this)
                .setCalendarMonthCallback(this)

        val buttonPrev: Button = findViewById(R.id.prev)

        buttonPrev.setOnClickListener({
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, 2019)
            calendar.set(Calendar.MONTH, 6)
            calendar.set(Calendar.DAY_OF_MONTH, 27)
            flexibleCalendarView.moveToDate(Date(calendar.timeInMillis))
        })
        val buttonNext: Button = findViewById(R.id.next)
        buttonNext.setOnClickListener({
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, 2015)
            calendar.set(Calendar.MONTH, 6)
            calendar.set(Calendar.DAY_OF_MONTH, 27)
            flexibleCalendarView.moveToDate(Date(calendar.timeInMillis))
        })

        val buttonUpdate: Button = findViewById(R.id.update)
        buttonUpdate.setOnClickListener({
            flexibleCalendarView.getSettings()
                    .setOtherMonthTextStyle(FlexibleCalendarView.BOLD)
                    .setCurrentMonthBackgroundColor(Color.CYAN)
                    .updateCalendar()
        })

        flexibleCalendarView.setDateFormat(df)
        flexibleCalendarView.getDayOfWeekTextView(FlexibleCalendarView.TUESDAY).setTextColor(Color.CYAN)
        flexibleCalendarView.getDayOfWeekTextView(FlexibleCalendarView.WEDNESDAY).textSize = 20F
        flexibleCalendarView.getDayOfWeekTextView(FlexibleCalendarView.FRIDAY).setTextColor(Color.GREEN)
        flexibleCalendarView.getDayOfWeekTextView(FlexibleCalendarView.SUNDAY).setTextColor(Color.RED)
        flexibleCalendarView.getNextMonthImageButton().setImageResource(R.drawable.ic_next_button_example)
        flexibleCalendarView.weekContainer().setBackgroundColor(ContextCompat.getColor(this, R.color.colorDays))
        flexibleCalendarView.titleContainer().setBackgroundColor(ContextCompat.getColor(this, R.color.colorTitle))
    }

    override fun onCalendarDayClick(day: CalendarDay) {
        Toast.makeText(this@MainActivity, " " + df.format(day.date), Toast.LENGTH_SHORT).show()

        val today = CalendarDay()
        today.customViewCallback = object : CustomCalendarDayCallback {
            override fun getNewCustomiseView(): View {
                val imageView = ImageView(this@MainActivity)
                imageView.setImageResource(R.drawable.ic_stat_name)
                return imageView
            }
        }

        today.date = day.date

        flexibleCalendarView.getSettings().updateCalendarDay(today, true)

    }

    override fun onCalendarDayLongClick(day: CalendarDay) {
        Toast.makeText(this@MainActivity, "Long " + df.format(day.date), Toast.LENGTH_SHORT).show()

        val today = CalendarDay()
        today.date = day.date
        val vrCalendarDaySettings = CalendarDaySettings()
        vrCalendarDaySettings.dayTextStyle = FlexibleCalendarView.BOLD
        vrCalendarDaySettings.dayTextSize = 13
        vrCalendarDaySettings.dayBackgroundColor = ContextCompat.getColor(this, R.color.colorPrimary)
        vrCalendarDaySettings.dayTextColor = ContextCompat.getColor(this, R.color.colorToday)
        today.calendarDaySettings = vrCalendarDaySettings

        flexibleCalendarView.getSettings().updateCalendarDay(today, false)
    }

    override fun getCustomizeDayView(calendar: Calendar): List<CalendarDay> {

        val customDayUtils = CustomDayUtils()

        return customDayUtils.getCustomizeDayView(this)
    }

}
