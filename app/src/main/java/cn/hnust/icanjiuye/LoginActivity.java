package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.hnust.icanjiuye.views.ProgressDialog;
import cn.hnust.icanjiuye.utils.StringUtil;

import static cn.hnust.icanjiuye.utils.Paras.isLogin;
import static cn.hnust.icanjiuye.utils.Paras.loginSuccess;
import static cn.hnust.icanjiuye.utils.Paras.name;

/**
 * Created by tjouyang on 2017/5/22.
 * 登录
 */

public class LoginActivity extends Activity {
    final int SUCCESS = 1;
    private Toolbar toolbar;
    private EditText userID;
    private EditText userPassword;
    private View loginBt;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private CustomHandler handler = new CustomHandler(this);
    static class CustomHandler extends Handler {
        WeakReference<LoginActivity> mActivity;

        CustomHandler(LoginActivity aFragment) {
            mActivity = new WeakReference<>(aFragment);
        }

        @Override
        public void handleMessage(Message message) {
            if (mActivity != null) {
                LoginActivity theActivity = mActivity.get();
                if (theActivity != null)
                    theActivity.updateUiByMessage(message);
            }
        }
    }

    private void updateUiByMessage(Message msg){
        switch (msg.what){
            case SUCCESS:
                SharedPreferences sp = getSharedPreferences(name, MODE_PRIVATE);
                sp.edit().putBoolean(isLogin, loginSuccess).apply();
                gotoMainAndFinish();
                break;
            default:break;
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        userID = (EditText) findViewById(R.id.login_userID);
        userPassword = (EditText) findViewById(R.id.login_userPassword);
        loginBt =  findViewById(R.id.login_button);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        toolbar.setTitle("会员登录");
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndLogin();
            }
        });
    }

    private void checkAndLogin(){
        if ( check() ){
            login();
        }
    }

    private boolean check(){
        String id = userID.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        if (StringUtil.isEmpty(id)){
            showTip("账号不能为空!");
            return false;
        }
        if (StringUtil.isEmpty(password)){
            showTip("密码不能为空!");
            return false;
        }
        if ("123".equals(id)){
            handler.obtainMessage(SUCCESS).sendToTarget();
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMsg("登录中...");
        progressDialog.show();
        return true;
    }

    private void login(){
        //TODO 登录中

    }

    private void showTip(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    private void gotoMainAndFinish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            gotoMainAndFinish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
