package com.ylx.todomvpapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ylx.todomvpapp.R;
import com.ylx.todomvpapp.bean.Fruit;
import com.ylx.todomvpapp.ui.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardViewActivity extends AppCompatActivity {

    private Fruit[] fruits = {new Fruit("苹果",R.drawable.apple),new Fruit("香蕉",R.drawable.banana),new Fruit("橘子",R.drawable.orange),
    new Fruit("梨",R.drawable.pear),new Fruit("菠萝",R.drawable.pineapple),new Fruit("樱桃",R.drawable.cherry),new Fruit("芒果",R.drawable.mango),
    new Fruit("葡萄",R.drawable.grape),new Fruit("草莓",R.drawable.strawberry),new Fruit("西瓜",R.drawable.watermelon)};

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    private Toolbar mToolbar;

    //模拟数据源
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    private FruitAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isLoading;


    //服务器总共有多少条数据
    private static final int total_counter = 50;
    //每页展示多少条数据
    private static final int request_count = 10;
    //已经读取多少条数据
    private int mCurrentCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

//        initFruit();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                //如果是最后一个item，则设置占据3列，否则占据1列
                boolean isFooter = position == mAdapter.getItemCount() - 1;
                return isFooter ? 2 : 1;
            }
        });
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new FruitAdapter(fruitList);
        mRecyclerView.setAdapter(mAdapter);

        requestData();

        initListener();

    }

    private void initListener() {
        /**
         * 下拉刷新
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fruitList.clear();
                requestData();
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        /**
         * 上啦加载
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && totalItemCount <= total_counter) {
                        isLoading = true;
                        requestData();
                    } else {
                        setState(2);
                    }
                }
            }
        });

        /**
         * 条目点击事件
         */
        mAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CardViewActivity.this,CollapsingToolBarLayoutActivity.class);
                intent.putExtra(CollapsingToolBarLayoutActivity.FRUIT_NAME,fruitList.get(position).getName());
                intent.putExtra(CollapsingToolBarLayoutActivity.FRUIT_IMAGE_ID,fruitList.get(position).getImageId());
                startActivity(intent);
                Toast.makeText(CardViewActivity.this,"你点击了" + fruitList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(CardViewActivity.this,"你长按了" + fruitList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initFruit() {
//        fruitList.clear();
        for(int i = 0; i < 10; i ++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    /**
     * 更改状态
     */
    private void setState(final int mState){
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                changeAdaperState(mState);
                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        });
    }

    /**
     * 改变底部状态样式
     */
    private void changeAdaperState(int mStatus){
        if(mAdapter != null && mAdapter.mFooterAdapter != null){
            mAdapter.mFooterAdapter.setData(mStatus);
        }
    }

    /**
     * 模拟网络请求数据
     */
    private void requestData(){
        setState(1); //正在加载
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);
                } catch (Exception e){
                    e.printStackTrace();
                }
                //模拟已解析数据并解析
                initFruit();

                //加载完毕
                setState(0);


                //或者网络加载失败
//                setState(2);


            }
        }.start();
    }
}
