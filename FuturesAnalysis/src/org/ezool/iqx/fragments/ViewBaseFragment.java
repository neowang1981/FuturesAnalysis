package org.ezool.iqx.fragments;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ezool.iqx.MainActivity;

import android.support.v4.app.Fragment;

/**
 * ĳһ��tab�Ļ���fragment
 */
abstract public class ViewBaseFragment extends Fragment{
	
	/** ��fragment��tab�е�λ�ã���0��ʼ */
	private int indicatorPosition = -1;
	
	/** ��fragment�����fragment����ض��Ǵ�CategoryBaseFragment������ */
	private CategoryBaseFragment containerFragment;

	/** ͨ�Ŵ����Ľ�� */
	private int communicateResult;
	
	/**
	 * ��ȡ��fragment��tab�е�λ��
	 * @return λ�á���0��ʼ
	 */
	private int getIndicatorPosition() {
		return indicatorPosition;
	}

	/**
	 * ���ñ�fragment��tab�е�λ��
	 * �������ɿ�ܵ��ã�����Ҫ��ʹ���ߵ���
	 */
	public final void setIndicatorPosition(int indicatorPosition) {
		this.indicatorPosition = indicatorPosition;
	}
	
	/**
	 * ȡ�ñ�tab������ʾ������
	 * ���������ɱ�fragment�����ĸ���View����
	 * @return ��ʾ�����ݡ����û�������򷵻�null
	 */
	public final Serializable getViewDataFromSave()
	{
		MainActivity ma = (MainActivity)this.getActivity();
		return ma.getTabData(this.getIndicatorPosition());
	}
	
	/**
	 * ���汾tab������ʾ������
	 * @param data ��Ҫ�������ʾ����
	 * @return true���ɹ� false��ʧ��
	 */
	protected boolean setViewDataToSave(Serializable data)
	{
		MainActivity ma = (MainActivity)this.getActivity();
		return ma.setTabData(this.getIndicatorPosition(), data);
	}
	
	/**
	 * �趨��fragment�����fragment�������ṩCategoryBaseFragment�е�һЩ�ӿ�
	 * @param baseFragment ���fragment
	 */
	public final void setContainerFragment(CategoryBaseFragment baseFragment)
	{
		this.containerFragment = baseFragment;
	}
	
	/**
	 * �õ���fragment�����fragment�������ṩCategoryBaseFragment�е�һЩ�ӿ�
	 * @return ���fragment
	 */
	private final CategoryBaseFragment getContainerFragment()
	{
		return this.containerFragment;
	}
	
	/**
	 * ����tab��ʾ�õ���Ϣ
	 * ���õ��Ǵ���ģʽ
	 * ���������
	 * @param key KEYֵ
	 * @param data ��ʾ�õ���Ϣ
	 */
	protected void setTabDisplayData(String key, Object data)
	{
		this.getContainerFragment().setTabDisplayData(
				this.getIndicatorPosition(), key, data);
	}
	
	
	/**
	 * �õ�tab��ʾ�õ���Ϣ
	 * ���õ��Ǵ���ģʽ
	 * ���������
	 * @param key KEYֵ
	 * @return ��ʾ�õ���Ϣ�����û����Ϣ�򷵻�null
	 */
	protected Object getTabDisplayData(String key)
	{
		return this.getContainerFragment().getTabDisplayData(
				this.getIndicatorPosition(), key);
	}
	
	/**
	 * ��ʾ�����н���
	 */
	abstract protected void showWaitingView();
	
	/**
	 * �رմ����н��沢�ָ���������ʾ���棨���߱���
	 */
	abstract protected void closeWaitingView();
	
	/**
	 * ������ͨ�ŵ������
	 * ���ڻ�ȡ��tab��fragmentר������ʾ����
	 * ����������������б�����
	 * @return ͨ�ź������
	 */
	protected Serializable communicate()
	{
		return null;
	}
	
	/**
	 * ����ͨ�Ŵ���Ľ����
	 * @param code �����
	 */
	protected void setCommunicateResult(int code)
	{
		this.communicateResult = code;
	}
	
	/**
	 * �õ�ͨ�Ŵ���Ľ����
	 * @return �����
	 */
	protected int getCommunicateResult()
	{
		return this.communicateResult;
	}
}
