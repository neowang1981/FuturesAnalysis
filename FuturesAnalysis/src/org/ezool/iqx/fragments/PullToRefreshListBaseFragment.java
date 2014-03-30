package org.ezool.iqx.fragments;

import java.io.Serializable;

import org.ezool.iqx.R;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

/**
 * ����ˢ���б�Ļ���fragment
 * �ǵ�������ˢ��list����Ļ���
 */
public class PullToRefreshListBaseFragment extends ViewBaseFragment{
	
	/** ����ˢ�µ�flag */
	private static final String VIEWDATA_INITIALIZATION_DONE = "VIEWDATA_INITIALIZATION_DONE";
	
	/** �����н����frame view */
	private RelativeLayout processingView;
	
	/** ����ˢ�½������list */
	private PullToRefreshListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// �����ڲ�view����ʼ��������view����
		View v = inflater.inflate(R.layout.view_with_refresh_list_frame, container, false);
		this.processingView = (RelativeLayout)v.findViewById(R.id.ready_wait_layout);
		this.listView = (PullToRefreshListView)v.findViewById(R.id.pull_refresh_list);
		
		// ��ͼȡ�ñ�fragment��ʾ����Ҫ������
		Serializable viewData = this.getViewDataFromSave();
		// ȡ���Ƿ��Ѿ�����һ�γ�ʼ������ȡ�õ�flag
		Boolean b = (Boolean)this.getTabDisplayData(VIEWDATA_INITIALIZATION_DONE);
		
		// ����list��������
		this.listView.setAdapter(this.getListAdapter(this.getActivity()));
		
		// ���û��������δ������ʼ�����������������ͨ��
		if (viewData == null && b == null)
		{
			// ������ʾ�����н���
			this.showWaitingView();
			
			// �����Ƿ��Ѿ�����һ�γ�ʼ������ȡ�õ�flagΪtrue
			this.setTabDisplayData(VIEWDATA_INITIALIZATION_DONE, Boolean.valueOf(true));
		
			
			v.postDelayed(new Runnable() {
	
				@Override
				public void run() {
					PullToRefreshListBaseFragment.this.closeWaitingView();
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
	
	/**
	 * ��ʾ�����н���
	 */
	@Override
	public void showWaitingView() {
		this.listView.setVisibility(View.INVISIBLE);
		this.processingView.setVisibility(View.VISIBLE);
	}

	/**
	 * �رմ����н��沢�ָ�������ʾ
	 */
	@Override
	public void closeWaitingView() {
		this.listView.setVisibility(View.VISIBLE);
		this.processingView.setVisibility(View.INVISIBLE);
		
	}
	
	/**
	 * ����list��������
	 * ������������������ʵ��
	 * @param ctx �������context
	 * @return list��������
	 */
	protected ListAdapter getListAdapter(Context ctx)
	{
		return null;
	}
	
	
}
