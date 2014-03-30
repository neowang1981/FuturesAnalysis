package org.ezool.iqx.fragments;

import java.io.Serializable;

import org.ezool.iqx.R;
import org.ezool.iqx.views.BaseView;
import org.ezool.iqx.views.SwitchablePullToRefreshScrollView;
import org.ezool.iqx.views.detail.TransactStatisticsView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 一个下拉刷新的scrollview
 * 暂时没有用到
 *
 */
public class PullToRefreshScrollBaseFragment extends ViewBaseFragment {
	
	private static final String VIEWDATA_INITIALIZATION_DONE = "VIEWDATA_INITIALIZATION_DONE";
	
	/** "处理中"界面 */
	private RelativeLayout processingView;
	
	private SwitchablePullToRefreshScrollView refreshableView;
	
	private View innerView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// 生成所有view
		View v = inflater.inflate(R.layout.view_with_refresh_scroll_frame, container, false);
		this.processingView = (RelativeLayout)v.findViewById(R.id.ready_wait_layout);
		this.refreshableView = (SwitchablePullToRefreshScrollView)v.findViewById(R.id.pull_refresh_scrollview);
		
		this.innerView = this.getInnerView(this.getActivity());
		this.innerView.setTag(this);
		this.addView(this.innerView);
		
		// 试图取得本fragment显示所需要的数据
		Serializable viewData = this.getViewDataFromSave();
		// 取得是否已经做过一次初始化数据取得的flag
		Boolean b = (Boolean)this.getTabDisplayData(VIEWDATA_INITIALIZATION_DONE);
		
		// 如果没有数据且未做过初始化，则与服务器进行通信
		if (viewData == null && b == null)
		{
			this.showWaitingView();
			
			this.setTabDisplayData(VIEWDATA_INITIALIZATION_DONE, Boolean.valueOf(true));
		
			v.postDelayed(new Runnable() {
	
				@Override
				public void run() {
					PullToRefreshScrollBaseFragment.this.closeWaitingView();
				}
			}, 
			3000);
		}
		else
		{
			this.closeWaitingView();
			// TODO
		}
		
		return v;
	}
	
	@Override
	public void showWaitingView() {
		this.refreshableView.setVisibility(View.INVISIBLE);
		this.processingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void closeWaitingView() {
		this.refreshableView.setVisibility(View.VISIBLE);
		this.processingView.setVisibility(View.INVISIBLE);
		
	}
	
	private void addView(View innerView)
	{
		this.refreshableView.getRefreshableView().addView(
				innerView, 
				new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}
	
	protected View getInnerView(Context ctx)
	{
		return new TransactStatisticsView(ctx);
	}

}
