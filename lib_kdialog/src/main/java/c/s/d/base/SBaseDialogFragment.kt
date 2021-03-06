package c.s.d.base

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.SparseArray
import android.view.*
import androidx.fragment.app.DialogFragment
import c.s.d.R
import c.s.d.base.help.DismissListener
import c.s.d.base.help.LogicListener
import c.s.d.base.help.shieldBackScreen

abstract class SBaseDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "SKBaseDialog_  "
    }


    protected var window: Window? = null
    protected var windowParams: WindowManager.LayoutParams? = null
    private var layoutView: View? = null

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (dismissCallback() != null) {
            dismissCallback()?.dismissCallback(dialog)
        }
    }

    override fun onStart() {
        super.onStart()
        windowParams?.dimAmount = getDialogOutTransparency()
        windowParams?.gravity = getGravity()
        window?.attributes = windowParams
        if (getDialogCancle()) {
            shieldBackScreen(this)
        } else {
            dialog?.setCanceledOnTouchOutside(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.K_Dialog_FULL_SCREEN);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeLayout()
        layoutView = if (getLayoutView() != null) {
            getLayoutView()
        } else {
            inflater.inflate(getLayout(), window?.findViewById(android.R.id.content), false)
        }
        setWindow()
        return layoutView
    }


    private fun setWindow() {
        windowParams = window?.attributes
        windowParams?.windowAnimations = getAnimation()
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = layoutView?.layoutParams!!
        window?.setLayout(layoutParams.width, layoutParams.height)
    }


    private fun beforeLayout() {
        window = dialog?.window
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun logic(view: View) {
        val listener = getLogiclistener()
        if (listener != null) {
            listener?.logicCallback(this, view)
        }
    }


    private fun afterLayout(view: View) {
        val views = getViews()
        if (views != null && views!!.size() > 0) {
            for (it in 0 until views!!.size()) {
                val keyAt = views.keyAt(it)
                val childView = view?.findViewById<View>(views.keyAt(it))
                if (childView != null && getClickListener() != null)
                    childView.setOnClickListener(getClickListener())
                else
                    Log.i(TAG, "Error ViewId = $keyAt")
            }
        } else {
            Log.i(TAG, "设置点击事件的View为 null")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterLayout(view)
        logic(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        var click = getClickListener()
        if (click != null) {
            click = null
        }
        if (getViews() != null) {
            getViews()!!.clear()
        }
    }


    protected open fun getGravity(): Int? {
        return Gravity.CENTER
    }

    protected open fun getLayout(): Int {
        return 0
    }

    protected open fun getLayoutView(): View? {
        return null
    }

    protected open fun getAnimation(): Int? {
        return R.style.k_dialogAnim
    }

    protected open fun getDialogCancle(): Boolean {
        return false
    }

    protected open fun getIsFullScreen(): Boolean {
        return false
    }

    protected open fun getDialogOutTransparency(): Float {
        return 0.5f
    }

    protected open fun getViews(): SparseArray<Int>? {
        return null
    }

    protected open fun getClickListener(): View.OnClickListener? {
        return null
    }

    protected open fun dismissCallback(): DismissListener? {
        return null
    }

    protected open fun getLogiclistener(): LogicListener? {
        return null
    }


}