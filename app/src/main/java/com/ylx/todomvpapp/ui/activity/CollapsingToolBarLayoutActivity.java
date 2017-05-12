package com.ylx.todomvpapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylx.todomvpapp.R;

public class CollapsingToolBarLayoutActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar_layout);

        String fruitName = getIntent().getStringExtra(FRUIT_NAME);
        int fruitImageId = getIntent().getIntExtra(FRUIT_IMAGE_ID,0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolBar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolBar.setTitle(fruitName);

        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image);
        TextView fruitContentView = (TextView) findViewById(R.id.fruit_content_text);

        Glide.with(this).load(fruitImageId).into(fruitImageView);

        String fruitContent = generateFruitContent(fruitName);
        fruitContentView.setText(fruitContent);
    }


    private String generateFruitContent(String fruitName) {
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < 1000; i ++){
            content.append(fruitName);
        }
        return content.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
