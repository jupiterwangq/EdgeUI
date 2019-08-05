package edgecom.tech.uilib.widget

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edgecom.tech.uilib.R

import kotlinx.android.synthetic.main.uilib_text_tab.view.*

/**
 * 带有图标和文字的TAB
 */
class TextTab @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defAttr: Int) : FrameLayout(context, attrs, defAttr) {

    constructor(context: Context): this(context, null, 0)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.uilib_text_tab, this, true)
    }

    /**
     * 设置标签
     * @param text 标签文本
     * @param textColor 文本的颜色
     * @param icon Tab图标
     */
    fun setup(text: String, @ColorRes textColor: Int, @DrawableRes icon: Int): TextTab {
        tab_tv.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0)
        tab_tv.text = text
        tab_tv.setTextColor(resources.getColorStateList(textColor))
        return this
    }

    fun setup(@StringRes text: Int, @ColorRes textColor: Int, @DrawableRes icon: Int): TextTab {
        return setup(context.resources.getString(text), textColor, icon)
    }
}