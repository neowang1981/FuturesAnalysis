package org.zool.iqx.config;

import org.ezool.iqx.MainActivity;
import org.ezool.iqx.events.EventBusWrapper;
import org.ezool.iqx.events.MainActivitySlidingEnableChangedEvent;

/**
 * �������ù�����
 * @author WANGJI
 */
public class ActionConfigurations {
	
	/**
	 * ���캯��Ϊ˽�У�����singletonģʽ��ȫ��Ψһ
	 */
	private ActionConfigurations()
	{}
	
	/** Ψһ��instance */
	private static ActionConfigurations instance = null;
	
	/********************** �����������Ƿ��������һ����Ĺ��� **************************/
	
	/** ���һ����Ƿ����� */
	private boolean isScrollActionEnabled = true;
	
	/**
	 * �õ�ȫ��instance
	 * @return ȫ��instance
	 */
	public static ActionConfigurations getInstance()
	{
		if (ActionConfigurations.instance == null)
		{
			ActionConfigurations.instance = new ActionConfigurations();
		}
		
		return ActionConfigurations.instance;
	}

	/**
	 * ���һ����Ƿ�����
	 * @return TRUE������    FALSE��������
	 */
	public boolean isScrollActionEnabled() {
		return isScrollActionEnabled;
	}

	/**
	 * �������һ����Ƿ�����
	 * @param isScrollActionEnabled �Ƿ�����
	 */
	public void setScrollActionEnabled(boolean isScrollActionEnabled) {
		this.isScrollActionEnabled = isScrollActionEnabled;
	}
	
	/********************** �����������Ƿ���Բ໬���� **************************/
	
	/** �Ƿ���Բ໬��flag */
	private boolean isMainSlidingEnabled = true;
	
	/**
	 * ������Ĳ໬�Ƿ�����
	 * @return TRUE������  FALSE��������
	 */
	public boolean isMainSlidingEnabled() {
		return this.isMainSlidingEnabled;
	}

	/**
	 * ����������Ĳ໬�Ƿ�����
	 * @param isMainSlidingEnabled�Ƿ�����
	 */
	public void setMainSlidingEnabled(boolean isMainSlidingEnabled) {
		this.isMainSlidingEnabled = isMainSlidingEnabled;
		
		// ������MainActivity�����¼�
		MainActivitySlidingEnableChangedEvent evnt = new MainActivitySlidingEnableChangedEvent();
		evnt.setSlidingEnabled(this.isMainSlidingEnabled);
		EventBusWrapper.post(evnt);
	}
	

	/********************** �����������Ƿ������������ˢ�� **************************/
	
	/** �Ƿ��������ˢ�� */
	private boolean isPullToRefreshEnabled = true;
	
	/**
	 * �Ƿ��������ˢ��
	 * @return TRUE:��������ˢ��  FALSE������������ˢ��
	 */
	public boolean isPullToRefreshEnabled() {
		return isPullToRefreshEnabled;
	}

	/**
	 * �����Ƿ��������ˢ��
	 * @param isPullToRefreshEnabled �Ƿ����ˢ��
	 */
	public void setPullToRefreshEnabled(boolean isPullToRefreshEnabled) {
		this.isPullToRefreshEnabled = isPullToRefreshEnabled;
	}
}
