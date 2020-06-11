package c.s.d.listener

import android.content.DialogInterface
import android.view.View

interface DismissListener {
    fun dismissCallback(dialog: DialogInterface?)
}

interface LogicListener {

    fun logicCallback(view: View?)
}

