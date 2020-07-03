package c.s.dialog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Button;

import java.util.List;

public class AppUtils {

    public static void get(Context c) {
        // TODO Auto-generated method stub
        PackageManager pm = c.getPackageManager();
        List<PackageInfo> list = pm
                .getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo packageInfo : list) {
            // 获取到设备上已经安装的应用的名字,即在AndriodMainfest中的app_name。
            String appName = packageInfo.applicationInfo.loadLabel(
                    c.getPackageManager()).toString();
            // 获取到应用所在包的名字,即在AndriodMainfest中的package的值。
            String packageName = packageInfo.packageName;
            Log.i("zyn", "应用的名字:" + appName + ">>>>>" + "应用的包名字:" + packageName);
        }

    }

    public static void getLoaction(Button btn) {
        int[] array = new int[2];
        btn.getLocationOnScreen(array);
        Log.i("getLoaction", "应用的名字:" + array[0] + "---应用的包名字:" + array[1]);
        int[] array2 = new int[2];
        btn.getLocationInWindow(array2);
        Log.i("getLoaction", "应用的名字:" + array[0] + "---应用的包名字:" + array[1]);
    }
}


