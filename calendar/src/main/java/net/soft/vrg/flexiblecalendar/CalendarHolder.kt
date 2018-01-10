package net.soft.vrg.flexiblecalendar

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.FrameLayout
import android.widget.TextView

class CalendarHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var tvDay: TextView = itemView.findViewById(R.id.tv_day)
    private var vBackground: View = itemView.findViewById(R.id.vBackground)
    private var vCustomBack: FrameLayout = itemView.findViewById(R.id.vCustomBack)


    fun setTextToTextView(style: Int, color: Int, textSize: Int) {
        tvDay.setTypeface(null, style)
        tvDay.setTextColor(color)
        tvDay.textSize = textSize.toFloat()
    }

    fun setBackgroundToView(color: Int, drawable: Drawable?) {
        itemView.setBackgroundColor(color)
        vBackground.background = drawable
    }


    fun addCustomView(newCustomiseView: View?) {
        if (newCustomiseView != null) {
            val viewParent : ViewParent? = newCustomiseView?.parent
            if (viewParent != null){
                var viewGroup  = viewParent as ViewGroup
                viewGroup.removeView(newCustomiseView)
            }
            vCustomBack.addView(newCustomiseView)
        } else {
            vCustomBack.removeAllViews()
        }
    }

}