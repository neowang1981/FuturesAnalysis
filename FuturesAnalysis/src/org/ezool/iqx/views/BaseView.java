package org.ezool.iqx.views;

import java.io.Serializable;

import org.ezool.iqx.fragments.ViewBaseFragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * 基类view
 * 现在没用到
 *
 */
public class BaseView extends ViewGroup{

	private LayoutInflater layoutInflater;
	
	private ViewBaseFragment viewContainerFragment;
	
	public BaseView(Context context, ViewBaseFragment container) {
		super(context);
		this.setLayoutInflater(LayoutInflater.from(context));
		this.viewContainerFragment = container;
		
		
	}
	
	public BaseView(Context context, AttributeSet attrs, ViewBaseFragment container) {
		super(context, attrs);
		this.setLayoutInflater(LayoutInflater.from(context));
		this.viewContainerFragment = container;
	}

	protected LayoutInflater getLayoutInflater() {
		return layoutInflater;
	}

	private void setLayoutInflater(LayoutInflater layoutInflater) {
		this.layoutInflater = layoutInflater;
	}
	
	protected final Serializable getViewData()
	{
		return this.viewContainerFragment.getViewDataFromSave();
	}

	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int i = 0;
	}
}
