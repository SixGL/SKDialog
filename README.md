# SKDialog
![image](https://github.com/SixGL/SKDialog/blob/master/example.png)
![image](https://github.com/SixGL/SKDialog/blob/master/dialog.gif)
### 引用方式
#### 注意
因为库中时直接引用的项目的kotlin（目的是为了跟项目的Kotlin版本一致）
```
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version
```
所以在项目gradle中需要支持kotlin：
```
ext.kotlin_version = '1.3.31'  // 根据自己需求选择版本
    repositories {
        google()
        jcenter()
    }
```

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
``` 
```
dependencies {
	     
	     implementation 'com.github.SixGL:SKDialog:v0.2.2'
        // AndroidX 版本
        //implementation 'com.github.SixGL:SKDialog:v0.2.5'
	}
```
### 使用

新版本重构：构造者模式

请仔细观看使用说明！！！
请仔细观看使用说明！！！
请仔细观看使用说明！！！
#### 自定义布局
##### 使用方式/属性方法
```
  var dialog:SDialog.Builder?=null
  dialog = SDialog.Builder()
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
                              //  dialog?.d?.getDialogView()?.findViewById<TextView>(R.id.tvCenter)?.text = "6666"  //点击控件更新ui

                            }
                        }
//                        dialogs.d?.dismiss()
                    }
                }, R.id.tvCancle, R.id.tvSure, R.id.tvCenter)// 设置dialog布局控件的点击事件
                .setcancelable(false)// 是否屏蔽蔽触摸弹出框外和返回键关闭dialog
                .setAnimation(0) // 弹出动画
                .setDialogOutTransparency(0.3f)// 弹出框外 背景透明度
                .setGravity(Gravity.BOTTOM)// dialog弹出位置
                .setFramentManager(supportFragmentManager) //FragmentManager
                .addDismissListener(object : DismissLitener {
                    override fun dismissCallback(dialog: DialogInterface?) {
                        Log.e("dismissCallback", "dismissCallback")
                    }
                })// dialog关闭回调
                .addLogicListener(object : LogicListener {
                    override fun logicCallback(dialog: DialogFragment,view: View?) {
                        view?.findViewById<TextView>(R.id.tvCancle)?.text = "沙雕"
                    }
                })// 用于dialog弹出后，处理dialog内部业务逻辑
                .show()// 弹出dialog，放置最后调用
```
##### 如何结合ViewBinding使用
```
        // 需要自己写
        val binding = DialogBinding.inflate(layoutInflater, window?.findViewById(android.R.id.content), false)
        // 把binding.root 传入即可
        val show = SDialog.Builder().setContentView(binding.root)
                .setCancelable(false)
                .setAnimation(R.style.k_dialogAnim)
                .setDialogOutTransparency(0.3f)
                .setGravity(Gravity.BOTTOM)
                .setFramentManager(supportFragmentManager)
                .addDismissListener(object : DismissListener {
                    override fun dismissCallback(dialog: DialogInterface?) {
                        // dialog关闭回调
                    }
                })
                .addLogicListener(object : LogicListener {
                    override fun logicCallback(dialog: DialogFragment, view: View?) {
                        binding.tvCenter.text = "ViewBinding 设置的文字"
                        binding.tvCancle.setOnClickListener { dialog?.dismiss() }
                    }

                })
                .show()
```
##### 属性介绍
```
setContentView（必须）     设置布局
setFramentManager（必须）
setViewClick （非必须）       设置点击事件（支持批量设置监听）
setcancelable（非必须）    是否屏蔽返回键和触摸对话框外的屏幕 dialog关闭。true屏蔽 false 不屏蔽（默认时不屏蔽的）   
setAnimation（非必须）    Dialog的进入退出动画，默认支持 进入从下往上  退出 从上往下，如果不需要动画设置为0即可
setDialogOutTransparency（）非必须   对话框以外屏幕的透明度。0-1f。默认时0.5f
setGravity（非必须）    设置Dialog的位置
addDismissListener（非必须） dialog关闭的时的回调
addLogicListener（非必须）  用于处理dialog展示的内部业务逻辑/更新ui/获取dialog内部控件。开发者也可以直接调用getViewzi自行获取，这种方式需要使用Handler，延迟几十毫秒，更新ui才有效果
show(） 展示

isShow() // 判断Dialog是否在展示

 dialog?.d?.dismiss() // 可通过调用Builder下的d，获取dialog对象，在调用dismiss关闭
 dialog?.d?.getDialogView() // 获取布局view，调用findViewById找到对应控件



```

#### loading加载框
默认提供了两种方式
##### 1.使用SDialog.Builder(),提供的有写好的loading布局XML：R.layout.s_dialog_loading
```
    val dialog = SDialog.Builder()
                .setContentView(R.layout.s_dialog_loading)// dialog 布局
                .setAnimation(0) // 弹出动画
                .setDialogOutTransparency(0.3f)// 弹出框外 背景透明度
                .setFramentManager(supportFragmentManager) //FragmentManager
                .addDismissListener(object : DismissLitener {
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
```

##### 2.使用AlertDialog.Builder

```
   // 简易Loading
    var showAlert: SLoadingAlertDialog? = null
    fun showAlertLoadingDialog(v: View) {
        // 默认简洁的AlertDialog，不需要FragmentManager
        showAlert = SLoadingAlertDialog()
                .with(this)
                .setContentView(R.layout.s_dialog_loading)
                .setHeight(90) // dp单位
                .setWidth(90)
                .setCancelable(true)
                .setDialogOutTransparency(0.5f)
                .show()


        //getAlertDialog() 获取dialog对象
        Handler().postDelayed(Runnable {
            showAlert?.getAlertDialog()?.dismiss()
        },3000)


    }
```
