package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static cn.hnust.icanjiuye.utils.Paras.toolbarName;

/**
 * Created by tjouyang on 2017/5/22.
 * 主Activity企业版
 */

public class MainActivityBusiness extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_business);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        String str;
        Intent intent = getIntent();
        str = intent.getStringExtra(toolbarName);
        toolbar.setTitle(str);
    }

    private void gotoMainAndFinish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
