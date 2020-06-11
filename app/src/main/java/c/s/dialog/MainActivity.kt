package c.s.dialog

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import c.s.d.SDialog
import c.s.d.SLoadingAlertDialog
import c.s.d.listener.DismissListener
import c.s.d.listener.LogicListener

class MainActivity : AppCompatActivity() {
    var activitys: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activitys = this

    }
    var dialog:SDialog.Builder?=null

    fun showDialog(v: View) {
         dialog = SDialog.Builder()
                .setContentView(R.layout.dialog)
                .setViewClick(View.OnClickListener { v ->
                    when (v?.id) {
                        R.id.tvCancle -> {
                            Toast.makeText(activitys, "取消", Toast.LENGTH_SHORT).show()
                        }
                        R.id.tvSure -> {
                            Toast.makeText(activitys, "确定", Toast.LENGTH_SHORT).show()
                        }
                        R.id.tvCenter -> {
                            Toast.makeText(activitys, "美丽", Toast.LENGTH_SHORT).show()
                            dialog?.d?.getDialogView()?.findViewById<TextView>(R.id.tvCenter)?.text = "6666"
                        }
                    }
                    //                         dialog?.d?.dismiss()
                }, R.id.tvCancle, R.id.tvSure, R.id.tvCenter)// 设置dialog布局控件的点击事件
                .setcancelable(false)// 是否屏蔽蔽触摸弹出框外和返回键关闭dialog
//                .setAnimation(0) // 弹出动画
                .setDialogOutTransparency(0.3f)// 弹出框外 背景透明度
                .setGravity(Gravity.BOTTOM)// dialog弹出位置
                .setFramentManager(supportFragmentManager) //FragmentManager
                .addDismissListener(object : DismissListener {
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

    fun showLoadingDialog(v: View) {
        val dialog = SDialog.Builder()
                .setContentView(R.layout.s_dialog_loading)// dialog 布局
                .setAnimation(0) // 弹出动画
                .setDialogOutTransparency(0.3f)// 弹出框外 背景透明度
                .setFramentManager(supportFragmentManager) //FragmentManager
                .addDismissListener(object : DismissListener {
                    override fun dismissCallback(dialog: DialogInterface?) {
                        Log.e("dismissCallback", "dismissCallback")
                    }
                })// dialog关闭回调
                .addLogicListener(object : LogicListener {
                    override fun logicCallback(view: View?) {
                        view?.findViewById<TextView>(R.id.s_tv_loading)?.text = "正在加载..."
                        view?.findViewById<ConstraintLayout>(R.id.s_ct_loading)?.setBackgroundResource(R.drawable.s_shape_loading_test)
                    }
                })// 用于dialog弹出后，处理dialog内部业务逻辑/ui的更新
                .show()// 弹出dialog，放置最后调用

    }

    // 简易Loading
    var showAlert: SLoadingAlertDialog? = null
    fun showAlertLoadingDialog(v: View) {
        // 默认简洁的AlertDialog，不需要FragmentManager
        showAlert = SLoadingAlertDialog()
                .with(this)
                .setContentView(R.layout.s_dialog_loading)
                .setHeight(90)
                .setWidth(90)
                .setCancelable(true)
                .setDialogOutTransparency(0.5f)
                .show()


        Handler().postDelayed(Runnable {
            showAlert?.getAlertDialog()?.dismiss()
        },3000)


    }
}
