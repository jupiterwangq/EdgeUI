package edgecom.tech.uilib

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.uilib_titlebar_activity.*

/**
 * 扩展一些比较方便的工具函数
 */
fun Activity.fullScreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.toast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

open class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(null)
    }

    /**
     * 设置沉浸式标题栏
     * @param color 标题栏颜色
     */
    open fun immersive(color: Int) {
        StatusBarUtil.setColor(this, color, Color.TRANSPARENT)
        titlebar?.setBackgroundColor(color)
    }

    /**
     * 默认的标题栏文字颜色，注意该属性也会改变左右两侧图片或文字的颜色
     */
    open fun defaultTitleTextColor(): Int = Color.WHITE

    /**
     * 切换fragment
     * @param fragment 切换到指定的fragment
     */
    open fun switchTo(fragment: Fragment) {}
}