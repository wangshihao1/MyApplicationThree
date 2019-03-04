package com.example.workwebview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.workwebview.R;
import com.example.workwebview.data.adapter.MlssBrvahAdapter;
import com.example.workwebview.data.adapter.PzshBrvahAdapter;
import com.example.workwebview.data.adapter.RxxpBrvahAdapter;
import com.example.workwebview.data.bean.BannerBean;
import com.example.workwebview.data.bean.HomeGoodsBean;
import com.example.workwebview.di.contrain.IXBannerContract;
import com.example.workwebview.di.preenter.IXBannerPresenterlmpl;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IXBannerContract.IXBannerView {


    @BindView(R.id.shouye_pager)
    RelativeLayout shouyePager;
    @BindView(R.id.webview_pager)
    RelativeLayout webviewPager;
    @BindView(R.id.btn_pzsh)
    Button btnPzsh;
    @BindView(R.id.btn_mlss)
    Button btnMlss;
    @BindView(R.id.btn_rxxp)
    Button btnRxxp;
    @BindView(R.id.pzsh_rv)
    RecyclerView pzshRv;
    @BindView(R.id.mlss_rv)
    RecyclerView mlssRv;
    @BindView(R.id.rxxp_rv)
    RecyclerView rxxpRv;
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    @BindView(R.id.web_view_shop)
    WebView webViewShop;
    private IXBannerPresenterlmpl presenterlmpl;
    private RxxpBrvahAdapter rxxpBrvahAdapter;
    private List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> commodityList;
    private MlssBrvahAdapter mlssBrvahAdapter;
    private PzshBrvahAdapter pzshBrvahAdapter;
    private List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList_mlss;
    private List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> commodityList_commodityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenterlmpl = new IXBannerPresenterlmpl();
        presenterlmpl.attahView(this);
        //XB  数据
        presenterlmpl.goToXrequestBannerData();
        //商品数据
        presenterlmpl.goToRequestHomeGoodsData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //对Toolbar左边的导航图标进行监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings1:
                        //Toast.makeText(MainActivity.this, "菜单1", Toast.LENGTH_SHORT).show();
                        shouyePager.setVisibility(View.VISIBLE);
                        webviewPager.setVisibility(View.GONE);
                        return true;
                    case R.id.action_settings2:
                        //Toast.makeText(MainActivity.this, "菜单2", Toast.LENGTH_SHORT).show();
                        shouyePager.setVisibility(View.GONE);
                        webviewPager.setVisibility(View.VISIBLE);
                        return true;

                }
                return false;
            }
        });
        webViewShow();

    }
    //展示web
    private void webViewShow() {
        webViewShop.loadUrl("file:///android_asset/news.html");

    }

    @OnClick({R.id.btn_pzsh, R.id.btn_mlss, R.id.btn_rxxp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pzsh:
                pzshRv.setVisibility(View.VISIBLE);
                mlssRv.setVisibility(View.GONE);
                rxxpRv.setVisibility(View.GONE);
                break;
            case R.id.btn_mlss:
                pzshRv.setVisibility(View.GONE);
                mlssRv.setVisibility(View.VISIBLE);
                rxxpRv.setVisibility(View.GONE);
                break;
            case R.id.btn_rxxp:
                pzshRv.setVisibility(View.GONE);
                mlssRv.setVisibility(View.GONE);
                rxxpRv.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setBannerData(BannerBean bannerBean) {
        List<BannerBean.ResultBean> result = bannerBean.getResult();
        List<String> list_banner = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            String imageUrl = result.get(i).getImageUrl();
            list_banner.add(imageUrl);
        }
        flyBanner.setImagesUrl(list_banner);

    }

    @Override
    public void setHomeGoodsData(HomeGoodsBean homeGoodsBean) {

        HomeGoodsBean.ResultBean.PzshBean pzsh = homeGoodsBean.getResult().getPzsh();
        HomeGoodsBean.ResultBean.MlssBean mlss = homeGoodsBean.getResult().getMlss();
        HomeGoodsBean.ResultBean.RxxpBean rxxp = homeGoodsBean.getResult().getRxxp();

        if (rxxp != null) {
            if (rxxp.getCommodityList().size() <= 0) {
                return;
            } else {
                for (int i = 0; i < rxxp.getCommodityList().size(); i++) {
                    //数据源
                    commodityList = rxxp.getCommodityList();

                    //布局
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    rxxpRv.setLayoutManager(layoutManager);

                    //适配器
                    rxxpBrvahAdapter = new RxxpBrvahAdapter(R.layout.rxxp_goods_item, commodityList);
                    rxxpRv.setAdapter(rxxpBrvahAdapter);
                }
            }
            rxxpBrvahAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    intentValues(commodityList.get(position).getCommodityId());
                }
            });
        }
        if (mlss != null) {
            if (mlss.getCommodityList().size() <= 0) {
                return;
            } else {
                for (int i = 0; i < mlss.getCommodityList().size(); i++) {
                    //得到数据源
                    commodityList_mlss = mlss.getCommodityList();
                    //布局
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    mlssRv.setLayoutManager(layoutManager);

                    mlssBrvahAdapter = new MlssBrvahAdapter(R.layout.mlss_goods_item, commodityList_mlss);
                    mlssRv.setAdapter(mlssBrvahAdapter);
                }
            }
            mlssBrvahAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    intentValues(commodityList_mlss.get(position).getCommodityId());
                }
            });
        }
        if (pzsh != null) {
            if (pzsh.getCommodityList().size() <= 0) {
                return;
            } else {
                for (int i = 0; i < pzsh.getCommodityList().size(); i++) {
                    //得到数据源
                    commodityList_commodityList = pzsh.getCommodityList();
                    //布局
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, LinearLayoutManager.VERTICAL, false);
                    pzshRv.setLayoutManager(gridLayoutManager);

                    pzshBrvahAdapter = new PzshBrvahAdapter(R.layout.pzss_goods_item, commodityList_commodityList);
                    pzshRv.setAdapter(pzshBrvahAdapter);
                }
            }
            pzshBrvahAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    intentValues(commodityList_commodityList.get(position).getCommodityId());
                }
            });
            pzshRv.setVisibility(View.VISIBLE);
            mlssRv.setVisibility(View.GONE);
            rxxpRv.setVisibility(View.GONE);
        }
    }

    private void intentValues(int commodityId) {

        //跳转传值
        Intent intent = new Intent(MainActivity.this, GoodsDetailsActivity.class);
        intent.putExtra("CommodityId", commodityId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }
}
