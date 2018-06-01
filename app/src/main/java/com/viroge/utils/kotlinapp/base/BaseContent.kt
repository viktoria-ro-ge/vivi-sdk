package com.viroge.utils.kotlinapp.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

open class BaseContent @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

}