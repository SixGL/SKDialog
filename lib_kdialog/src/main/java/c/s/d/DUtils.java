package c.s.d;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;

public class DUtils {
    public static void shield_back_screen(DialogFragment dialogFragment){
        dialogFragment.getDialog().setCanceledOnTouchOutside(false);
        dialogFragment.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    /**
                     * 屏蔽返回键
                     * */
                    return true;
                }
                return false;
            }
        });
    }
}
