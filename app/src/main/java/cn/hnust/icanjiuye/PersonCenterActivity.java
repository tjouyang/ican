package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static cn.hnust.icanjiuye.utils.Paras.startJobUrl;
import static cn.hnust.icanjiuye.utils.Paras.isLogin;
import static cn.hnust.icanjiuye.utils.Paras.loginFail;
import static cn.hnust.icanjiuye.utils.Paras.name;

/**
 * Created by tjouyang on 2017/5/24.
 * 个人中心
 */

public class PersonCenterActivity extends Activity implements View.OnClickListener{
    View quit;
    View about;
    String aboutUlr = "http://139.199.213.120/Ican/Aboutus.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personcenter);
        initToolbar();
        initViews();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        toolbar.setTitle("个人中心");
    }

    private void initViews() {
        quit = findViewById(R.id.personCenter_quit);
        quit.setOnClickListener(this);
        about = findViewById(R.id.personCenter_about);
        about.setOnClickListener(this);
    }


    private void gotoMainAndFinish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personCenter_quit:
                getSharedPreferences(name, MODE_PRIVATE).edit().putBoolean(isLogin, loginFail).apply();
                gotoMainAndFinish();
                break;
            case R.id.personCenter_about:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra(startJobUrl, aboutUlr);
                startActivity(intent);
            default:break;


        }
    }
}
