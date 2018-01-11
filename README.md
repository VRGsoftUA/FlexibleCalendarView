# FlexibleCalendarView
<img src="https://github.com/VRGsoftUA/FlexibleCalendarView/blob/master/1132495145.jpg" width="250">


# Usage

*For a working implementation, Have a look at the Sample Project - sample*

1. Include the library as local library project.
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
	implementation"com.github.VRGsoftUA:FlexibleCalendarView:1.0.1"
}
```
2. Include the FlexibleCalendarView widget in your layout.

	```xml
      <net.soft.vrg.flexiblecalendar.FlexibleCalendarView
        android:id="@+id/calendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:fcv_background_color="@android:color/white"
        app:fcv_calendar_day_text_size="15sp"
        app:fcv_chosen_day_background_color="@color/colorGreen"
        app:fcv_chosen_day_background_drawable="@drawable/background"
        app:fcv_current_day_text_color="@android:color/darker_gray"
        app:fcv_current_month_other_days_background_drawable="@drawable/background"
        app:fcv_current_month_other_days_text_color="@color/colorAccent"
        app:fcv_current_month_text_color="@android:color/darker_gray"
        app:fcv_next_button="@drawable/ic_next"
        app:fcv_other_month_text_color="@color/colorPrimary"
        app:fcv_previous_button="@drawable/ic_previous"
        app:fcv_title_text_color="@color/colorGreen"
        app:fcv_week_days_color="@android:color/darker_gray" />
    ```
3. You can do same with kotlin
```kotlin
         flexibleCalendarView.getSettings()
                .setOnCalendarClickListener(this)
                .setOnCalendarLongClickListener(this)
                .setCalendarMonthCallback(this)
```
    
or

 ```kotlin
        vrCalendarView.getSettings()
                       .setOnCalendarClickListener(this)
                       .setOnCalendarLongClickListener(this)
                       .setVRCalendarMonthCallback(this);
 ```

4. You can update all days by calling flexibleCalendarView.getSettings().updateCalendar();

    ```java
     flexibleCalendarView.getSettings()
                    .setOtherMonthTextStyle(FlexibleCalendarView.BOLD)
                    .setCurrentMonthBackgroundColor(Color.CYAN)
                    .updateCalendar()
    ```
    
    if <b>FlexibleCalendarView.getSettings().updateCalendar()</b> is called and you want to save some custom day
    <b>List<FlexibleCalendarView> getCustomizeDayView(Calendar calendar)</b> method should be overridden
    <b>getCustomizeDayView(Calendar calendar)</b> is return all days you need to make custom.
    With parameter calendar you can get the year and the month to return customised days from specific month
    ```kotlin
          override fun getCustomizeDayView(calendar: Calendar): List<CalendarDay> {

        return getCustomizeDayView(this)
    }
    
    
    fun getCustomizeDayView(context: Context): List<CalendarDay> {
        val vrCalendarDays = ArrayList<CalendarDay>()

        vrCalendarDays.add(getSomeDay(context))

        return vrCalendarDays
    }
    
      private fun getSomeDay(context: Context): CalendarDay {

        val someDay = CalendarDay()

        val someDaySettings = CalendarDaySettings()
        someDaySettings.dayTextColor = Color.CYAN
        someDay.calendarDaySettings = someDaySettings

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -3)
        someDay.date = cal.time
        someDay.calendarDaySettings = someDaySettings
        return someDay
    }
    ```
    You can set set whatever view you want and customise it like you want if standard customisation does not fit
    
    <b>Attention!!!</b>
    
    View <b>getNewCustomiseView()</b> should always return new View. other wise it doesn't work properly
    ```kotlin
     someDay.customViewCallback = object : CustomCalendarDayCallback {
            override fun getNewCustomiseView(): View {
                val imageView = ImageView(context)
                imageView.setImageResource(R.drawable.simple_ex)
                return imageView
            }
        }
    ```

    but if you need to update specific day it is better to call

    ```kotlin
        flexibleCalendarView.getSettings().updateCalendarDay(VrCalendarDay today, boolean hasToSelect);
    ```
    where flexibleCalendarView has settings to customise specific day - <b>hasToSelect</b> should be false than
    and hasToSelect is boolean that sets specific customisation
    not from flexibleCalendarView settings but from default settings that has attribute "chosen". Like below
    ```xml
    app:fcv_chosen_day_background_color="@color/colorGreen"
    app:fcv_chosen_day_background_drawable="@drawable/background"
    ```
    to update specific day you have to set VRCalendarDay.setDate(Date date); - it is required
    ```kotlin
       val today = CalendarDay()
        today.date = Date()
        return today
    ```

    Move to date with

    flexibleCalendarView.moveToDate(Date date);

5. There is onClick listener and onLongClick
	```kotlin
interface OnCalendarLongClickListener {
    fun onCalendarDayLongClick(day: CalendarDay)
}
    ```

   and

    ```kotlin
   interface OnCalendarClickListener {
    fun onCalendarDayClick(day: CalendarDay)
}

that returns VrCalendarDay you click on

#### Customisation
     You can add fields via xml or flexibleCalendarView or flexibleCalendarView.getSettings().
     
# Supported fields:

| Method  | Type |
| ------------- | ------------- |
| fcr_current_day_text_color | color |
| fcr_current_month_text_color | color |
| fcr_other_month_text_color | color |
| fcr_current_month_other_days_text_color | color |
| fcr_chosen_day_text_color |color |
| fcr_current_day_background_color | color |
| fcr_current_month_background_color | color |
| fcr_other_month_background_color | color |
| fcr_current_month_other_days_background_color |color |
| fcr_chosen_day_background_color | color |
| fcr_current_day_background_drawable | integer |
| fcr_current_month_background_drawable | integer |
| fcr_other_month_background_drawable" | integer |
| fcr_current_month_other_days_background_drawable | integer |
| fcr_chosen_day_background_drawable | integer |
| fcr_calendar_day_text_size | dimension |
| fcr_calendar_title_text_size | dimension |
| fcr_next_button | integer | 
| fcr_previous_button | integer |
| fcr_title_text_color | color |
| fcr_background_color | color |
| fcr_week_days_color | color |
| fcr_current_day_text_style | normal,bold,italic |
| fcr_current_month_text_style |  normal,bold,italic |
| fcr_other_month_text_style |  normal,bold,italic |
| fcr_current_month_other_days_text_style |  normal,bold,italic |
| fcr_chosen_day_text_style |  normal,bold,italic |

#### Contributing

* Contributions are always welcome
* If you want a feature and can code, feel free to fork and add the change yourself and make a pull request
