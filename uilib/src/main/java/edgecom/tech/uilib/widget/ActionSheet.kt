package edgecom.tech.uilib.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import edgecom.tech.uilib.R
import kotlinx.android.synthetic.main.uilib_action_sheet.*

import java.lang.Exception

class ActionSheet(
    context: Context?, private val items: List<String> = listOf(), private val callback: (Int) -> Unit
    ): Dialog(context, R.style.UiLibActionSheet) {

    companion object {
        const val ID_CANCEL = 100001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uilib_action_sheet)
        initView()
    }

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (e: Exception) {

        }
    }

    private fun initView() {
        window.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.BottomDialog_Animation)
        window.attributes.width  = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes.height = WindowManager.LayoutParams.WRAP_CONTENT

        initItems()
    }

    private fun initItems() {

        items.forEach {
            val id = items.indexOf(it)
            val tv = TextView(context)
            tv.text = it
            tv.gravity = Gravity.CENTER
            tv.setTextColor(ContextCompat.getColor(context, R.color.uilib_blue_text))
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14.0f)
            tv.background = ContextCompat.getDrawable(context, R.drawable.uilib_action_sheet_frame)
            val p = LinearLayout.LayoutParams(MATCH_PARENT, context.resources.getDimension(R.dimen.uilib_action_sheet_item_height).toInt())
            action_sheet_items.addView(tv, p)

            if (id != items.size - 1) {  //加条线
                val p = LinearLayout.LayoutParams(MATCH_PARENT, context.resources.getDimension(R.dimen.uilib_action_sheet_item_sep_line).toInt())
                val line = View(context)
                line.setBackgroundColor(Color.rgb(0xf0, 0xf0, 0xf0))
                action_sheet_items.addView(line, p)
            }

            tv.setOnClickListener {
                callback(id)
                dismiss()
            }
        }

        action_sheet_cancel.setOnClickListener {
            callback(ID_CANCEL)
            dismiss()
        }

    }
}