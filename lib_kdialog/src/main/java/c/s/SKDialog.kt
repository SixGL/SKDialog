package c.s

import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast


/**
 * Created by SixGL on 2019/07/26.
 */
class SKDialog : SKBaseDialog() {


    /**
     * 设置FragmentManager管理器
     * @param fragmentManager
     * */
    fun setFramentManager(fragmentManager: FragmentManager): SKDialog {
        k_fragmentManager = fragmentManager
        return this
    }

    /**
     * 设置Layout
     * @param layoutId 自定义布局
     * */
    fun setContentView(layoutId: Int): SKDialog {
        k_layoutId = layoutId
        return this
    }

    /**
     * 设置Dialog弹出框动画
     * 默认：进入从下往上
     *      退出从上往下
     *@param styleAnimation 可以指直接指定一个style 不想要动画传入0即可
     * */
    fun setAnimation(styleAnimation: Int): SKDialog {
        k_dialogAnimation = styleAnimation
        return this
    }

    /**
     * 屏蔽触摸dialog框以外屏幕、返回键  关闭dialog
     * @param cancelable
     * */
    fun setcancelable(cancelable: Boolean): SKDialog {
        k_cancelable = cancelable
        return this
    }

    /**
     * 设置Dialog位置
     * @param Gravity
     * */
    fun setGravity(gravity: Int): SKDialog {
        k_dialogGravity = gravity
        return this
    }

    /**
     * 设置Dialog框以外的背景透明度
     * 默认0.5f
     * @param transparency 0 - 1f
     * */
    fun setDialogOutTransparency(transparency: Float): SKDialog {
        k_dialogOutTransparency = transparency
        return this
    }


    /**
     * 批量设置点击事件
     * @param viewId 可变参数
     * */
    fun setViewClick(listener: View.OnClickListener, vararg viewId: Int): SKDialog {
        k_listener = listener
        for (it in viewId) {
            mViews.put(it, it)
        }
        return this
    }


    fun show(): SKDialog {
        if (k_fragmentManager == null)
            Toast.makeText(context, "fragmentManager 不能为null", Toast.LENGTH_SHORT).show()
        else
            showDialog()
        return this
    }
}