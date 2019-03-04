package com.example.workwebview.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.workwebview.R;
import com.example.workwebview.data.bean.HomeGoodsBean;

import java.util.List;

public class RxxpBrvahAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean,BaseViewHolder> {
   /* OnRxxpItemCheckListener onRxxpItemCheckListener;

    public void setOnRxxpItemCheckListener(OnRxxpItemCheckListener onRxxpItemCheckListener) {
        this.onRxxpItemCheckListener = onRxxpItemCheckListener;
    }
    public interface OnRxxpItemCheckListener {
        void onCallBack();
    }*/
    public RxxpBrvahAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean item) {
        helper.setText(R.id.rxxp_tv_name,item.getCommodityName());
        helper.setText(R.id.rxxp_tv_price,item.getPrice()+"");
        ImageView rxxp_icon = helper.getView(R.id.rxxp_iv_icon);
        Glide.with(mContext).load(item.getMasterPic()).into(rxxp_icon);



    }
}
