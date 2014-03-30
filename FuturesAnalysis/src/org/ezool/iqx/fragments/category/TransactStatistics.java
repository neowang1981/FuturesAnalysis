package org.ezool.iqx.fragments.category;

import org.ezool.iqx.fragments.CategoryBaseFragment;
import org.ezool.iqx.fragments.PullToRefreshListBaseFragment;
import org.ezool.iqx.fragments.TabhostWithRefreshableViewBaseFragment;
import org.ezool.iqx.fragments.TransactStatisticsDetailFragment;
import org.ezool.iqx.fragments.ViewBaseFragment;

/**
 * ����ͳ��
 */
public class TransactStatistics extends CategoryBaseFragment{
	
	// tab����
	private final String[] titles= new String[]
			{
				"����ͳ��"
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
		// ��categoryֻ��һ����tab������ͳ��
		if (position == 0)
		{
			return new TransactStatisticsDetailFragment();
		}
		else
		{
			return null;
		}
	}
}
