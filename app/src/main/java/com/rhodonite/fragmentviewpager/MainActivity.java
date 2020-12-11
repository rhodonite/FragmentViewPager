package com.rhodonite.fragmentviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rhodonite.fragmentviewpager.Fragment.Fragmrnt_1;
import com.rhodonite.fragmentviewpager.Fragment.Fragmrnt_2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLinearLayout;// 小灰點
    private View point_red;// 小紅點
    private int mPointWidth;// 小圓點之間的距離
    public List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments.add(new Fragmrnt_1());
        fragments.add(new Fragmrnt_2());
        initView();
        initDot();
    }


    private void initDot() {
        for (int i = 0; i < fragments.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.dot_gray);

            // dp和px的關係: dp = px/裝置密度
            float density = getResources().getDisplayMetrics().density;
            int width = (int) (10 * density);
            int height = (int) (10 * density);
            //System.out.println("裝置密度:" + density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            if (i > 0) {
                params.leftMargin = width;
            }
            view.setLayoutParams(params);
            mLinearLayout.addView(view);
        }

        mLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        mLinearLayout.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = mLinearLayout.getChildAt(1).getLeft()
                                - mLinearLayout.getChildAt(0).getLeft();
                        Log.e("tag", mPointWidth + "");
                    }
                });
    }

    private void initView() {
        ViewPager mVp = (ViewPager) findViewById(R.id.viewPager);

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_point_group);
        point_red = findViewById(R.id.point_red);


        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftWidth = mPointWidth * positionOffset + position * mPointWidth;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) point_red
                        .getLayoutParams();// 獲取小紅點的佈局引數
                layoutParams.leftMargin = (int) leftWidth;// 設定小紅點的左邊距
                point_red.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < fragments.size(); i++) {
                    if (i == position) {
                        fragments.get(i).onResume();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
