package com.wuzheng.customviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.wuzheng.customviewpager.viewPager.BeeViewPager;
import com.wuzheng.customviewpager.viewPager.LoopViewPager;
import com.wuzheng.customviewpager.viewpagerindicator.BeeCirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    private int[] imageBg = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private LoopViewPager viewPager;
    private Timer bannerTimer;
    private BannerTimerTask bannerTimerTask;
    private Handler handler;
    private int currIndex;
    private Long DALAY = 3000L;
    private Long PEROOD = 3000L;
    BeeCirclePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerTimer = new Timer();
        viewPager = (LoopViewPager) findViewById(R.id.news_banner_pager);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setAdapter(new IndexGalleryAdapter(this, imageBg));
        mIndicator = (BeeCirclePageIndicator) findViewById(R.id.indicator);
        //给Viewpager设置Indicator
        mIndicator.setViewPager(viewPager, 0);
        //设置Viewpager初始化位置
        viewPager.setCurrentItem(0);
        currIndex = 0;

        viewPager.setOnPageChangeListener(new BeeViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                currIndex = position;
                mIndicator.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    bannerStartPlay();
                } else {
                    bannerStopPlay();
                }
            }
        });
        handler = new Handler() {
            public void handleMessage(Message msg) {
                viewPager.setCurrentItem(msg.what);
                super.handleMessage(msg);

            }

        };

        bannerStartPlay();
    }


    public void bannerStartPlay() {
        if (bannerTimer != null) {
            if (bannerTimerTask != null)
                bannerTimerTask.cancel();
            bannerTimerTask = new BannerTimerTask();
            bannerTimer.schedule(bannerTimerTask, DALAY, PEROOD);
        }
    }


    public void bannerStopPlay() {
        if (bannerTimerTask != null)
            bannerTimerTask.cancel();
        bannerTimerTask = null;
    }

    class BannerTimerTask extends TimerTask {
        @Override
        public void run() {
            Message msg = new Message();
            if (imageBg.length <= 1)
                return;
            msg.what = currIndex + 1;
            handler.sendMessage(msg);
        }

    }
}






