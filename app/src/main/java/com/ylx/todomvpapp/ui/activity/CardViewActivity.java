package com.ylx.todomvpapp.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    private FruitAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        initFruit();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new FruitAdapter(fruitList);
        mRecyclerView.setAdapter(mAdapter);

        initListener();

    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initFruit();
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initFruit() {
        fruitList.clear();
        for(int i = 0; i < 50; i ++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
