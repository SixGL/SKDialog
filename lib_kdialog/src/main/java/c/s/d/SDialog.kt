package c.s.d

import android.os.Looper
import androidx.fragment.app.FragmentManager
import android.util.Log
import android.util.SparseArray
import android.view.View
import c.s.d.base.SBaseDialogFragment
import c.s.d.base.SController
import c.s.d.base.SController.Params
import c.s.d.base.help.DismissListener
import c.s.d.base.help.LogicListener
import java.lang.Exception


class SDialog : SBaseDialogFragment() {

    fun getDialogView(): View? {
        return view
    }

    override fun getLogiclistener(): LogicListener? {
        return c?.getLogiclistener()
    }

    override fun getLayoutView(): View? {
        return c?.getLayoutView()
    }

    override fun getLayout(): Int {
        return c?.getLayoutId()!!
    }

    override fun dismissCallback(): DismissListener? {
        return c?.dismissCallback()
    }

    override fun getDialogOutTransparency(): Float {
        return c?.getDialogOutTransparency()!!
    }

    override fun getDialogCancle(): Boolean {
        return c?.getDialogCancle()!!
    }

    override fun getIsFullScreen(): Boolean {
        return c?.getIsFullScreen()!!
    }

    override fun getViews(): SparseArray<Int>? {
        return c?.getViews()
    }

    override fun getAnimation(): Int? {
        return c?.getAnimation()
    }


    override fun getClickListener(): View.OnClickListener? {
        return c?.getClickListener()
    }

    override fun getGravity(): Int? {
        return c?.getGravity()
    }


    private var c: SController? = null

    init {
        c = SController()
    }


    class Builder {
        private var p: Params? = null
        var d: SDialog? = null

        init {
            p = Params()
        }

        fun show(): Builder {
            d = SDialog()
            p?.apply(d?.c)
            if (d?.c?.getFragmentManager() != null) {
                d?.show(d?.c?.getFragmentManager(), "sd")
            } else {
                println("请设置FragmentManager")
            }
            return this
        }

        fun dismiss(): Builder {
            try {
                d?.dismiss()
            } catch (e: Exception) {
                println("dialog catch ${Log.getStackTraceString(e)}")
                Looper.loop()
            }
            return this
        }

        /**
         * 判断Dialog 是否显示
         * */
        fun isShow(): Boolean {
            var isShow = false
            isShow = (d?.dialog != null)
            if (isShow) {
                isShow = d?.dialog!!.isShowing
            }
            return isShow
        }


        /**
         * 设置FragmentManager管理器
         * @param fragmentManager
         * */
        fun setFramentManager(fragmentManager: androidx.fragment.app.FragmentManager): Builder {
            p?.k_fragmentManager = fragmentManager
            return this
        }

        /**
         * 设置Layout
         * @param layoutId 自定义布局
         * */
        fun setContentView(layoutId: Int): Builder {
            p?.k_layoutId = layoutId
            return this
        }

        /**
         * 设置Dialog弹出框动画
         * 默认：进入从下往上
         *      退出从上往下
         *@param styleAnimation 可以指直接指定一个style 不想要动画传入0即可
         * */
        fun setAnimation(styleAnimation: Int): Builder {
            p?.k_dialogAnimation = styleAnimation
            return this
        }

        /**
         * 屏蔽触摸dialog框以外屏幕、返回键  关闭dialog
         * @param cancelable
         * */
        fun setcancelable(cancelable: Boolean): Builder {
            p?.k_cancelable = cancelable
            return this
        }

        /**
         * 设置Dialog位置
         * @param Gravity
         * */
        fun setGravity(gravity: Int): Builder {
            p?.k_dialogGravity = gravity
            return this
        }


        /**
         * 设置Dialog框以外的背景透明度
         * 默认0.5f
         * @param transparency 0 - 1f
         * */
        fun setDialogOutTransparency(transparency: Float): Builder {
            p?.k_dialogOutTransparency = transparency
            return this
        }

        /**
         * @param isFullScreen 是否全屏
         * 跟随宿主（Activity、Fragment）：宿主沉浸式，dialogy也是
         * */
        fun setIsFullScreen(isFullScreen: Boolean): Builder {
            p?.k_isFullScreen = isFullScreen
            return this
        }

        /**
         * 批量设置点击事件
         * @param viewId 可变参数
         * */
        fun setViewClick(listener: View.OnClickListener, vararg viewId: Int): Builder {
            p?.k_listener = listener
            for (it in viewId) {
                p?.mViews?.put(it, it)
            }
            return this
        }

        /**
         * dialog dismiss 回调
         * @param
         * */
        fun addDismissListener(listener: DismissListener): Builder {
            p?.k_dismisslistener = listener
            return this
        }

        /**
         * 业务逻辑回调,这里可用于dialog显示后的业务处理
         * @param
         * */
        fun addLogicListener(listener: LogicListener): Builder {
            p?.k_LogicListener = listener
            return this
        }
    }
}

