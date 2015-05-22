package com.wuzheng.customviewpager;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class IndexGalleryAdapter extends PagerAdapter {
    private int[] imageBg;
    private LayoutInflater mInflater;
    private Context mContext;


    public IndexGalleryAdapter(Context context, int[] imageBg) {

        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.imageBg = imageBg;

    }


    @Override
    public float getPageWidth(int position) {
        return 0.8f;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageBg.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        View imageLayout = mInflater.inflate(R.layout.index_gallery_item, null);
        ImageView image = (ImageView) imageLayout.findViewById(R.id.news_banner_image);
        image.setBackgroundResource(imageBg[position]);

        view.addView(imageLayout);

        return imageLayout;
    }


}
