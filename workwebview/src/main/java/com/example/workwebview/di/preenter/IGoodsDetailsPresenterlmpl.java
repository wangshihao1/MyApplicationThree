package com.example.workwebview.di.preenter;



import com.example.workwebview.data.bean.GoodsDetailsBean;
import com.example.workwebview.di.contrain.GoodsDetailsContract;
import com.example.workwebview.di.model.IGoodsDetailsModellmpl;

import java.lang.ref.SoftReference;

public class IGoodsDetailsPresenterlmpl implements GoodsDetailsContract.IGoodsDetailsPresenter<GoodsDetailsContract.IGoodsDetailsView> {
    GoodsDetailsContract.IGoodsDetailsView iGoodsDetailsView;
    private SoftReference<GoodsDetailsContract.IGoodsDetailsView> reference;
    private IGoodsDetailsModellmpl modellmpl;

    @Override
    public void attahView(GoodsDetailsContract.IGoodsDetailsView iGoodsDetailsView) {
        this.iGoodsDetailsView = iGoodsDetailsView;
        reference = new SoftReference<>(iGoodsDetailsView);
        modellmpl = new IGoodsDetailsModellmpl();


    }

    @Override
    public void decathView(GoodsDetailsContract.IGoodsDetailsView iGoodsDetailsView) {
        reference.clear();
    }
    /*
    * 请求商品详情的数据
    * */
    @Override
    public void goToRequestGoodsDetailsData(int commodityId) {
        modellmpl.setResponesData(commodityId,new GoodsDetailsContract.IGoodsDetailsModel.OnHomeCallBack() {
            @Override
            public void setGoodsDetailsData(GoodsDetailsBean.ResultBean resultDetailsData) {
                iGoodsDetailsView.setGoodsDetailsData(resultDetailsData);
            }
        });
    }


}
