package org.ezool.iqx.views;

import org.zool.iqx.config.ActionConfigurations;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * ��������/��ֹ����������һ��������������scroll��ͼ
 * ĿǰҲû�õ�
 * �������һ��listview̫���ˡ�����
 *
 */
public class SwitchablePullToRefreshScrollView extends PullToRefreshScrollView{
	
	public SwitchablePullToRefreshScrollView(Context context) {
		super(context);
	}

	public SwitchablePullToRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwitchablePullToRefreshScrollView(Context context, Mode mode) {
		super(context, mode);
	}

	public SwitchablePullToRefreshScrollView(Context context, Mode mode, AnimationStyle style) {
		super(context, mode, style);
	}

	/**
	 * ���������е�isPullToRefreshEnabled������/ʹ������ˢ��
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (ActionConfigurations.getInstance().isPullToRefreshEnabled())
		{
			return super.onInterceptTouchEvent(event);
		}
		else
		{
			return false;
		}
	}

	/**
	 * ���������е�isPullToRefreshEnabled������/ʹ������ˢ��
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (ActionConfigurations.getInstance().isPullToRefreshEnabled())
		{
			return super.onTouchEvent(event);
		}
		else
		{
			return false;
		}
	}
}
