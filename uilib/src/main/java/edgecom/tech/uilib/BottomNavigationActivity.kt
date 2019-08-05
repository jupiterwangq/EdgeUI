package edgecom.tech.uilib

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View

import kotlinx.android.synthetic.main.uilib_bottom_navigation.*

/**
 * 带有底部TAB的Activity
 */
abstract class BottomNavigationActivity: TitlebarActivity(), TabLayout.OnTabSelectedListener {

    private var currentFragment: Fragment? = null

    companion object {

        fun newTab(fragment: Fragment, tabTitle: String, tabView: View) : Tab {
            val tab = Tab()
            tab.fragment = fragment
            tab.tabTitle = tabTitle
            tab.tabView  = tabView
            return tab
        }

        fun newTab(fragment: Fragment, @StringRes tabTitle: Int, tabView: View): Tab {
            return newTab(fragment, tabView.context.getString(tabTitle), tabView)
        }
    }

    class Tab {

        var fragment: Fragment? = null  //关联的fragment

        var tabTitle: String    = ""    //关联的标题

        var tabView:  View?     = null  //tab的view
    }

    open fun switchTab(tab: Tab?) {
        tab?.fragment?.let {
            setTitleText(tab.tabTitle)
            switchTo(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        currentFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun switchTo(fragment: Fragment) {
        if (currentFragment != fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            currentFragment?.let { transaction.hide(it) }
            if (!fragment.isAdded) {
                transaction.add(R.id.frag_container, fragment).commit()
            } else {
                transaction.show(fragment).commit()
            }
            currentFragment = fragment
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) = Unit

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        tab?.customView?.isPressed = false
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.customView?.isPressed = true
        switchTab(tab?.tag as? Tab)
    }

    override fun getContentViewRes(): Int = R.layout.uilib_bottom_navigation

    /**
     * 获取tab列表
     */
    abstract fun getTabs(): List<Tab>

    private fun initView() {
        bottom_bar.addOnTabSelectedListener(this)
        val tabs = getTabs()
        for (tab in tabs) {
            val t = bottom_bar.newTab()
            t.customView = tab.tabView
            t.tag = tab
            bottom_bar.addTab(t)
        }
    }
}