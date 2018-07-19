package cn.hnust.icanjiuye.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.hnust.icanjiuye.R;

/**
 * Created by tjouyang on 2017/5/24.
 * 个人用户注册
 */

public class RegisterPersonalFragment extends Fragment implements View.OnClickListener{
    IRegisterBackOrNext callback;
    public interface IRegisterBackOrNext {
        void previous();
        void next();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_personal, container, false);
    }

    @Override
    public void onAttach(Context context) {
        try {
            callback = (IRegisterBackOrNext) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        view.findViewById(R.id.register_next).setOnClickListener(this);
        view.findViewById(R.id.register_previous).setOnClickListener(this);
        //TODO View初始化
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_next:
                if (check()) {
                    callback.next();
                }
                break;
            case R.id.register_previous: callback.previous();break;
            default:break;
        }
    }

    private boolean check(){
        //TODO check
//        if (){
//
//        }
        return true;
    }
}
