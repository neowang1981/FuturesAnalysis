package org.ezool.iqx.fragments;

import org.ezool.iqx.R;
import org.ezool.iqx.views.SwitchablePullToRefreshScrollView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TabhostWithRefreshableViewBaseFragment extends TabhostBaseFragment{
	
	@Override
	protected View createTabView(Context ctx, int position)
	{
		if (position == 0)
		{
			LayoutInflater li = LayoutInflater.from(ctx);
			SwitchablePullToRefreshScrollView v = 
					(SwitchablePullToRefreshScrollView)li.inflate(R.layout.pull_to_refresh_view_frame, null, false);
			return v;
		}
		else if (position == 1)
		{
		
			ImageView iv = new ImageView(ctx);
			iv.setBackgroundResource(R.drawable.weng8);
			return iv;
		}
		else
		{
			return null;
		}
	}
}
