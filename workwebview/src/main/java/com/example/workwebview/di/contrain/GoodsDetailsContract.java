package com.example.workwebview.di.contrain;


import com.example.workwebview.data.bean.GoodsDetailsBean;

public interface GoodsDetailsContract {

    public interface IGoodsDetailsView{

        void setGoodsDetailsData(GoodsDetailsBean.ResultBean resultDetailsData);

    }

    public interface IGoodsDetailsPresenter<IGoodsDetailsView>{

        void attahView(IGoodsDetailsView iGoodsDetailsView);

        void decathView(IGoodsDetailsView iGoodsDetailsView);

        //请求商品  数据
        void goToRequestGoodsDetailsData(int commodityId);

    }

    public interface IGoodsDetailsModel{

        void setResponesData(int commodityId, OnHomeCallBack onHomeCallBack);
        //请求商品  数据
        public interface OnHomeCallBack{
            void setGoodsDetailsData(GoodsDetailsBean.ResultBean resultDetailsData);
        }

    }
}
