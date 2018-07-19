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

import cn.hnust.icanjiuye.entities.JobInfo;
import cn.hnust.icanjiuye.views.MyDecoration;
import cn.hnust.icanjiuye.views.adapter.JobAdapter;

/**
 * Created by tjouyang on 2017/5/24.
 * 求职
 */

public class JobActivity extends Activity {
    private SwipeRefreshLayout refreshParent;
    private RecyclerView recyclerView;
    private JobAdapter adapter;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        initToolbar();
        initAdapter();
        initRecyclerView();
        initRefreshInterface();
        initData();
    }

    private void initData() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                simulate();
            }
        }, 1000);
    }

    private void simulate() {
        JobInfo tmp;
        ArrayList<JobInfo> data = new ArrayList<>();
        tmp = new JobInfo();
        tmp.setTitle("销售代表");
        tmp.setContent("职位月薪：4001-6000元/月 \n" +
                "工作地点：长沙\n" +
                "发布日期：2017-03-13\n" +
                "工作性质：全职\n" +
                "工作经验：不限\n" +
                "最低学历：大专\n" +
                "招聘人数：5人\n" +
                "职位类别：销售代表\n" +
                "【岗位职责】\n" +
                "（1）负责全省家装公司渠道开发\n" +
                "（2）家装公司关系维护\n" +
                "（3）家装公司产品推广\n" +
                "（4）提供家装公司渠道服务\n" +
                "（5）家装公司市场调研分析\n" +
                "【任职要求】\n" +
                "（1）具备独立开拓市场及客户能力，勤奋敬业\n" +
                "（2）良好交际及沟通能力，思维敏捷\n" +
                "（3）一定的应变能力及谈判能力\n" +
                "（4）熟悉家装公司开发业务，有一定家装公司资源优先\n" +
                "【公司简介】\n" +
                "   嘉宝莉化工集团股份有限公司，1999年10月28日成立，经营范围包括生产和销售：涂料及其配套使用产品，化工原料，树脂、固化剂、稀释剂和粘合剂，油墨及辅助配套材料等。\n");
        data.add(tmp);

        tmp = new JobInfo();
        tmp.setTitle("手机商城在线客服专员");
        tmp.setContent("职位月薪：2000-2999元/月\n" +
                "工作地点：可在家工作\n" +
                "发布日期：2017-04-13\n" +
                "工作性质：全职\n" +
                "工作经验：不限\n" +
                "最低学历：中专\n" +
                "招聘人数：5人\n" +
                "职位类别：客服\n" +
                "【工作职责】 \n" +
                "通过旺旺、咚咚、千牛等在线平台与客户进行沟通，为客户提供售前、售后服务，及时准确处理客户的问题，为客户提供订单查询、退换货处理等各种售后服务。接听客户热线解答疑问。 \n" +
                "【任职要求】 \n" +
                "1、普通话标准流利，电脑中文打字每分钟不低于45字； \n" +
                "2、工作积极主动，具备良好的心理素质和强烈的服务意识； \n" +
                "3、具备良好的沟通能力和团队合作精神； \n" +
                "4、能承受一定工作压力，能够接受调班制度。 \n" +
                "【薪酬福利】 \n" +
                "1、月均收入2600-3300元(基本工资+各项补贴+绩效提成+全勤奖)； \n" +
                "2、试用期即与公司签订正式劳动合同，转正即购买广州社保； \n" +
                "3、提供专业技能培训7天（期间补贴30元/天，包吃可提供住宿）； \n" +
                "4、工作满一年享有7天带薪年假，满三年则有9天。\n" +
                "【公司简介】\n" +
                "广州润宝信息科技有限公司成立于2009 年，是一家专门从事BPO（Business Process Outsourcing）" +
                "服务外包的高新技术企业和现代服务业企业。公司以丰富的客户联络中心运营经验及自主研发的业务系统" +
                "为依托，致力于满足各不同行业机构对联络中心的多元化外包需求，可为客户提供客服热线、在线客服、" +
                "电话营销、客户关怀、培训及顾问、系统集成等一站式的客户联络中心外包解决方案。\n");
        data.add(tmp);

        tmp = new JobInfo();
        tmp.setTitle("手游运营专员");
        tmp.setContent("职位月薪：3000-4000元/月\n" +
                "工作地点：上海\n" +
                "发布日期：2017-04-23\n" +
                "工作性质：全职\n" +
                "工作经验：不限\n" +
                "最低学历：本科\n" +
                "招聘人数：2人\n" +
                "职位类别：手游运营专员\n" +
                "【岗位职责】\n" +
                "1、游戏上线前与渠道的相关对接事宜；\n" +
                "2、负责完成各类合同审核、签订所有流程以及归档备案；\n" +
                "3、完成各渠道定期对账、核账等工作；\n" +
                "4、协助主管进行合作伙伴关系的维护，进行商务信息的收集、整理、分类，对项目信息时时跟踪；\n" +
                "5、负责公司产品问题的收集反馈，协助解决游戏对接及联运过程中的各类问题，保证联运工作有效地进行；\n" +
                "6、完成领导交办的其他工作。\n" +
                "【任职要求】 \n" +
                "1、对网络游戏有良好的理解度；\n" +
                "2、性格开朗，具有优秀的口头及书面表达能力，沟通协调能力；\n" +
                "3、熟练运用各种word、excel、ppt等office办公软件；\n" +
                "4、对数据敏感且态度谨慎；\n" +
                "5、忠诚可信，有责任心，对工作认真负责踏实，为人正直；\n" +
                "6、与人沟通协调能力强，领悟力高；\n" +
                "7、有团队意识，可以承担工作压力。\n" +
                "【公司简介】\n" +
                "上海淘米网络科技有限公司（以下简称“淘米”）成立于2007年10月8日，目前是中国领先的家庭娱乐公司，致力于打造“国民级动漫品牌”，业务涵盖游戏、影视、版权和乐园四大领域。\n" +
                "目前，淘米已成功打造“赛尔号”、“摩尔庄园”、“小花仙”三大品牌，并以此为核心依托电影、电视、PC、移动四块屏幕，在游戏、影视、版权和乐园四大领域展开了拓展和尝试，具体业务包括游戏、动画片、电影、图书、品牌授权等。作为国内儿童在线游戏开拓者，淘米目前在线注册用户超5.3亿；动画片和电影具有强大的票房和收视号召力；授权合作的产品累计零售额已超13亿。\n" +
                "未来，淘米将向着打造“国民级动漫品牌”的目标继续探索和拓展，秉承“将心注入、追求极致”的价值观，致力于为家庭创造独特、美妙、高品质的娱乐体验，成为最受家庭喜爱的娱乐公司。\n");
        data.add(tmp);

        tmp = new JobInfo();
        tmp.setTitle("湖南区域业务员");
        tmp.setContent("职位月薪：6000-7999元/月\n" +
                "工作地点：长沙\n" +
                "发布日期：2017-05-08\n" +
                "工作性质：全职\n" +
                "工作经验：不限\n" +
                "最低学历：大专\n" +
                "招聘人数：2人\n" +
                "职位类别：湖南区域业务员\n" +
                "【岗位职责】\n" +
                "负责科利隆公司产品在湖南省的销售业务，包括渠道的拓展，产品推广、客户的开发与管理，销售日常事务的处理等。\n" +
                "【任职要求】\n" +
                "1.30岁以下，大专及以上学历，农学、植物保护、化学相关等专业优先  \n" +
                "2.1-3年农药行业销售工作经验，有大型农化企业从业经验者优先，任职期间销售表现良好；  \n" +
                "3.具备扎实的农药植保专业基础，还须掌握市场营销、产品、农药行业等基础知识等；  \n" +
                "4.具备较强的沟通协调、市场开拓、谈判能力；  \n" +
                "5.必须具有诚信、团队协作精神、抗压能力、有责任心。\n" +
                "【公司简介】\n" +
                "本公司系广东中迅农科股份有限公司骨干企业之一，座落于四川省成都市青白江区化工产业园区内，项目占地150亩，总投资1.8亿元。是集农药、药肥、颗粒剂的研发、生产、销售及植保技术推广为一体的专业化企业。公司拥有自主技术研发中心，拥有一批技术骨干及技术专家，研究领域涵盖了植物保护系统的各个学科，具有较强的新剂型及自主创新品种的开发能力，目前在广东惠州、四川成都、湖南岳阳、云南昆明等地设有生产基地及子公司、分公司、控股公司。\n");
        data.add(tmp);

        adapter.refreshData(data);
        if (refreshParent.isRefreshing()){
            refreshParent.setRefreshing(false);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainAndFinish();
            }
        });
        toolbar.setTitle("求职信息");
    }

    private void initAdapter() {
        adapter = new JobAdapter(new ArrayList<JobInfo>());
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

    private void gotoMainAndFinish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
