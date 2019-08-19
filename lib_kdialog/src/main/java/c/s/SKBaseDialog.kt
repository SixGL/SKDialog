package c.s

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.SparseArray
import android.view.*

abstract class SKBaseDialog : DialogFragment() {
    companion object {
        const val TAG = "SKBaseDialog_  "
    }

    protected var mViews = SparseArray<Int>()
    protected var k_fragmentManager: FragmentManager? = null
    protected var k_width: Int = ViewGroup.LayoutParams.MATCH_PARENT
    protected var k_height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    protected var k_fullscreen: Boolean = false

    protected var k_dialogOutTransparency: Float = 0.5f
    protected var k_dialogGravity: Int = Gravity.NO_GRAVITY
    protected var k_dialogAnimation: Int = R.style.k_dialogAnim
    protected var k_cancelable: Boolean = false
    protected var k_listener: View.OnClickListener? = null
    protected var k_layoutId: Int = 0

    protected var window: Window? = null
    protected var windowParams: WindowManager.LayoutParams? = null

    private fun isFullScreen(): Int {
        if (k_fullscreen)
            return ViewGroup.LayoutParams.MATCH_PARENT
        else
            return ViewGroup.LayoutParams.WRAP_CONTENT

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeLayout()
        var layoutView = inflater.inflate(k_layoutId, window?.findViewById(android.R.id.content), false)
        afterLayout()
        return layoutView
    }


    private fun beforeLayout() {
        window = dialog.window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun afterLayout() {
        windowParams = window?.attributes
        windowParams?.windowAnimations = k_dialogAnimation
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(k_width!!, isFullScreen())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logic()

    }

    abstract fun logic()


    override fun onStart() {
        super.onStart()
        windowParams?.dimAmount = k_dialogOutTransparency
        windowParams?.gravity = k_dialogGravity
        window?.attributes = windowParams
        if (k_cancelable!!) {
            DUtils.shield_back_screen(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (k_listener != null) {
            k_listener = null
        }
        if (mViews != null) {
            mViews.clear()
        }
    }

}