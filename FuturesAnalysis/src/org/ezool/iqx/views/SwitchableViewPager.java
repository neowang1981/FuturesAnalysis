package org.ezool.iqx.views;

import org.zool.iqx.config.ActionConfigurations;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ���Կ��ƻ�����ViewPager
 * ���ؼ���������CategoryBaseFragment�Ĳ����ļ���
 */
public class SwitchableViewPager extends ViewPager{

	public SwitchableViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwitchableViewPager(Context context) {
		super(context);
	}

	/**
	 * ���������¼�����
	 * ���ȫ�ֶ��������в��������һ�������ֱ�ӷ���FALSE
	 * ���򣬰���ԭ�������
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (ActionConfigurations.getInstance().isScrollActionEnabled())
		{
			return super.onTouchEvent(arg0);
		}
		else
		{
			return false;
		}
	}

	/**
	 * ���ش����¼�
	 * ���ȫ�ֶ��������в��������һ�������ֱ�ӷ���FALSE
	 * ���򣬰���ԭ�������
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (ActionConfigurations.getInstance().isScrollActionEnabled())
		{
			return super.onInterceptTouchEvent(arg0);
		}
		else
		{
			return false;
		}
	}
	
	
}
