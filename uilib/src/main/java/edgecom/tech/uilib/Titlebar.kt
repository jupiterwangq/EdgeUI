package edgecom.tech.uilib

import android.content.Context
import android.graphics.LightingColorFilter
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import edgecom.tech.uilib.Titlebar.Which.Back
import edgecom.tech.uilib.Titlebar.Which.Right

import kotlinx.android.synthetic.main.uilib_titlebar.view.*

/**
 * 标题栏
 */
class Titlebar constructor(context: Context, attr: AttributeSet? ) : RelativeLayout(context, attr),
      View.OnClickListener {

    private var barClickListener: OnClickTitlebar? = null

    constructor(context: Context):this(context, null)

    object Which {
        val Back  = 0
        val Right = 1
    }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.uilib_titlebar, this, true)
    }

    /**
     * 标题栏点击回调
     */
    interface OnClickTitlebar {

        /**
         * @param bar 标题栏
         * @param which 点击了哪一个
         */
        fun onClickTitlebar(bar: Titlebar, which:Int)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_back -> barClickListener?.onClickTitlebar(this, Back)
            R.id.iv_right-> barClickListener?.onClickTitlebar(this, Right)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        iv_back.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        tv_right.setOnClickListener(this)
    }

    fun setContentColor(color: Int) {
        val colorFilter = LightingColorFilter(0, color)
        iv_back.drawable?.colorFilter  = colorFilter
        iv_right.drawable?.colorFilter = colorFilter
        tv_right.setTextColor(color)
        tv_center.setTextColor(color)
    }

    fun setRightImage(@DrawableRes resId: Int) = iv_right.setImageResource(resId)

    fun setRightText(@StringRes resId: Int)  = tv_right.setText(resId)

    fun setRightText(text: CharSequence) {
        tv_right.text = text
    }

    fun setTitle(text: CharSequence) {
        tv_center.text = text
    }

    fun setRightTextColor(color: Int)   = tv_right.setTextColor(color)

    fun setTitle(@StringRes resId: Int) = tv_center.setText(resId)

    fun setTitleTextColor(color: Int)   = tv_center.setTextColor(color)

    fun setClickListener(l: OnClickTitlebar) {
        barClickListener = l
    }

}