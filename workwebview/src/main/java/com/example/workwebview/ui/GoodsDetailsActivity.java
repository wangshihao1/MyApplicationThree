package com.example.workwebview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.workwebview.R;
import com.example.workwebview.data.bean.GoodsDetailsBean;
import com.example.workwebview.di.contrain.GoodsDetailsContract;
import com.example.workwebview.di.preenter.IGoodsDetailsPresenterlmpl;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailsActivity extends AppCompatActivity implements GoodsDetailsContract.IGoodsDetailsView {


    @BindView(R.id.goodsContent_goods_Price)
    TextView goodsContentGoodsPrice;
    @BindView(R.id.goodsContent_goods_yiShou)
    TextView goodsContentGoodsYiShou;
    @BindView(R.id.goodsContent_goods_title)
    TextView goodsContentGoodsTitle;
    @BindView(R.id.goodsContent_goods_weight)
    TextView goodsContentGoodsWeight;
    @BindView(R.id.goodsContent_product_contentImg)
    ImageView goodsContentProductContentImg;
    @BindView(R.id.goodsContent_product_introductionText)
    TextView goodsContentProductIntroductionText;
    @BindView(R.id.goodsContent_product_introductionImg)
    ImageView goodsContentProductIntroductionImg;
    @BindView(R.id.goodsContentPage_ViewPager)
    FlyBanner goodsContentPageViewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IGoodsDetailsPresenterlmpl presenterlmpl;
    private int commodityId;
    private List<GoodsDetailsBean.ResultBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsdetails_item);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("CommodityId", 0);

        presenterlmpl = new IGoodsDetailsPresenterlmpl();
        presenterlmpl.attahView(this);
        presenterlmpl.goToRequestGoodsDetailsData(commodityId);

    }

    @Override
    public void setGoodsDetailsData(GoodsDetailsBean.ResultBean resultDetailsData) {
        //标题
        String CommodityName = resultDetailsData.getCommodityName();
        goodsContentGoodsTitle.setText(CommodityName);
        //价格
        String price = resultDetailsData.getPrice() + "";
        goodsContentGoodsPrice.setText(price);
        //重量
        String weight = resultDetailsData.getWeight() + "Kg";
        goodsContentGoodsWeight.setText(weight);
        //mingz
        String CategoryName = resultDetailsData.getCategoryName();
        goodsContentProductIntroductionText.setText(CategoryName);
        //已售出
        String SaleNum = "已售出" + resultDetailsData.getCommentNum() + "件";
        goodsContentGoodsYiShou.setText(SaleNum);
        //获取图片
        String picture = resultDetailsData.getPicture();

        String[] split = picture.split(",");
        List<String> image_list_details = Arrays.asList(split);
        Glide.with(GoodsDetailsActivity.this).load(split[0]).into(goodsContentProductContentImg);
        Glide.with(GoodsDetailsActivity.this).load(split[1]).into(goodsContentProductIntroductionImg);
        goodsContentPageViewPager.setImagesUrl(image_list_details);

        Toast.makeText(GoodsDetailsActivity.this, CommodityName + "", Toast.LENGTH_SHORT).show();
        list.clear();
        list.add(resultDetailsData);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }


    @OnClick(R.id.toolbar)
    public void onViewClicked() {
        finish();
    }
}
