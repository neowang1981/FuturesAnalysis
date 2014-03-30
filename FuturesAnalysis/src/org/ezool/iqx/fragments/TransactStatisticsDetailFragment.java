package org.ezool.iqx.fragments;

import org.ezool.iqx.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/**
 * ����ͳ�Ƶ�tab fragment
 * һ��tab������ͳ��
 * ����tab������ͳ��
 *
 */
public class TransactStatisticsDetailFragment extends PullToRefreshListBaseFragment{
	
	/**
	 * ����listview��������
	 */
	@Override
	protected ListAdapter getListAdapter(Context ctx)
	{
		return new TransactStatisticsListViewAdapter(
				ctx, new int[]{0, 1, 1, 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 1, 2});
	}
	
	private class TransactStatisticsListViewAdapter extends BaseAdapter{
		
		//����Դ
		private Context context;
		private int []type;
	 
		//���캯��
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
			//����һ��View
			View view = null;
			//����type��ͬ���������͹��첻ͬ��View
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
