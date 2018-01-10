package net.soft.vrg.flexiblecalendarview

import android.content.Context
import android.widget.RelativeLayout

class CustomView(context: Context) : RelativeLayout(context) {

    init {
        init()
    }

    private fun init() {

        inflate(context, R.layout.custom_view, this)

    }


}
