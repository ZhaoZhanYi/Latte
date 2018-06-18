package org.demo.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;

import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.ec.main.EcBottomDelegate;
import org.demo.latte.ec.main.personal.list.ListAdapter;
import org.demo.latte.ec.main.personal.list.ListBean;
import org.demo.latte.ec.main.personal.list.ListItemType;
import org.demo.latte.ec.main.personal.order.OrderListDelegate;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PersonalCenterDelegate extends BottomItemDelegate {

    private static final String TAG = "PersonalCenterDelegate";


    @BindView(R2.id.rv_setting)
    RecyclerView mRecyclerView;

    @OnClick(R2.id.civ_avatar)
    void onAvatarClick() {
//        test();
    }

    @OnClick(R2.id.btn_allorder)
    void onAllOrderClick() {
        EcBottomDelegate delegate = getParentDelegate();
        delegate.start(new OrderListDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personalcenter;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        ListBean listBean1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setTitle("收货地址")
                .build();
        ListBean listBean2 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setTitle("系统设置")
                .build();
        ListBean listBean3 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setTitle("关于我们")
                .build();

        ArrayList<ListBean> listBeans = new ArrayList<>();
        listBeans.add(listBean1);
        listBeans.add(listBean2);
        listBeans.add(listBean3);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ListAdapter listAdapter = new ListAdapter(listBeans);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(listAdapter);

//        mRecyclerView.addItemDecoration(
//                new DividerDecoration(ContextCompat.getColor(getContext(), R.color.divider), 2));
    }

    public void test() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("http://wdclt.zj.chinamobile.com/clt/clt/getAdviseInfo.msp?n=70021795&bgType=2&sceneId=60D6F3836CFC2840D8AF9B27947F5CC8&phoneResolution=720*1280&WD_VERSION=2.2.1&WD_CHANNEL=200007&WD_UUID=862095022708652&WD_CLIENT_TYPE=04&WD_UA=meizu_mx4_android&WD_RESOLUTION=1152*1854&encrypt=c77a5b7f20e3ed699a56c5d76719773d")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.body().string());
                Logger.addLogAdapter(new LogAdapter() {
                    @Override
                    public boolean isLoggable(int priority, @Nullable String tag) {
                        return false;
                    }

                    @Override
                    public void log(int priority, @Nullable String tag, @NonNull String message) {

                    }
                });
                Logger.d("===============" + response.body().string());
            }
        });

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
