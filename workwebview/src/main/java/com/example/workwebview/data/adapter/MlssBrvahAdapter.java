package com.example.workwebview.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.workwebview.R;
import com.example.workwebview.data.bean.HomeGoodsBean;


import java.util.List;

public class MlssBrvahAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX,BaseViewHolder> {
    public MlssBrvahAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX item) {
        helper.setText(R.id.mlss_tv_name,item.getCommodityName());
        helper.setText(R.id.mlss_tv_price,item.getPrice()+"");
        ImageView mlss_icon = helper.getView(R.id.mlss_iv_icon);
        Glide.with(mContext).load(item.getMasterPic()).into(mlss_icon);
    }
}
