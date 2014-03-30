package org.ezool.iqx.fragments;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.ezool.iqx.MainActivity;
import org.ezool.iqx.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * �ֱ�category�Ļ��ࣨ��Ӧ����໬�˵��е�һ�
 *
 */
public class CategoryBaseFragment extends Fragment {
	
	private static String TAB_DATA_PREFIX = "TAB_DATA_";
	
	/** �ڲ���ͼ  */
	private View innerView;
	
	/** ��fragment�����ݡ���λ��tabͨ��MainActivity���л�ȡ */
	private Map<String, Serializable> tabsData = new HashMap<String, Serializable>();
	
	/** ��fragment����tab�ĸ�����ʾ״̬����Ϣ������Ϣ�����б��棬�����������ĸ���tab���ü�ʹ�� */
	private Map<String, Map<String, Object>> displayData = new HashMap<String, Map<String, Object>>();
	
	public CategoryBaseFragment() { 
		setRetainInstance(true);
	}
	
	/**
	 * �����ڲ�View
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// tab��ͼ��adapter
		CategoryBaseAdapter adapter = new CategoryBaseAdapter(this.getChildFragmentManager());
		
		// �����ڲ���ͼ��frame
		View innerView = inflater.inflate(R.layout.category_base_tabs, container, false);
		ViewPager pager = (ViewPager)innerView.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)innerView.findViewById(R.id.indicator);
        indicator.setViewPager(pager);	
        
        // �����ڲ�������ͼ
        this.innerView = innerView;
        
        // ��Ե�ǰ��ʾtab��λ�õ����໬�˵�
        MainActivity parentActivity = (MainActivity)this.getActivity();
        this.setSlidingMenu(parentActivity.getSlidingMenu());
        
        this.setViewPager(pager);
        
        // �ؽ�����tab�Ѿ������õ�data������еĻ���
        // ��Щdata��onSaveInstanceState(Bundle outState)�б�����
        if (savedInstanceState != null)
        {
	        for(int i = 0; i < this.getTabsCount(); ++i)
	        {
	        	if (savedInstanceState.containsKey(this.getTabDataKey(i)))
	        	{
	        		this.tabsData.put(this.getTabDataKey(i), 
	        				savedInstanceState.getSerializable(this.getTabDataKey(i)));
	        	}
	        }
        }
        
		return innerView;
	}
	
	
	/**
	 * ��дondetach
	 * android.support.v4.app.Fragment��BUG����
	 * �������������
	 * http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��fragment����ʱ����ʱ�������tab������
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (this.tabsData != null)
		{
			 Set<Entry<String, Serializable>> dataSet = this.tabsData.entrySet();
			 for(Entry<String, Serializable> entry : dataSet)
			 {
				 outState.putSerializable(entry.getKey(), entry.getValue());
			 }
		}
	}
	
	/**
	 * �����ڱ���tab����ʱ��keyֵ
	 * @param position tabλ�ã���0��ʼ
	 * @return keyֵ
	 */
	private String getTabDataKey(int position)
	{
		return TAB_DATA_PREFIX + position;
	}
	
	/**
	 * ����tab��ʾ����
	 * ��MainActivity����
	 * @param position tabλ�ã���0��ʼ
	 * @param data tab��ʾ����
	 */
	public void setTabData(int position, Serializable data)
	{
		this.tabsData.put(this.getTabDataKey(position), data);
	}
	
	/**
	 * ȡ��tab��ʾ����
	 * ��MainActivity����
	 * @param position tabλ�ã���0��ʼ
	 * @return tab��ʾ���ݡ����û�������򷵻�null
	 */
	public Serializable getTabData(int position)
	{
		String k = this.getTabDataKey(position);

		if (this.tabsData.containsKey(k))
		{
			return this.tabsData.get(k);
		}

		return null;
	}
	
