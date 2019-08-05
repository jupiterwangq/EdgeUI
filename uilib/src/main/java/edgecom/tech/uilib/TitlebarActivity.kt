package edgecom.tech.uilib

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView

import kotlinx.android.synthetic.main.uilib_titlebar_activity.*

/**
 * 标题栏activity基类，可以设置沉浸式以及标题栏控制
 */
open class TitlebarActivity : BaseActivity(), Titlebar.OnClickTitlebar {

    open var contentV: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uilib_titlebar_activity)
        init()
    }

    override fun onClickTitlebar(bar: Titlebar, which: Int) {
        if (which == Titlebar.Which.Back) {
            onTitlebarLeftClick(bar)
        } else if (which == Titlebar.Which.Right) {
            onTitlebarRightClick(bar)
        }
    }

    open fun getContentViewRes(): Int = -1

    open fun getContentView(): View? = null

    open fun getImmersiveColor(): Int = R.color.uilib_default_immersive_color

    open fun onTitlebarLeftClick(titlebar: Titlebar)  = onBackPressed()

    open fun onTitlebarRightClick(titlebar: Titlebar) = Unit

    open fun setTitlebarRightImage(@DrawableRes resId: Int) = titlebar.setRightImage(resId)

    open fun setTitlebarRightText(@StringRes resId: Int) = titlebar.setRightText(resId)

    open fun setTitlebarRightText(text: String)  = titlebar.setTitle(text)

    open fun setTitlebarRightTextColor(color: Int) = titlebar.setRightTextColor(color)

    open fun setTitleText(@StringRes resId: Int) = titlebar.setTitle(resId)

    open fun setTitleText(text: String) = titlebar.setTitle(text)

    open fun setTitleTextColor(color: Int) = titlebar.setTitleTextColor(color)

    private fun init() {
        titlebar?.setContentColor(defaultTitleTextColor())
        titlebar?.setClickListener(this)
        content_container.addView(TextView(this))
        contentV = if (getContentViewRes() > 0) {
            val inflater = LayoutInflater.from(this)
            val v = inflater.inflate(getContentViewRes(), content_container, true) as ViewGroup
            v.getChildAt(0)
        } else {
            val v = getContentView()
            content_container.addView(v, ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
            v
        }
    }
}