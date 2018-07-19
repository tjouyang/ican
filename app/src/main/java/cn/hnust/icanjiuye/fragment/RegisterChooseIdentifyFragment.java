package cn.hnust.icanjiuye.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.hnust.icanjiuye.R;
import static cn.hnust.icanjiuye.utils.Paras.*;

/**
 * Created by tjouyang on 2017/5/24.
 * 注册第一步
 */

public class RegisterChooseIdentifyFragment extends Fragment implements View.OnClickListener{

    IHasChooseIdentify callback;
    public interface IHasChooseIdentify {
        void identifyChose(int identify);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_choose_identify, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View personal =  view.findViewById(R.id.register_button_user_personal);
        personal.setOnClickListener(this);
        View business =  view.findViewById(R.id.register_button_user_business);
        business.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (IHasChooseIdentify) context;
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_button_user_personal:
                callback.identifyChose(personal);
                break;
            case R.id.register_button_user_business:
                callback.identifyChose(business);
                break;
            default:break;
        }
    }
}
