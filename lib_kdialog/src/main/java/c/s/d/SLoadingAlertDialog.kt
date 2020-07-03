package c.s.d

import android.app.Activity
import android.app.AlertDialog
import android.util.TypedValue
import android.view.LayoutInflater


class SLoadingAlertDialog {
    private var mActivity: Activity? = null
    private var mWidth: Int = 90 //dp
    private var mHeight: Int = 90
    private var mLayoutId: Int = 0
    private var mCancelable: Boolean = true
    private var mDialogOutTransparency: Float = 0.3f
    private var dialog: AlertDialog? = null


    fun getAlertDialog(): AlertDialog? {
        return dialog
    }

    fun with(mAc: Activity): SLoadingAlertDialog {
        mActivity = mAc
        return this
    }

    fun setWidth(width: Int): SLoadingAlertDialog {
        mWidth = width
        return this
    }

    fun setHeight(height: Int): SLoadingAlertDialog {
        mHeight = height
        return this
    }


    fun setContentView(layoutId: Int): SLoadingAlertDialog {
        mLayoutId = layoutId
        return this
    }

    /**
     * 设置Dialog框以外的背景透明度
     * 默认0.5f
     * @param transparency 0 - 1f
     * */
    fun setDialogOutTransparency(transparency: Float): SLoadingAlertDialog {
        mDialogOutTransparency = transparency
        return this
    }


    /**
     * 屏蔽触摸dialog框以外屏幕、返回键  关闭dialog
     * @param cancelable
     * */
    fun setCancelable(cancelable: Boolean): SLoadingAlertDialog {
        mCancelable = cancelable
        return this
    }

    fun show(): SLoadingAlertDialog {
        val inflate = LayoutInflater.from(mActivity).inflate(mLayoutId, null)
        dialog = AlertDialog.Builder(mActivity)
                .setView(inflate)
                .setCancelable(mCancelable)
                .show()

        val params = dialog?.window!!.attributes
        params.width = dp2px(mWidth.toFloat())
        params.height = dp2px(mHeight.toFloat())
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return this
    }


    fun dismiss(): SLoadingAlertDialog {
        dialog?.dismiss()
        return this
    }

    private fun dp2px(dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mActivity?.resources?.displayMetrics).toInt()
    }
}