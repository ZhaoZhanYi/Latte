package org.demo.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_goods_list)
    RecyclerView mRecyclerView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_orderlist;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.setAdapter();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        requestOrderList();
    }


    public void requestOrderList() {
        RestClient.builder()
                .url("http://127.0.0.1/order_list")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject orderData = JSONObject.parseObject(response);
                        JSONArray dataArray = orderData.getJSONArray("data");
                        ArrayList<OrderBean> orderBeans = new ArrayList<>();
                        OrderBean orderBean = null;
                        for (int i = 0; i < dataArray.size(); i++) {
                            JSONObject itemObj = dataArray.getJSONObject(i);
                            String title = itemObj.getString("title");
                            String price = itemObj.getString("price");
                            String time = itemObj.getString("time");
                            String image = itemObj.getString("image");
                            orderBean = new OrderBean.Builder()
                                    .setTitle(title)
                                    .setPrice(price)
                                    .setTime(time)
                                    .setImage(image)
                                    .build();
                            orderBeans.add(orderBean);
                        }
                        mRecyclerView.setAdapter(new OrderAdapter(R.layout.item_order_delegate, orderBeans));
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .params("", "")
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build()
                .get();


    }


}
