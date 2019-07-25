package c.s.dialog

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import c.s.SKDialog

class MainActivity : AppCompatActivity() {
    var activitys: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activitys = this
    }

    fun showDialog(v: View) {
        var dialogs = SKDialog()
        dialogs.setContentView(R.layout.dialog)
                .setViewClick(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        when (v?.id) {
                            R.id.tvCancle -> {
                                Toast.makeText(activitys, "取消", Toast.LENGTH_SHORT).show()
                            }
                            R.id.tvSure -> {
                                Toast.makeText(activitys, "确定", Toast.LENGTH_SHORT).show()
                            }
                            R.id.tvCenter -> {
                                Toast.makeText(activitys, "美丽", Toast.LENGTH_SHORT).show()
                            }

                        }
                        dialogs.dismiss()
                    }
                }, R.id.tvCancle, R.id.tvSure, R.id.tvCenter)
                .setcancelable(false)
//                .setAnimation(0)
                .setDialogOutTransparency(0.5f)
                .setGravity(Gravity.BOTTOM)
                .setFramentManager(supportFragmentManager)
                .show()
    }
}
