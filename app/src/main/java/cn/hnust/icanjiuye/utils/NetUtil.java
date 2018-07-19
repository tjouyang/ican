package cn.hnust.icanjiuye.utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import cn.hnust.icanjiuye.MainApplication;
import cn.hnust.icanjiuye.R;

/**
 * Created by tjouyang on 2017/5/21.
 * 联网工具类
 */

public class NetUtil{
//    private final String ip = R.string.path;
    public static void request(String url, JSONObject js, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        MainApplication.getHttpRequestQueue().add(new JsonObjectRequest(Request.Method.POST, url, js, listener, errorListener));
    }

}
