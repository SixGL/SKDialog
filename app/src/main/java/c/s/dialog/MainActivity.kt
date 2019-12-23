package c.s.dialog

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import c.s.d.SDialog
import c.s.d.listener.DismissLitener
import c.s.d.listener.LogicListener

class MainActivity : AppCompatActivity() {
    var activitys: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activitys = this

    }

    fun showDialog(v: View) {
        val dialog = SDialog.Builder()
                .setContentView(R.layout.dialog)// dialog 布局
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
//                                dialogs.view?.findViewById<TextView>(R.id.tvCenter)?.text = "6666"
                            }
                        }
//                        dialogs.dismiss()
                    }
                }, R.id.tvCancle, R.id.tvSure, R.id.tvCenter)// 设置dialog布局控件的点击事件
                .setcancelable(false)// 是否屏蔽蔽触摸弹出框外和返回键关闭dialog
                .setAnimation(0) // 弹出动画
                .setDialogOutTransparency(0.3f)// 弹出框外 背景透明度
                .setGravity(Gravity.BOTTOM)// dialog弹出位置
                .setFullScreen(false)// 是否全屏
                .setFramentManager(supportFragmentManager) //FragmentManager
                .addDismissListener(object : DismissLitener {
                    override fun dismissCallback(dialog: DialogInterface?) {
                        Log.e("dismissCallback", "dismissCallback")
                    }
                })// dialog关闭回调
                .addLogicListener(object : LogicListener {
                    override fun logicCallback(view: View?) {
                        view?.findViewById<TextView>(R.id.tvCancle)?.text = "沙雕"
                    }
                })// 用于dialog弹出后，处理dialog内部业务逻辑
                .show()// 弹出dialog，放置最后调用
    }
}
