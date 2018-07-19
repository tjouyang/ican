package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.hnust.icanjiuye.utils.Paras;

import static cn.hnust.icanjiuye.utils.Paras.business;
import static cn.hnust.icanjiuye.utils.Paras.isLogin;
import static cn.hnust.icanjiuye.utils.Paras.name;
import static cn.hnust.icanjiuye.utils.Paras.personal;
import static cn.hnust.icanjiuye.utils.Paras.toolbarName;

/**
 * Created by tjouyang on 2017/5/21.
 * 主Activity之个人版
 */

public class MainActivity extends Activity implements View.OnClickListener{
//    private final String TAG = MainActivity.class.getSimpleName();

    TextView location;
    private View login;
    private View register;
    private View login_success_parent;
    private TextView login_success_text;
    View change;
    View job;
    View start_job;
    View train;
    View community;
    View personCenter;
    View help;
    View home_page;
    View search;

    private ViewPager promote_self;
    private int currentItem = 0; // 当前图片的索引号
    private int[] imageResId; // 图片ID
    private List<ImageView> imageViews; // 滑动的图片集合
    private ScheduledExecutorService scheduledExecutorService;
    private CustomHandler handler = new CustomHandler(this);
    static class CustomHandler extends Handler {
        WeakReference<MainActivity> mActivity;

        CustomHandler(MainActivity aFragment) {
            mActivity = new WeakReference<>(aFragment);
        }

        @Override
        public void handleMessage(Message message) {
            if (mActivity != null) {
                MainActivity theActivity = mActivity.get();
                if (theActivity != null)
                    theActivity.handleMessage(message);
            }
        }
    }
    private void handleMessage(android.os.Message msg) {
        promote_self.setCurrentItem(currentItem);// 切换当前显示的图片

    }

    /**
     * 换行切换任务
     *
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (promote_self) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.main_location:
                break;
            case R.id.main_login:
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.main_register:
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.main_change:
                intent.setClass(this, MainActivityBusiness.class);
                intent.putExtra(toolbarName, "企业版");
                startActivity(intent);
                break;
            case R.id.main_hand:
                intent.setClass(this, MainActivityBusiness.class);
                intent.putExtra(toolbarName, "手语版");
                startActivity(intent);
                break;
            case R.id.main_voice:
                intent.setClass(this, MainActivityBusiness.class);
                intent.putExtra(toolbarName, "语音版");
                startActivity(intent);
                break;
            case R.id.main_job:
                intent.setClass(this, JobActivity.class);
                startActivity(intent);
                break;
            case R.id.main_start_job:
                intent.setClass(this, StartJobActivity.class);
                startActivity(intent);
                break;
            case R.id.main_train:
                intent.setClass(this, TrainActivity.class);
                startActivity(intent);
                break;
            case R.id.main_community:
                intent.setClass(this, CommunityActivity.class);
                startActivity(intent);
                break;
            case R.id.main_service:
                intent.setClass(this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.main_start_page:
                break;
            case R.id.main_person_center:
                intent.setClass(this, PersonCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.main_search:
                intent.setClass(this, SearchActivity.class);
                startActivity(intent);
                break;
            default:break;
        }

    }

    private void initView() {
        location = (TextView) findViewById(R.id.main_location);
        promote_self = (ViewPager) findViewById(R.id.main_promote_self);
        login = findViewById(R.id.main_login);
        register = findViewById(R.id.main_register);
        login_success_parent = findViewById(R.id.main_login_success_parent);
        login_success_text = (TextView)findViewById(R.id.main_login_success_text);
        change = findViewById(R.id.main_change);
        job = findViewById(R.id.main_job);
        start_job = findViewById(R.id.main_start_job);
        train = findViewById(R.id.main_train);
        community = findViewById(R.id.main_community);
        personCenter =  findViewById(R.id.main_person_center);
        help = findViewById(R.id.main_service);
        home_page = findViewById(R.id.main_start_page);
        search = findViewById(R.id.main_search);


        imageResId = new int[] {R.mipmap.promote1, R.mipmap.promote2, R.mipmap.promote3,};
        imageViews = new ArrayList<>();
        // 初始化推广自身App图片资源
        for (int id : imageResId) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(id);
            imageView.setPadding(1, 1, 1, 1);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageViews.add(imageView);
        }
        promote_self.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageResId.length;
            }

            // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup view, int position, Object object) {
                view.removeView((View) object);
            }

            // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViews.get(position));
                return imageViews.get(position);
            }
        });

        location.setOnClickListener(this);
        personCenter.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        change.setOnClickListener(this);
        job.setOnClickListener(this);
        start_job.setOnClickListener(this);
        train.setOnClickListener(this);
        community.setOnClickListener(this);
        help.setOnClickListener(this);
        home_page.setOnClickListener(this);
        search.setOnClickListener(this);

        //新增
        findViewById(R.id.main_voice).setOnClickListener(this);
        findViewById(R.id.main_hand).setOnClickListener(this);
    }
    private void refresh(){
        boolean hasLogin = getSharedPreferences(name, MODE_PRIVATE).getBoolean(isLogin, false);
        if (hasLogin){
            login_success_parent.setVisibility(View.VISIBLE);
            login_success_text.setText("已登录");
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
        } else {
            login.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            login_success_parent.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        refresh();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(name, MODE_PRIVATE);
        int identify = sp.getInt(Paras.identify, personal);
        if (identify == business){
            startActivity(new Intent(this, MainActivityBusiness.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 2, 3, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    public void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }

}
