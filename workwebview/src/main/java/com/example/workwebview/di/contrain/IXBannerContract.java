package com.example.workwebview.di.contrain;


import com.example.workwebview.data.bean.BannerBean;
import com.example.workwebview.data.bean.HomeGoodsBean;

public interface IXBannerContract {

    public interface IXBannerView{
        //请求XB  数据
        void setBannerData(BannerBean bannerBean);
        //请求商品  数据
        void setHomeGoodsData(HomeGoodsBean homeGoodsBean);

    }

    public interface IXBanerPresenter<IXBannerView>{

        void attahView(IXBannerView ixBannerView);

        void decathView(IXBannerView ixBannerView);
        //请求XB  数据
        void goToXrequestBannerData();
        //请求商品  数据
        void goToRequestHomeGoodsData();

    }

    public interface IXBannerModel{
        //请求XB  数据
        void containXBannerData(OnCallBack onCallBack);

        //请求XB  数据
        public interface OnCallBack{
            void setData(BannerBean bannerBean);
        }
        void setResponesData(OnHomeCallBack onHomeCallBack);
        //请求商品  数据
        public interface OnHomeCallBack{
            void setHomeGoodsData(HomeGoodsBean homeGoodsBean);
        }
    }
}
