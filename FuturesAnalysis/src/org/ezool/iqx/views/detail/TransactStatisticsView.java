package org.ezool.iqx.views.detail;

import java.util.HashMap;
import java.util.List;

import org.ezool.iqx.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TransactStatisticsView extends ListView{

	public TransactStatisticsView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.init(context);
	}
	
	public TransactStatisticsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init(context);
	}
	
	public TransactStatisticsView(Context context) {
		super(context);
		this.init(context);
	}
	
	private void init(Context ctx)
	{
		TransactStatisticsListViewAdapter adapter = new 
				TransactStatisticsListViewAdapter(ctx, new int[] {0, 1, 1, 1, 2, 0, 1, 2});
		this.setAdapter(adapter);
		this.setBackgroundColor(Color.argb(0xff, 0xf0, 0xed, 0xee));
		this.setDividerHeight(0);
		this.setDivider(null);
		
	}

	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	    ListAdapter listAdapter = this.getAdapter();  
	    if (listAdapter == null) { 
	        return; 
	    } 
	 
	    int totalHeight = 0; 
	    for (int i = 0; i < listAdapter.getCount(); i++) { 
	        View listItem = listAdapter.getView(i, null, this); 
	        try
	        {
	        	listItem.measure(0, 0); 
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        totalHeight += listItem.getMeasuredHeight(); 
	    } 
	 
	    totalHeight = totalHeight + (this.getDividerHeight() * (listAdapter.getCount() - 1));
	    totalHeight += this.getPaddingBottom();
	    totalHeight += this.getPaddingTop();
	    
	    this.setMeasuredDimension(this.getMeasuredWidth(), totalHeight);
		
		
	}



	private class TransactStatisticsListViewAdapter extends BaseAdapter{
		
		//数据源
		private Context context;
		private int []type;
	 
		//构造函数
		public TransactStatisticsListViewAdapter(Context context, int[] typeArray){
			
			this.context = context;
			this.type = typeArray;
			
		}
		 
		@Override
		public int getCount() {
			//return list.size();
			return this.type.length;
		}
		 
		@Override
		public Object getItem(int position) {
			//return list.get(position);
			return null;
		}
		 
		@Override
		public long getItemId(int position) {
			return position;
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater mInflater = LayoutInflater.from(context);
			//产生一个View
			View view = null;
			//根据type不同的数据类型构造不同的View
			if (type[position]==1)
			{
				view = mInflater.inflate(R.layout.transact_statistics_item_layout, null);
			}
			else if (type[position]==0)
			{
				view = mInflater.inflate(R.layout.transact_statistics_title_layout, null);
			}
			else if (type[position]==2)
			{
				view = mInflater.inflate(R.layout.transact_statistics_bottom_layout, null);
			}

			return view;
		}
		
	}
}
