package edgecom.tech.uilib.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edgecom.tech.uilib.R

import kotlinx.android.synthetic.main.uilib_image_tab.view.*

class ImageTab(context: Context, attrs: AttributeSet?, defAttr: Int): FrameLayout(context, attrs, defAttr) {

    constructor(context: Context): this(context, null, 0)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.uilib_image_tab, this, true)
    }

    fun setup(@DrawableRes icon: Int): ImageTab {
        tab_iv.setImageResource(icon)
        return this
    }
}