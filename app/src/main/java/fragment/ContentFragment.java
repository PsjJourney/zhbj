package fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.zhbj.R;

import java.util.ArrayList;

import base.BasePager;
import impl.GovAffairsPager;
import impl.HomePager;
import impl.NewsCenterPager;
import impl.SettingPager;
import impl.SmartServicePager;

/**
 * Created by sj on 2016/6/15.
 */
public class ContentFragment extends BaseFragment {
    RadioGroup radioGroup;
    ViewPager mViewPager;
    private ArrayList<BasePager> mPagerList;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_content_layout, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_content);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        return view;
    }

    @Override
    public void initData() {
        radioGroup.check(R.id.rd_home); //默认勾选项
        mPagerList = new ArrayList<BasePager>();
        mPagerList.add(new HomePager(mActivity));
        mPagerList.add(new NewsCenterPager(mActivity));
        mPagerList.add(new SmartServicePager(mActivity));
        mPagerList.add(new GovAffairsPager(mActivity));
        mPagerList.add(new SettingPager(mActivity));
//        for (int i = 0; i > 4; i++) {
//            BasePager pager = new BasePager(mActivity);
//            mPagerList.add(pager);
//        }
        mViewPager.setAdapter(new ContentAdapter());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_home:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.news_center:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.smart_service:
                        mViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.gov:
                        mViewPager.setCurrentItem(3,false);
                        break;
                    case R.id.set:
                        mViewPager.setCurrentItem(4,false);
                        break;
                }
            }
        });
    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagerList.get(position);
            container.addView(basePager.mRootView);
            basePager.initData();
            return basePager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
