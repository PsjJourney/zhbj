package com.zhbj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import utils.PrefUtils;

/**
 * Created by 桑健 on 2016/6/5.
 */
public class GuideActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImageView imageView;
    public int[] Rid = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    public ArrayList<ImageView> ImgList;
    LinearLayout llPointGroup;
    View redPoint;
    private int width; //俩点之间的距离
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        redPoint = findViewById(R.id.red_point);
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setBoolean(GuideActivity.this, "isFirstOpened", true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        initViews();
        GuideAdapter adapter = new GuideAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new GuidePageListener());
    }

    public void initViews() {
        ImgList = new ArrayList();
        for (int i = 0; i < Rid.length; i++) {
            ImageView img = new ImageView(GuideActivity.this);
            img.setBackgroundResource(Rid[i]);
            ImgList.add(img);

            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            if (i > 0) {
                params.leftMargin = 30;
            }
            view.setLayoutParams(params);
            llPointGroup.addView(view);

        }


        //获取试图树
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当layout执行结束后回调此方法
            @Override
            public void onGlobalLayout() {
                Log.e("layout执行结束", "layout执行结束");
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //measure layout ondraw
                width = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                Log.e("width:", width + "");
            }
        });
    }

    class GuideAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return Rid.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(ImgList.get(position));
            return ImgList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.e("position:", position + "");
//            Log.e("positionOffset:", positionOffset + "");
//            Log.e("positionOffsetPixels:", positionOffsetPixels + "");
            int length = (int) (width * positionOffset) + position * width;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
            params.leftMargin = length;
            redPoint.setLayoutParams(params);

        }

        @Override
        public void onPageSelected(int position) {
            if (position == Rid.length - 1) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
