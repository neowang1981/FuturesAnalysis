package org.ezool.iqx.fragments;

import org.ezool.iqx.MainActivity;
import org.ezool.iqx.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * �Ҳ�Ļ����˵�����Fragment
 * ����ѡ��Ӧ�ó����趨����
 */
public class AppMenuListFragment extends ListFragment{

	private MenuItem[] menuInfo = new MenuItem[] {
		new MenuItem("�˳�����", android.R.drawable.ic_menu_search)
	};
		
	/**
	 * ��������ʾ�Ľ��棨ֻ��һ��ListView��
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu_list, null);
	}

	/**
	 * ��ʼ��ListView
	 */
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		MenuAdapter adapter = new MenuAdapter(getActivity());
		for(int i = 0; i < this.menuInfo.length; ++i)
		{
			adapter.add(this.menuInfo[i]);
		}
		this.setListAdapter(adapter);
	}

	/**
	 * ���˵���ĳһ����ʱ���ñ�����
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		//ȡ��������Activity
		MainActivity ma = (MainActivity)this.getActivity();
		
		if (position == 0)
		{
			System.exit(0);
		}
		
	}
	
	/**
	 * �Ҳ�˵���Ϣ��
	 *
	 */
	private class MenuItem {
		/** �˵����� */
		public String tag;
		/** �˵�ͼ�� */
		public int iconRes;
		
		public MenuItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}
	
	/**
	 * �Ҳ໬���˵�����������
	 */
	public class MenuAdapter extends ArrayAdapter<MenuItem> {

		public MenuAdapter(Context context) {
			super(context, 0);
		}

		/**
		 * ȡ��ĳһ�в˵���
		 * ��ȫ����getItem�Ľ��������
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(this.getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(this.getItem(position).tag);
			return convertView;
		}
	}
}
