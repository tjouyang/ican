package cn.hnust.icanjiuye.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.hnust.icanjiuye.entities.StartJobInfo;

/**
 * Created by tjouyang on 2017/6/2.
 * 查询创业
 */

public class StartJobService extends Service {
    public interface IGetData {
        void dataListener(List<StartJobInfo> datas);
    }

    public class StartJobBinder extends Binder {
        IGetData iGetData;

        public void callMethodInService(IGetData p) {
            iGetData = p;
            p.dataListener(getData());
        }
    }

    private List<StartJobInfo> getData() {
        StartJobInfo temp;
        ArrayList<StartJobInfo> array = new ArrayList<>(10);

        temp = new StartJobInfo();
        temp.setFlag("成功案例");
        temp.setTitle("探访部分残疾人上班族");
        temp.setCome("内江新闻网");
        temp.setUrl("http://www.scnjnews.com/special/2017-03/09/content_1445213.htm");
        array.add(temp);

//        temp = new StartJobInfo();
//        temp.setFlag("政策资讯");
//        temp.setTitle("助残爱心基金成立助残创业电商平台上线");
//        temp.setCome("搜狐公众平台");
//        temp.setUrl("http://mt.sohu.com/business/p/128175387_109644");
//        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("成功案例");
        temp.setTitle("残疾人西式面点培训学校 积极扶持残疾人创业、就业");
        temp.setCome("和讯新闻");
        temp.setUrl("http://news.hexun.com/2017-03-06/188382929.html");
        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("政策资讯");
        temp.setTitle("全国人大代表戴碧蓉：希望国家和社会多关爱残疾人");
        temp.setCome("红网");
        temp.setUrl("http://hn.rednet.cn/c/2017/03/08/4233140.htm");
        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("成功案例");
        temp.setTitle("残疾人创业故事：70元起家，如今身家百万");
        temp.setCome("搜狐");
        temp.setUrl("http://mt.sohu.com/20160226/n438606422.shtml");
        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("成功案例");
        temp.setTitle("轮椅上的创业英雄 励志残疾人创业故事");
        temp.setCome("");
        temp.setUrl("http://money.hexun.com/2015-01-26/172762166.html");
        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("政策资讯");
        temp.setTitle("残疾人两项补贴制度将从2016年元旦开始实施");
        temp.setCome("");
        temp.setUrl("http://ccn.people.com.cn/n/2015/0921/c366510-27611109.html");
        array.add(temp);

        temp = new StartJobInfo();
        temp.setFlag("政策资讯");
        temp.setTitle("长沙市残疾人联合会、长沙市文明办关于开展学雷锋扶残助残“善行四十佳”评选表彰活动的通知");
        temp.setCome("");
        temp.setUrl("http://www.cdpsn.org.cn//policy/dt104l46035.htm");
        array.add(temp);
        return array;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new StartJobBinder();
    }
}