	/**
	 * ����tab��ʾ�õ���Ϣ
	 * ��MainActivity����
	 * @param position tabλ�ã���0��ʼ
	 * @param key KEYֵ
	 * @param data ��ʾ�õ���Ϣ
	 */
	public void setTabDisplayData(int position, String key, Object data)
	{
		if (this.displayData.containsKey(this.getTabDataKey(position)))
		{
			Map<String, Object> dispData = this.displayData.get(this.getTabDataKey(position));
			dispData.put(key, data);
		}
		else
		{
			Map<String, Object> dispData = new HashMap<String, Object>();
			dispData.put(key, data);
			this.displayData.put(this.getTabDataKey(position), dispData);
		}
	}
	
	
	/**
	 * �õ�tab��ʾ�õ���Ϣ
	 * ��MainActivity����
	 * @param position tabλ�ã���0��ʼ
	 * @param key KEYֵ
	 * @return ��ʾ�õ���Ϣ�����û����Ϣ�򷵻�null
	 */
	public Object getTabDisplayData(int position, String key)
	{
		if (this.displayData.containsKey(this.getTabDataKey(position)))
		{
			Map<String, Object> dispData = this.displayData.get(this.getTabDataKey(position));
			return dispData.get(key);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * �໬�˵�������
	 * �����ڵ�һ�������һ��tabʱ���໬�˵��Ķ������б仯
	 * @param menu Ŀ��໬�˵�
	 */
	private void setSlidingMenu(final SlidingMenu menu)
	{
		if (this.innerView != null)
		{
			TabPageIndicator indicator = (TabPageIndicator)innerView.findViewById(R.id.indicator);
			indicator.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageScrollStateChanged(int arg0) { }

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) { }

				/**
				 * ��ĳ��page��ѡ����ʾʱ����
				 * 
				 * @param position ��ѡ����tabλ�ã���0��ʼ
				 */
				@Override
				public void onPageSelected(int position) {
					// ���ֻ��һ��tab�������һ�������
					/*if (getTabsCount() <= 1)
					{
						menu.setMode(SlidingMenu.LEFT_RIGHT);
						menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					}
					else
					{
						// ����ǵ�һ���������һ��tab����໬�˵�ֻ��һ������
						if (position == 0 || position == (getTabsCount() - 1))
						{
							menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
							if (position == 0)
							{
								menu.setMode(SlidingMenu.LEFT);
							}
							else
							{
								menu.setMode(SlidingMenu.RIGHT);
							}
						}
						// ��������������Ҳ໬�˵�������
						else
						{
							menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
							menu.setMode(SlidingMenu.LEFT_RIGHT);
						}
					}*/
				}

			});
			
			// ��ǰ��ѡtabΪ��һ��
			indicator.setCurrentItem(0);
			
			// �������ò�Ʋ��ܴ���onPageSelected�¼������������ֶ���������������
			/*if (getTabsCount() <= 1)
			{
				menu.setMode(SlidingMenu.LEFT);
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			}
			else
			{
				menu.setMode(SlidingMenu.LEFT_RIGHT);
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			}*/
		}
			
	}
	
	/**
	 * ���������˽�����marginʱ�Ĳ໬�˵�
	 * ���������������ָ��viewpager�ϵĻ����켣�������ʵ�ʹ��ʱ�ڴ򿪲໬�˵�
	 * @param pg viewpager
	 */
	private void setViewPager(final ViewPager pg)
	{
		pg.setOnTouchListener(new OnTouchListener() {
			
			// ���ڱ�����ͬʱ������move��up�¼���������Ҫ����move��ǰһ��������
			// move1->move2->up
			// ����������¼������У�move2��up������ͬ��X����
			// Ϊ�˱����������������������previousOnClickX
			private float onClickX = 0; 
			private float previousOnClickX = 0;
			private int tabCount = getTabsCount();
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e("ACTION_DOWN", "ACTION_DOWN");
					previousOnClickX = onClickX;
					onClickX = event.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e("ACTION_MOVE", "ACTION_MOVE");
					previousOnClickX = onClickX;
					onClickX = event.getX();
					break;
				case MotionEvent.ACTION_UP:
					Log.e("ACTION_UP", "ACTION_UP");
					if((tabCount - 1) == pg.getCurrentItem()) { // Ϊ���һ��ʱ
						if(previousOnClickX > event.getX()) { // ���󻬶����ұ�����viewֱ�Ӵ��Ҳ໬�˵���
							((MainActivity) getActivity()).getSlidingMenu().showSecondaryMenu();
						}
					} 
					
					if(0 == pg.getCurrentItem()) {
						if(previousOnClickX < event.getX()) { // ���һ������������viewֱ�Ӵ���໬�˵���
							((MainActivity) getActivity()).getSlidingMenu().showMenu();
						}
					}
					onClickX = 0; // ��ԭ����
					previousOnClickX = 0;
					break;
				default:
					break;
				}
				return false;
			}
		});
	}
	
	/**
	 * ����ָ��λ��tab�ı��⣬�˴�ֻ��ʾ��
	 * �˷�����������������д
	 * @param pos tab��λ�ã���0��ʼ
	 * @return tab�ı���
	 */
	//TODO:��Ҫ�ĳ�abstract
	protected String getTabsTitle(int pos)
	{
		return "�ҵı��� " + pos;
	}
	
	/**
	 * �����ܹ�tab�ĸ������˴�ֻ��ʾ��
	 * �˷�����������������д
	 * @return tab�ĸ���
	 */
	//TODO:��Ҫ�ĳ�abstract
	protected int getTabsCount()
	{
		return 7;
	}
	
	/**
	 * ����ָ��tabλ�õ�fragment
	 * �������������ÿ�ܵ�getFragmentByTabIndex��
	 * ���ڷ��ص�fragment�����ô�fragment��tabλ�ã������Լ�ȡ����ʾ����ʱʹ�ã�
	 * @param position tabλ��
	 * @return
	 */
	private Fragment getFragmentByTabIndexImpl(int position)
	{
		ViewBaseFragment f = this.getFragmentByTabIndex(position);
		if (f != null)
		{
			f.setIndicatorPosition(position);
			f.setContainerFragment(this);
		}
		
		return f;
	}
	
	/**
	 * ����ָ��tabλ�õ�fragment
	 * ������Ӧ��������������
	 * @param position tabλ��
	 * @return
	 */
	//TODO:��Ҫ�ĳ�abstract
	protected ViewBaseFragment getFragmentByTabIndex(int position)
	{
		//if (position < 1)
    	//{
    	//	return TestFragment.newInstance(new Integer(position).toString());
    	//}
    	//else
    	{
    		//return new PullToRefreshBaseFragment();
    		TabhostBaseFragment f = new TabhostBaseFragment();
    		return f;
    	}
	}
	
	/**
	 * tabs��������
	 *
	 */
    private class CategoryBaseAdapter extends FragmentPagerAdapter {
        public CategoryBaseAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	return getFragmentByTabIndexImpl(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTabsTitle(position);
        }

        @Override
        public int getCount() {
            return getTabsCount();
        }
    }
	
}
