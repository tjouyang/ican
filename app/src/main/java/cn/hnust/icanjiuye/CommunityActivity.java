package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

import cn.hnust.icanjiuye.entities.Community;
import cn.hnust.icanjiuye.views.MyDecoration;
import cn.hnust.icanjiuye.views.adapter.CommunityAdapter;

/**
 * Created by tjouyang on 2017/5/24.
 * 爱心社区
 */

public class CommunityActivity extends Activity {
    private SwipeRefreshLayout refreshParent;
    private RecyclerView recyclerView;
    private CommunityAdapter adapter;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        initToolbar();
        initAdapter();
        initRecyclerView();
        initRefreshInterface();
        initData();
    }

    private void initAdapter() {
        adapter = new CommunityAdapter(new ArrayList<Community>());
    }

    private void initRefreshInterface() {
        refreshParent = (SwipeRefreshLayout) findViewById(R.id.refreshParent);
        refreshParent.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refreshParent.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        refreshParent.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        refreshParent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        toolbar.setTitle("爱心社区");
    }

    private void gotoMainAndFinish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void initData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                simulate();
            }
        }, 1000);
    }

    private void simulate() {
        ArrayList<Community> data = new ArrayList<>(10);
        Community tmp;

        tmp = new Community();
        tmp.setTitle("创业有哪些政策支持？");
        tmp.setContent("营业税：残疾人员利用自己的技能为社会提供的劳务免征营业税。\n" +
                "   城市维护建设税：城市维护建设税是随同增值税、消费税、营业税附征的。所以，如果免征增值税、消费税、营业税，也就免征了城市维护建设税。\n" +
                "   个人所得税：残疾人员所得经省、自治区、直辖市人民政府批准可以减征个人所得税。\n" +
                "车船使用税。残疾人专用的特制车辆免征车船使用税。\n");
        data.add(tmp);

        tmp = new Community();
        tmp.setTitle("交友帖");
        tmp.setContent("无论生活对你怎样，都要学会勇敢地去面对，希望大家可以成为朋友，共同交流共同进步，创造更好的生活！");
        data.add(tmp);

        tmp = new Community();
        tmp.setTitle("寻找合作伙伴长期合作");
        tmp.setContent("本人35岁，男下肢腿残疾，父母年老带病，两个孩子3岁7岁，因身体缺陷，工作不好找，一直从事养鹅，本人资金有限，养殖的数量只是维持家庭，想用我这几年的研究实验，成熟养殖几年来，技术经验进出货源的渠道，急找合作人士，合作方式多种化，有意之士致电或面谈，二级一下残疾朋友均可参加养殖，本人是残疾设计的都是我们残疾人能办到的养殖设备，跟我们这些弱势群体，轻松致富，不在为别人歧视下依靠家人下生活，身残志不残，奋斗努力让他们看看咱弱势群体不比他们差，事业不都是他们的，咱们也可以，集体残疾朋友聚集创业，养殖数量投资金额不限制，本人在当地名声可佳，没人骚扰，厂地设备住宿等具备齐全，本地没有不良人士，生产财产安全，场地1000多亩地，杨树林沙地，十分利于养殖业，放心合作，鹅苗柴鸡技术饲料销售等，本人都是成熟之路，能保证养殖风险成本降低，提高出栏率利润，本人资金有限，优势的地质条件，本人成熟的技术渠道找残疾人正常人集中发展鹅柴鸡事业养殖业，请求吃苦耐劳有事业心人士长期合作，鹅是食草杂粮动物，抗病力强，生长快，肉鹅70天左右平均8斤左右，可以出栏，如市场价格大落，本人可以控制出栏时间饲养方式调整利润，保鹅风险提高利润，这是本人长期养殖经验出来的，关于详细价格成本方面有意人士面谈或致电，网络不谈，有场地或养殖户也可以合作，提供鹅苗柴鸡苗技术收鹅柴鸡，交通便利，河南安阳地区离濮鹤高速1公里，濮阳市10公里，\n" +
                "电话微信13383938851\n");
        data.add(tmp);

        adapter.refreshData(data);
        if (refreshParent.isRefreshing()){
            refreshParent.setRefreshing(false);
        }
    }

}
