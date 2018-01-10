package net.soft.vrg.flexiblecalendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class CalendarAdapter constructor(
        private var mCells: MutableList<CalendarDay>
        , private var mCalendarSettingWrapper: CalendarSettingWrapper
) : RecyclerView.Adapter<CalendarHolder>() {

    private var chosen: CalendarDay? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        return CalendarHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false))
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        val vrCalendarDay = mCells[position]

        if (vrCalendarDay.customViewCallback != null) {
            val vrCalendarCustomViewCallback = vrCalendarDay.customViewCallback
            holder.addCustomView(vrCalendarCustomViewCallback?.getNewCustomiseView())
        } else {
            holder.addCustomView(null)
        }
        vrCalendarDay.customViewCallback = null
        if (vrCalendarDay.isChoosen()) {
            holder.setBackgroundToView(mCalendarSettingWrapper.chosenBackgroundColor, mCalendarSettingWrapper.chosenBackgroundDrawable)
            holder.setTextToTextView(mCalendarSettingWrapper.chosenTextStyle, mCalendarSettingWrapper.chosenTextColor, mCalendarSettingWrapper.dayTextSize)
        } else {
            holder.setBackgroundToView(vrCalendarDay.calendarDaySettings!!.dayBackgroundColor, vrCalendarDay.calendarDaySettings!!.dayBackgroundDrawable)
            holder.setTextToTextView(vrCalendarDay.calendarDaySettings!!.dayTextStyle, vrCalendarDay.calendarDaySettings!!.dayTextColor, vrCalendarDay.calendarDaySettings!!.dayTextSize)
        }
        holder.tvDay.text = vrCalendarDay.calendarDaySettings!!.day.toString()


        holder.itemView.setOnClickListener({
            if (mCalendarSettingWrapper.onCalendarClickListener != null) {
                mCalendarSettingWrapper.onCalendarClickListener?.onCalendarDayClick(vrCalendarDay)
            }
        })

        holder.itemView.setOnLongClickListener({
            if (mCalendarSettingWrapper.onCalendarLongClickListener != null) {
                mCalendarSettingWrapper.onCalendarLongClickListener?.onCalendarDayLongClick(vrCalendarDay)
            }
            true
        })
    }

    override fun getItemCount(): Int {
        return mCells.size
    }

    fun updateAll(cells: MutableList<CalendarDay>) {
        mCells = cells
        notifyDataSetChanged()
    }

    fun updateDay(day: CalendarDay, hasToSelect: Boolean) {
        for (i in mCells.indices) {

            if (chosen != null && CalendarUtils.isSameDay(mCells[i].date, chosen?.date)) {
                if (hasToSelect) {
                    mCells[i].isChoosen = false
                    notifyItemChanged(i)
                }
                break
            }
        }
        for (i in mCells.indices) {
            if (CalendarUtils.isSameDay(mCells[i].date, day.date)) {
                mCells[i] = day
                if (hasToSelect) {
                    mCells[i].isChoosen = true
                    chosen = mCells[i]
                }
                notifyItemChanged(i)
                break
            }
        }
    }

}