package com.example.workwebview.di.model;


import com.example.workwebview.data.aplis.Aplis;
import com.example.workwebview.data.bean.GoodsDetailsBean;
import com.example.workwebview.di.contrain.GoodsDetailsContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
public class IGoodsDetailsModellmpl implements GoodsDetailsContract.IGoodsDetailsModel {

    @Override
    public void setResponesData(int commodityId, final GoodsDetailsContract.IGoodsDetailsModel.OnHomeCallBack onHomeCallBack) {
        OkGo.<String>get(Aplis.GOODSDETAILS_URL+"?commodityId="+commodityId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                String responseDetailsData = body.toString();
                Gson gson = new Gson();
                GoodsDetailsBean goodsDetailsBean = gson.fromJson(responseDetailsData, GoodsDetailsBean.class);
                GoodsDetailsBean.ResultBean resultDetailsData = goodsDetailsBean.getResult();
                onHomeCallBack.setGoodsDetailsData(resultDetailsData);
            }
        });
    }


}
