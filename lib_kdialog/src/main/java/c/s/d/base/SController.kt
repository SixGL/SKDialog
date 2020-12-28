package c.s.d.base

import android.util.SparseArray
import android.view.Gravity
import android.view.View
import androidx.fragment.app.FragmentManager
import c.s.d.R
import c.s.d.base.help.DismissListener

import c.s.d.base.help.LogicListener

class SController {
    private var mViews: SparseArray<Int>? = null
    private var k_fragmentManager: FragmentManager? = null
    private var k_dialogOutTransparency: Float = 0.5f
    private var k_dialogGravity: Int = Gravity.NO_GRAVITY
    private var k_dialogAnimation: Int = R.style.k_dialogAnim
    private var k_cancelable: Boolean = false
    private var k_listener: View.OnClickListener? = null
    private var k_layoutId: Int = 0
    private var k_contentView: View? = null
    private var k_dismisslistener: DismissListener? = null
    private var k_LogicListener: LogicListener? = null


    fun getLogiclistener(): LogicListener? {
        return k_LogicListener
    }

    fun getLayoutId(): Int {
        return k_layoutId
    }

    fun getLayoutView(): View? {
        return k_contentView
    }

    fun getGravity(): Int {
        return k_dialogGravity
    }

    fun getFragmentManager(): FragmentManager? {
        return k_fragmentManager
    }

    fun getAnimation(): Int {
        return k_dialogAnimation
    }

    fun getDialogCancle(): Boolean {
        return k_cancelable
    }

    fun getDialogOutTransparency(): Float {
        return k_dialogOutTransparency
    }

    fun getViews(): SparseArray<Int>? {
        return mViews
    }

    fun getClickListener(): View.OnClickListener? {
        return k_listener
    }

    fun dismissCallback(): DismissListener? {
        return k_dismisslistener
    }


    class Params {

        var mViews = SparseArray<Int>()
        var k_fragmentManager: androidx.fragment.app.FragmentManager? = null
        var k_dialogOutTransparency: Float = 0.5f
        var k_dialogGravity: Int = Gravity.NO_GRAVITY
        var k_dialogAnimation: Int = R.style.k_dialogAnim
        var k_cancelable: Boolean = false
        var k_listener: View.OnClickListener? = null
        var k_layoutId: Int = 0
        var k_contentView: View? = null
        var k_dismisslistener: DismissListener? = null
        var k_LogicListener: LogicListener? = null
        fun apply(c: SController?) {
            c?.mViews = mViews
            c?.k_fragmentManager = k_fragmentManager
            c?.k_dialogOutTransparency = k_dialogOutTransparency
            c?.k_dialogGravity = k_dialogGravity
            c?.k_dialogAnimation = k_dialogAnimation
            c?.k_cancelable = k_cancelable
            c?.k_listener = k_listener
            c?.k_layoutId = k_layoutId
            c?.k_dismisslistener = k_dismisslistener
            c?.k_LogicListener = k_LogicListener
            c?.k_contentView = k_contentView
        }
    }
}