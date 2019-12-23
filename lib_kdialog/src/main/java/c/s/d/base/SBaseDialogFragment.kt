package c.s.d.base

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.util.SparseArray
import android.view.*
import c.s.d.util.DUtils
import c.s.d.R
import c.s.d.listener.DismissLitener

abstract class SBaseDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "SKBaseDialog_  "
    }


    protected var window: Window? = null
    protected var windowParams: WindowManager.LayoutParams? = null
    private var layoutView: View? = null
    protected var k_width: Int = ViewGroup.LayoutParams.MATCH_PARENT
    private fun isFullScreen(): Int {
        if (getFullScreen())
            return ViewGroup.LayoutParams.MATCH_PARENT
        else
            return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun onDismiss(dialog: DialogInterface?) {
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
            DUtils.shield_back_screen(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeLayout()
        layoutView = inflater.inflate(getLayout(), window?.findViewById(android.R.id.content), false)
        windowParams = window?.attributes
        windowParams?.windowAnimations = getAnimation()
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(k_width, isFullScreen())
        return layoutView
    }


     fun beforeLayout() {
        window = dialog.window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    abstract fun logic(view: View)
    abstract fun getLayout(): Int


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

    protected open fun getAnimation(): Int? {
        return R.style.k_dialogAnim
    }

    protected open fun getDialogCancle(): Boolean {
        return false
    }

    protected open fun getDialogOutTransparency(): Float {
        return 0.5f
    }


    protected open fun getFullScreen(): Boolean {
        return false
    }

    protected open fun getViews(): SparseArray<Int>? {
        return null
    }

    protected open fun getClickListener(): View.OnClickListener? {
        return null
    }

    protected open fun dismissCallback(): DismissLitener? {
        return null
    }


}