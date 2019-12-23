package c.s.d.listener

import android.content.DialogInterface

interface DismissLitener {
    fun dismissCallback(dialog: DialogInterface?)
}