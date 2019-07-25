# SKDialog
![image](https://github.com/SixGL/SKDialog/blob/master/d.gif)

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
	     implementation 'com.github.SixGL:SKDialog:0.1.2'
	}
```
### 使用
```
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
```
### 属性介绍
```
setContentView（必须）     设置布局
setViewClick （非必须）       设置点击事件（支持批量设置监听）
setcancelable（非必须）    是否屏蔽返回键和触摸对话框外的屏幕 dialog关闭。true屏蔽 false 不屏蔽（默认时不屏蔽的）   
setAnimation（非必须）    Dialog的进入退出动画，默认支持 进入从下往上  退出 从上往下，如果不需要动画设置为0即可
setDialogOutTransparency（非必须   对话框以外屏幕的透明度。0-1f。默认时0.5f
setGravity（非必须）    设置Dialog的位置
setFramentManager（必须）
show(） 展示

//暂不支持列表

```
