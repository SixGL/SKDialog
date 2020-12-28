package c.s.d.base.help

import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.DialogFragment

interface DismissListener {
    fun dismissCallback(dialog: DialogInterface?)
}

interface LogicListener {

    fun logicCallback(dialog: DialogFragment, view: View?)
}

/**
 * 屏蔽返回键
 */
fun shieldBackScreen(dialogFragment: DialogFragment) {
    dialogFragment.dialog?.setCanceledOnTouchOutside(false)
    dialogFragment.dialog?.setOnKeyListener(object : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
            return if (keyCode == KeyEvent.KEYCODE_BACK) true else false
        }
    })
}
