package org.ezool.iqx.fragments;

import org.ezool.iqx.MainActivity;
import org.ezool.iqx.R;
import org.ezool.iqx.events.EventBusWrapper;
import org.ezool.iqx.fragments.category.TransactStatistics;
import org.ezool.iqx.fragments.category.TypeAnalysis;
import org.zool.iqx.config.ActionConfigurations;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ���Ļ����˵�����Fragment
 * ����ѡ������Ļ��ҵ�����
 */
public class MenuListFragment extends ListFragment{
	
	private int previousSelectedList = -1;
	
	private MenuItem[] menuInfo = new MenuItem[] {
		new MenuItem("����ͳ��", android.R.drawable.ic_menu_search, TransactStatistics.class),
		new MenuItem("Ʒ�ַ���", android.R.drawable.ic_menu_search, TypeAnalysis.class),
		new MenuItem("CategoryBaseFragment", android.R.drawable.ic_menu_search, CategoryBaseFragment.class),
		new MenuItem("Sample List2", android.R.drawable.ic_menu_search, null)
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
	 * ���������滻����ʾ�����е�fragment�������ص�ǰ�Ļ����˵�
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		//ȡ��������Activity
		MainActivity ma = (MainActivity)this.getActivity();
		
		//����ͬ�Ĳ˵��ɶ������
		if (position == this.previousSelectedList)
		{
			ma.switchContent(null);
		}
		else
		{
			// ��ס��ǰ��ѡ�˵���λ��
			this.previousSelectedList = position;
			
			MenuItem mi = this.menuInfo[position];
			
			// sanity check
			// �����ʵ������class�Ҵ�class��������CategoryBaseFragment�Ļ�������ʾ��fragment����������ʾ
			if (mi.categoryFragment != null && CategoryBaseFragment.class.isAssignableFrom(mi.categoryFragment))
			{
				CategoryBaseFragment fragInst = this.loadCategoryFragmentInstance(position);
				
				//�����û��ʵ���������ɲ�����˵���Ϣ��
				if (fragInst == null)
				{
					try {
						fragInst = (CategoryBaseFragment)mi.categoryFragment.newInstance();
						this.saveCategoryFragmentInstance(position, fragInst);
					} catch (java.lang.InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//���Ѿ�����ʵ�������ȡ��
				else
				{
					//ò��û������
				}
				
				//������fragment���滻����
				ma.switchContent(fragInst);
			}
		}
		
	}
	
	/**
	 * ���������ӿڵ�˵��
	 * ������Ҫ�����ɵĲ˵���Ӧ��fragment������������Ϊ���ǰ��������и�����tab��ʾ����Ҫ����Ϣ
	 * ������Щfragment��Ӧ��ÿ�ζ���new��������ֻӦ��newһ��
	 * ���Ǳ�fragment���ܱ����ٺ��������ɣ����ڱ�Ӧ���У�Ψһ���ᱻ�ݻٵĶ�����MainActivity
	 * ����������������ʵ�����ǰѲ�����fragment���浽MainActivity��
	 */
	/**
	 * ���˵���Ӧ��category���浽MainActivity
	 * @param position �˵�λ�ã���0��ʼ
	 * @param f ��Ӧ��Ҫ�����fragment
	 */
	private void saveCategoryFragmentInstance(int position, CategoryBaseFragment f)
	{
		// ֱ�ӵ���MainActivity�Ľӿڷ���
		((MainActivity)this.getActivity()).setCategoryBaseFragment(position, f);
	}
	
	/**
	 * ��ȡ�˵���Ӧ��category
	 * @param position �˵�λ�ã���0��ʼ
	 * @return ��Ӧ�ı������fragment�����û���򷵻�null
	 */
	private CategoryBaseFragment loadCategoryFragmentInstance(int position)
	{
		// ֱ�ӵ���MainActivity�Ľӿڷ���
		return ((MainActivity)this.getActivity()).getCategoryBaseFragment(position);
	}

	/**
	 * �˵���Ϣ��
	 *
	 */
	private class MenuItem {
		/** �˵����� */
		public String tag;
		/** �˵�ͼ�� */
		public int iconRes;
		/** ����ʱ����ʾ��Class */
		public Class categoryFragment;
		
		public MenuItem(String tag, int iconRes, Class c) {
			this.tag = tag; 
			this.iconRes = iconRes;
			this.categoryFragment = c;
		}
	}

	/**
	 * ��໬���˵�����������
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
