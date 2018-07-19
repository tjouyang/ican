package cn.hnust.icanjiuye;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by tjouyang on 2017/5/21.
 *
 */

public class MainApplication extends Application {
    private static RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getHttpRequestQueue() {
        return mQueue;
    }
}
