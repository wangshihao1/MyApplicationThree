package com.example.workwebview.di.model;


import android.util.Log;

import com.example.workwebview.data.aplis.Aplis;
import com.example.workwebview.data.bean.BannerBean;
import com.example.workwebview.data.bean.HomeGoodsBean;
import com.example.workwebview.di.contrain.IXBannerContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IXBannerModellmpl implements IXBannerContract.IXBannerModel {
    @Override
    public void containXBannerData(final IXBannerContract.IXBannerModel.OnCallBack onCallBack) {
        OkGo.<String>get(Aplis.XBANNER_HOME).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String bannerResponseData = response.body().toString();
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(bannerResponseData, BannerBean.class);
                onCallBack.setData(bannerBean);
            }
        });
    }

    @Override
    public void setResponesData(final OnHomeCallBack onHomeCallBack) {
        OkGo.<String>get(Aplis.HOMEGOODS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String homeGoodsData = response.body().toString();
                Gson gson = new Gson();
                HomeGoodsBean homeGoodsBean = gson.fromJson(homeGoodsData, HomeGoodsBean.class);
                Log.d("IXBannerModellmpl", "homeGoodsBean.getResult():" + homeGoodsBean.getResult());
                onHomeCallBack.setHomeGoodsData(homeGoodsBean);
            }
        });
    }
}
