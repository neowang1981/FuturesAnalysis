package org.ezool.iqx.fragments.category;

import org.ezool.iqx.fragments.CategoryBaseFragment;
import org.ezool.iqx.fragments.PullToRefreshListBaseFragment;
import org.ezool.iqx.fragments.PullToRefreshScrollBaseFragment;
import org.ezool.iqx.fragments.TabhostBaseFragment;
import org.ezool.iqx.fragments.TabhostWithRefreshableViewBaseFragment;
import org.ezool.iqx.fragments.ViewBaseFragment;

import android.support.v4.app.Fragment;

/**
 * Ʒ�ַ���
 */
public class TypeAnalysis extends CategoryBaseFragment {
	
	// tab����
	private final String[] titles= new String[]
			{
				"Ʒ�ֶ��ʤ��",
				"�ɽ��ṹ",
				"�ֲֽṹ",
				"Ʒ��ӯ��",
				"Ʒ�ֶ�ճɽ�",
				"Ʒ�ֶ��ӯ��",
				"Ʒ�ֱ���"
			};
	
	/**
	 * ����ָ��λ��tab�ı���
	 * �����Ը���
	 * @param pos tab��λ�ã���0��ʼ
	 * @return tab�ı���
	 */
	protected String getTabsTitle(int pos)
	{
		return this.titles[pos];
	}
	
	/**
	 * �����ܹ�tab�ĸ������˴�ֻ��ʾ��
	 * �����Ը���
	 * @return tab�ĸ���
	 */
	protected int getTabsCount()
	{
		return this.titles.length;
	}
	
	/**
	 * ����ָ��tabλ�õ�fragment
	 * �����Ը���
	 * @param position tabλ��
	 * @return tab��fragment
	 */
	protected ViewBaseFragment getFragmentByTabIndex(int position)
	{
		if (position == 1)
		{
			TabhostWithRefreshableViewBaseFragment f = 
				new TabhostWithRefreshableViewBaseFragment();
			return f;
		}
		else
		{
			return new PullToRefreshListBaseFragment();
		}
	}
}
