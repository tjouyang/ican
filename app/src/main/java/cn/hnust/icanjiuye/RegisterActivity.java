package cn.hnust.icanjiuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import cn.hnust.icanjiuye.fragment.RegisterBusinessFragment;
import cn.hnust.icanjiuye.fragment.RegisterChooseIdentifyFragment;
import cn.hnust.icanjiuye.fragment.RegisterPersonalFragment;
import cn.hnust.icanjiuye.utils.Paras;

/**
 * Created by tjouyang on 2017/5/22.
 * 注册
 */

public class RegisterActivity extends FragmentActivity implements RegisterChooseIdentifyFragment.IHasChooseIdentify , RegisterPersonalFragment.IRegisterBackOrNext {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void gotoMainAndFinish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        toolbar.setTitle("会员注册");

        RegisterChooseIdentifyFragment firstFragment = new RegisterChooseIdentifyFragment();
//        firstFragment.setArguments(getIntent().getExtras());
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.register_fragment_container, firstFragment);
//        transaction.addToBackStack(null);//不放栈中
//        transaction.commit();
        replaceFragment(firstFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            gotoMainAndFinish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void identifyChose(int identify) {
        changeRegisterViewByIdentify(identify);
    }

    private void changeRegisterViewByIdentify(int identify) {
        Fragment fragment;
        if (identify == Paras.personal){
            fragment = new RegisterPersonalFragment();
        } else {
            fragment = new RegisterBusinessFragment();
        }
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.register_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void previous() {
        RegisterChooseIdentifyFragment firstFragment = new RegisterChooseIdentifyFragment();
        replaceFragment(firstFragment);
    }

    @Override
    public void next() {
        Toast.makeText(this, "已提交注册信息", Toast.LENGTH_SHORT).show();
    }
}
