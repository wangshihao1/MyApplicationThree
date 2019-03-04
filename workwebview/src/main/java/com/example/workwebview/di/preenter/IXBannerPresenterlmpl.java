package com.example.workwebview.di.preenter;


import com.example.workwebview.data.bean.BannerBean;
import com.example.workwebview.data.bean.HomeGoodsBean;
import com.example.workwebview.di.contrain.IXBannerContract;
import com.example.workwebview.di.model.IXBannerModellmpl;

import java.lang.ref.SoftReference;

public class IXBannerPresenterlmpl implements IXBannerContract.IXBanerPresenter<IXBannerContract.IXBannerView> {
    IXBannerContract.IXBannerView ixBannerView;
    private SoftReference<IXBannerContract.IXBannerView> reference;
    private IXBannerModellmpl modellmpl;

    @Override
    public void attahView(IXBannerContract.IXBannerView ixBannerView) {
        this.ixBannerView = ixBannerView;
        reference = new SoftReference<>(ixBannerView);
        modellmpl = new IXBannerModellmpl();
    }

    @Override
    public void decathView(IXBannerContract.IXBannerView ixBannerView) {
        reference.clear();
    }

    @Override
    public void goToXrequestBannerData() {
        modellmpl.containXBannerData(new IXBannerContract.IXBannerModel.OnCallBack() {
            @Override
            public void setData(BannerBean bannerBean) {
                ixBannerView.setBannerData(bannerBean );
            }
        });
    }

    @Override
    public void goToRequestHomeGoodsData() {
        modellmpl.setResponesData(new IXBannerContract.IXBannerModel.OnHomeCallBack() {
            @Override
            public void setHomeGoodsData(HomeGoodsBean homeGoodsBean) {
                ixBannerView.setHomeGoodsData(homeGoodsBean);
            }
        });
    }
}
