package org.ezool.iqx.fragments;

import java.io.Serializable;

import org.ezool.iqx.R;
import org.ezool.iqx.views.BaseView;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * �ڲ�Ϊһ���ڽ������¶˵�tabhost������Ϊһ��tab��ͼ��fragment
 * tab���������Դ�1��3��������
 * �߱�ͨ���еȴ�������л����ܣ��Լ�ViewBaseFragment�е����й���
 * ������μ�ViewBaseFragment
 */
public class TabhostBaseFragment extends ViewBaseFragment{
	
	private static final String VIEWDATA_TABHOST_CURRENT_INDEX = "VIEWDATA_TABHOST_CURRENT_INDEX";
	
	private static final String VIEWDATA_INITIALIZATION_DONE = "VIEWDATA_INITIALIZATION_DONE";
	
	/** ��һ��tab����ʾframe */
	private RelativeLayout tabViewFrame1;
	
	/** �ڶ���tab����ʾframe */
	private RelativeLayout tabViewFrame2;
	
	/** ������tab����ʾframe */
	private RelativeLayout tabViewFrame3;
	
	/** tabhost����ʾ�ڽ�������¶� */
	private TabHost tabHost;
	
	/** "������"���� */
	private RelativeLayout processingView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// ��������view
		View v = inflater.inflate(R.layout.view_with_tabhost_frame, container, false);
		this.tabHost = (TabHost)v.findViewById(android.R.id.tabhost);
		this.tabViewFrame1 = (RelativeLayout)v.findViewById(R.id.tab_first);
		this.tabViewFrame2 = (RelativeLayout)v.findViewById(R.id.tab_second);
		this.tabViewFrame3 = (RelativeLayout)v.findViewById(R.id.tab_third);
		
		this.processingView = (RelativeLayout)v.findViewById(R.id.ready_wait_layout);
		
		// �����ڲ���ܵ�ID����
		final int[] innerIdArray = new int[] {R.id.tab_first, R.id.tab_second, R.id.tab_third};
		// �����ڲ���ܵ�layout����
		final RelativeLayout[] innerLayoutArray = new RelativeLayout[] {this.tabViewFrame1, this.tabViewFrame2, this.tabViewFrame3};
		
		// ����tab������tab��������tabhost
		this.tabHost.setup();
		for(int i = 0; i < this.getTabCount(); ++i)
		{
			this.tabHost.addTab(this.tabHost.newTabSpec(this.getTabSpec(i))
					.setIndicator(this.getTabSpec(i), getResources().getDrawable(android.R.drawable.ic_menu_call))
					.setContent(innerIdArray[i]));
		}
		
		this.tabHost.setOnTabChangedListener(new OnTabChangeListener(){
			
			@Override
			public void onTabChanged(String arg0) {
				int currentIndex = TabhostBaseFragment.this.tabHost.getCurrentTab();
				TabhostBaseFragment.this.setTabDisplayData(
						VIEWDATA_TABHOST_CURRENT_INDEX, Integer.valueOf(currentIndex));
			}
			
		});
		
		// ����tab������tab���⽫���ɼ���tab��ȥ
		for(int i = this.getTabCount(); i < innerLayoutArray.length; ++i)
		{
			innerLayoutArray[i].setVisibility(View.GONE);
		}
		
		// ����tab��������tab�ڲ���VIEW
		for(int i = 0; i < this.getTabCount(); ++i)
		{
			this.createTabViewImpl(this.getActivity(), i);
		}
		
		Integer idx = (Integer)this.getTabDisplayData(VIEWDATA_TABHOST_CURRENT_INDEX);
		if (idx != null)
		{
			this.tabHost.setCurrentTab(idx.intValue());
		}
		else
		{
			// ������ʾ��һ��tab��view
			this.tabHost.setCurrentTab(0);
			this.setTabDisplayData(VIEWDATA_TABHOST_CURRENT_INDEX, Integer.valueOf(0));
		}
		
		
		// ��ͼȡ�ñ�fragment��ʾ����Ҫ������
		Serializable viewData = this.getViewDataFromSave();
		// ȡ���Ƿ��Ѿ�����һ�γ�ʼ������ȡ�õ�flag
		Boolean b = (Boolean)this.getTabDisplayData(VIEWDATA_INITIALIZATION_DONE);
		
		// ���û��������δ������ʼ�����������������ͨ��
		if (viewData == null && b == null)
		{
			this.showWaitingView();
			
			this.setTabDisplayData(VIEWDATA_INITIALIZATION_DONE, Boolean.valueOf(true));
		
			v.postDelayed(new Runnable() {
	
				@Override
				public void run() {
					TabhostBaseFragment.this.closeWaitingView();
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
	
	private void createTabViewImpl(Context ctx, int position)
	{
		View v = this.createTabView(ctx, position);
		if (v != null)
		{
			v.setTag(this);
			this.addView(v,  position);
		}
		
	}
	
	protected View createTabView(Context ctx, int position)
	{
		if (position == 0)
		{
			ImageView iv = new ImageView(ctx);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setImageResource(R.drawable.weng8);
			return iv;
		}
		else if (position == 1)
		{
		
			LayoutInflater li = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = li.inflate(R.layout.pull_to_refresh_view_frame, null);
			return v;
		}
		else
		{
			return null;
		}
	}
	
	
	private void addView(View innerView, int position)
	{
		RelativeLayout.LayoutParams lp = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 
						RelativeLayout.LayoutParams.MATCH_PARENT);

		if (position == 0)
		{
			this.tabViewFrame1.addView(innerView, lp);
		}
		else if (position == 1)
		{
			this.tabViewFrame2.addView(innerView, lp);
		}
		else if (position == 2)
		{
			this.tabViewFrame3.addView(innerView, lp);
		}
		else
		{
			assert false;
		}
	}

	
	protected int getTabCount()
	{
		return 2;
	}
	
	protected String getTabSpec(int tabPos)
	{
		return "������";
	}

	@Override
	public void showWaitingView() {
		this.tabHost.setVisibility(View.INVISIBLE);
		this.processingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void closeWaitingView() {
		this.tabHost.setVisibility(View.VISIBLE);
		this.processingView.setVisibility(View.INVISIBLE);
		
	}
}
