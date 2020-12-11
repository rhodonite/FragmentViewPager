package com.rhodonite.fragmentviewpager.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rhodonite.fragmentviewpager.MainActivity;
import com.rhodonite.fragmentviewpager.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class Fragmrnt_1 extends Fragment {

    MainActivity mainActivity;
    private ViewPager mVp;
    private LinearLayout mLinearLayout;// 小灰點
    private View point_red;// 小紅點
    private int mPointWidth;// 小圓點之間的距離
    private static final int[] pictures = { R.mipmap.pic_1, R.mipmap.pic_2};
    private ArrayList<Integer> list = new ArrayList<Integer>();
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1,container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i : pictures){
            list.add(i);
        }
        initView();
        initDot();
    }
    private void initDot() {
        for (int i = 0; i < list.size(); i++) {
            View view = new View(getActivity());
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
                                - mLinearLayout.getChildAt(0).getLeft();    //如果只有一張圖，這行註解掉
                        Log.e("tag", mPointWidth + "");
                    }
                });
    }



    private void initView() {
        mVp = (ViewPager) getView().findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter;
        myPagerAdapter = new MyPagerAdapter(this.getActivity(), list);
        mVp.setAdapter(myPagerAdapter);
        mLinearLayout = (LinearLayout) getView().findViewById(R.id.ll_point_group);
        point_red = getView().findViewById(R.id.point_red);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("Fragment_1", "onResume");
    }

}

