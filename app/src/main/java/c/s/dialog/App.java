package c.s.dialog;

import android.app.Application;
import android.os.Looper;
import android.util.Log;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        catchExceptionAndRestartLoop();
    }
    private void catchExceptionAndRestartLoop() {
        while (true) {
            try {
                Looper.loop();
            } catch (Exception e) {
                Log.e("try_> ","-----------------------");
                e.printStackTrace();
            }
        }
    }
}
