package base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.zhbj.R;

/**
 * Created by sj on 2016/6/16.
 */
public class BasePager {
    public Activity mActivity;
    public View mRootView;
    public FrameLayout flContent;// 内容

    public BasePager(Activity activity) {
        mActivity = activity;
        initViews();
    }

    public void initViews() {
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
    }

    public void initData() {

    }
}
