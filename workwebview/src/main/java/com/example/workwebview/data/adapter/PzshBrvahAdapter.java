package com.example.workwebview.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.workwebview.R;
import com.example.workwebview.data.bean.HomeGoodsBean;


import java.util.List;

public class PzshBrvahAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX,BaseViewHolder> {
    public PzshBrvahAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX item) {
        helper.setText(R.id.pzss_tv_name,item.getCommodityName());
        helper.setText(R.id.pzss_tv_price,item.getPrice()+"");
        ImageView pzsh_icon = helper.getView(R.id.pzss_iv_icon);
        Glide.with(mContext).load(item.getMasterPic()).into(pzsh_icon);
    }
}
