package org.ezool.iqx;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ezool.iqx.events.EventBusWrapper;
import org.ezool.iqx.events.MainActivitySlidingEnableChangedEvent;
import org.ezool.iqx.fragments.AppMenuListFragment;
import org.ezool.iqx.fragments.CategoryBaseFragment;
import org.ezool.iqx.fragments.MenuListFragment;
import org.zool.iqx.config.ActionConfigurations;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

/**
 * �����棬�����������໬�˵�
 */
public class MainActivity extends BaseActivity {
	
	/** ��ǰ������ʾ�Ľ��� */
	CategoryBaseFragment currentFragment = null;
	
	/** ���������Ѿ����ڵĽ��� */
	Map<Integer, CategoryBaseFragment> categoryfragments = new HashMap<Integer, CategoryBaseFragment>();
	
	/**
	 * ���캯��
	 * ���û���Ĺ��캯��
	 */
	public MainActivity()
	{
		super(R.string.main_title);
	}
	
	/**
	 * �������
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// ������໬���˵�
		this.getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		this.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		
		// ����������
		setContentView(R.layout.activity_main);
		
		// �����Ҳ໬���˵�
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_two, new AppMenuListFragment()).commit();
		
		// ȡ�õ�ǰ�����Ƿ���Ի������趨�������õ�����
		this.getSlidingMenu().setSlidingEnabled(ActionConfigurations.getInstance().isMainSlidingEnabled());
		
		
		// ������ע�ᵽ�����������Լ���MainActivitySlidingEnableChangedEvent
		// �����EventBus
		EventBusWrapper.register(this);
	}
	
	/**
	 * �趨�������fragment
	 * @param fragment ��������ʾ��fragment������CategoryBaseFragment������
	 */
	public void switchContent(CategoryBaseFragment fragment) {
		
		// �������Ҫ��ʾ��fragment���ǵ�ǰ��fragment���������������滻����
		if (fragment != null)
		{
			// ��ס��ǰ��fragment
			this.currentFragment = fragment;	
			this.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
		}
		
		// ���ز໬�˵�
		this.getSlidingMenu().showContent();
	}

	/**
	 * ����������ʱ����Ҫ��EventBus��ȡ������
	 */
	@Override
	protected void onDestroy()
	{
		// ������Ӽ��������Ƴ���Ϊ����MainActivitySlidingEnableChangedEvent�Ķ���
		EventBusWrapper.unregister(this);
		super.onDestroy();
	}
	
	/**
	 * ���յ������ɷ����ñ�����¼�
	 * @param event �����ɷ����ñ�������¼�
	 */
	public void onEvent(MainActivitySlidingEnableChangedEvent event) {
        this.getSlidingMenu().setSlidingEnabled(event.isSlidingEnabled());
    }
	
	/**
	 * ���õ�ǰ������fragment�е�ĳһ��tab����ʾ����
	 * ������ݱ��ص���������fragment���б��棬�Ա���������fragment����ʱ��ֹ���ٴλָ�ʱ������savedInstanceState��ȡ��
	 * @param position tab���±꣬��0��ʼ
	 * @param data tab��ʾ����
	 * @return
	 */
	public boolean setTabData(int position, Serializable data)
	{
		if (this.currentFragment != null)
		{
			this.currentFragment.setTabData(position, data);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * setTabData�������
	 * @param position tab���±꣬��0��ʼ
	 * @return tab��ʾ���ݡ����û�������򷵻�null
	 */
	public Serializable getTabData(int position)
	{
		if (this.currentFragment == null)
		{
			return null;
		}
		else
		{
			return this.currentFragment.getTabData(position);
		}
	}
	
	
	/**
	 * ���õ�ǰ������fragment�е�ĳһ��tab����ʾ��Ϣ
	 * @param position tab���±꣬��0��ʼ
	 * @param key ��ʾ��keyֵ����tab��view���Զ���
	 * @param data tab��ʾ����
	 * @return
	 */
	public boolean setTabDisplayData(int position, String key, Object data)
	{
		if (this.currentFragment != null)
		{
			this.currentFragment.setTabDisplayData(position, key, data);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * �õ���ǰ������fragment�е�ĳһ��tab����ʾ��Ϣ
	 * @param position tab���±꣬��0��ʼ
	 * @param key ��ʾ��keyֵ����tab��view���Զ���
	 * @return tab��ʾ����
	 */
	public Object getTabDisplayData(int position, String key)
	{
		if (this.currentFragment != null)
		{
			return this.currentFragment.getTabDisplayData(position, key);
		}
		else
		{
			return null;
		}
	}
	
	
	/**
	 * ���������ӿڵ�˵���μ�MenuListFragment�е�saveCategoryFragmentInstance�Լ�loadCategoryFragmentInstance
	 */
	
	/**
	 * ȡ��key��Ӧ��category
	 * @param key
	 * @return category
	 */
	public CategoryBaseFragment getCategoryBaseFragment(int key)
	{
		if (this.categoryfragments.containsKey(Integer.valueOf(key)))
		{
			return this.categoryfragments.get(Integer.valueOf(key));
		}	
		else
		{
			return null;
		}
	}
	
	/**
	 * ����key��Ӧ��category
	 * @param key
	 * @param f category
	 * @return ����ɹ����
	 */
	public boolean setCategoryBaseFragment(int key, CategoryBaseFragment f)
	{
		if (this.categoryfragments.containsKey(Integer.valueOf(key)))
		{
			return false;
		}	
		else
		{
			this.categoryfragments.put(Integer.valueOf(key), f);
			return true;
		}
	}
}
