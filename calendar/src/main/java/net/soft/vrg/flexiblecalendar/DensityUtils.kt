package net.soft.vrg.flexiblecalendar

import android.content.Context
import android.util.DisplayMetrics

class DensityUtils {

    companion object {
        fun convertPixelsToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

}