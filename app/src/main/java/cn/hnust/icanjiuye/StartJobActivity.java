package cn.hnust.icanjiuye;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.hnust.icanjiuye.entities.StartJobInfo;
import cn.hnust.icanjiuye.service.StartJobService;
import cn.hnust.icanjiuye.views.MyDecoration;
import cn.hnust.icanjiuye.views.adapter.StartJobAdapter;

/**
 * 创业
 * Created by tjouyang on 2017/5/24.
 */

public class StartJobActivity extends Activity implements StartJobService.IGetData{
    private SwipeRefreshLayout refreshParent;
    private RecyclerView recyclerView;
    private StartJobAdapter adapter = new StartJobAdapter(new ArrayList<StartJobInfo>());
    private StartJobService.StartJobBinder myBinder;
    private StartJobConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startjob);
        initToolbar();
        initRecyclerView();
        initData();
        initRefreshInterface();
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
                if (myBinder != null){
                    myBinder.callMethodInService(StartJobActivity.this);
                } else {
                    if (conn == null) {
                        conn = new StartJobConn();
                    }
                    bindService(new Intent(StartJobActivity.this, StartJobService.class), conn, BIND_AUTO_CREATE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (conn != null)
            unbindService(conn);
        super.onDestroy();
    }

    private void initData() {
        conn = new StartJobConn();
        bindService(new Intent(StartJobActivity.this, StartJobService.class), conn, BIND_AUTO_CREATE);
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
        toolbar.setTitle("创业");
    }

    private void gotoMainAndFinish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataListener(List<StartJobInfo> datas) {
        adapter.refreshData(datas);
        if (refreshParent.isRefreshing()){
            refreshParent.setRefreshing(false);
        }
    }

    private class StartJobConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (StartJobService.StartJobBinder) iBinder;
            myBinder.callMethodInService(StartJobActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
