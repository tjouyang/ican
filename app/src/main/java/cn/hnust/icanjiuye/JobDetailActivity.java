package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static cn.hnust.icanjiuye.utils.Paras.jobContent;
import static cn.hnust.icanjiuye.utils.Paras.jobTitle;

/**
 * Created by tjouyang on 2017/6/3.
 * 职位详情
 */

public class JobDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        initToolbar();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(jobTitle);
        String content = intent.getStringExtra(jobContent);

        TextView tv_title = (TextView) findViewById(R.id.title);
        TextView tv_content = (TextView)findViewById(R.id.content);

        tv_title.setText(title);
        tv_content.setText(content);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setTitle("职位详情");
    }


}
