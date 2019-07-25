package c.s

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.util.SparseArray
import android.view.*

abstract class SKBaseDialog : DialogFragment() {
    companion object {
        const val TAG = "SKBaseDialog_  "
    }

    var mViews = SparseArray<Int>()

    protected var k_fragmentManager: FragmentManager? = null
    protected var k_width: Int = -1
    protected var k_height: Int = -2

    protected var k_dialogOutTransparency: Float = 0.5f
    protected var k_dialogGravity: Int = Gravity.NO_GRAVITY
    protected var k_dialogAnimation: Int = R.style.k_dialogAnim
    protected var k_layoutId: Int = 0
    protected var k_cancelable: Boolean = false
    protected var k_listener: View.OnClickListener? = null

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = k_dialogOutTransparency
        windowParams?.gravity = k_dialogGravity
        window?.attributes = windowParams
        if (k_cancelable) {
            DUtils.shield_back_screen(this)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var window = dialog.window
        var layoutView = inflater.inflate(k_layoutId, window.findViewById(android.R.id.content), false)
        beforeOnCreateView(window)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view != null) {
            if (mViews != null && mViews?.size() > 0) {
                for (it in 0 until mViews.size()) {
                    val keyAt = mViews.keyAt(it)
                    Log.i(TAG, "layout View id=$keyAt")
                    val childView = view?.findViewById<View>(mViews.keyAt(it))
                    if (childView != null)
                        childView.setOnClickListener(k_listener)
                    else
                        Log.i(TAG, "Error ViewId = $keyAt")
                }
            }
        } else {
            Log.i(TAG, "View is null")
        }
    }


    private fun beforeOnCreateView(window: Window?) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.attributes?.windowAnimations = k_dialogAnimation
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(k_width, k_height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (k_listener != null) {
            k_listener = null
        }
    }


    fun showDialog() {
        if (k_fragmentManager == null) return
        show(k_fragmentManager, "SK")
    }


}

