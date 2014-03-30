package org.ezool.iqx;

import org.ezool.iqx.fragments.MenuListFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * ����ʾ��Ļ���
 * @author WANGJI
 */
public class BaseActivity extends SlidingFragmentActivity {

	/** �������Դ�� */
	private int mTitleRes;
	
	/** ��໬���˵���fragment */
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		this.mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(this.mTitleRes);

		// �趨��໬���˵��Ĳ���
		this.setBehindContentView(R.layout.menu_frame);
		
		// ��MenuListFragment���滻�����е�ռλ��
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			this.mFrag = new MenuListFragment();
			t.replace(R.id.menu_frame, this.mFrag);
			t.commit();
		} else {
			this.mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// ��໬�˵�����
		SlidingMenu sm = this.getSlidingMenu();
		//sm.setShadowWidthRes(R.dimen.shadow_width);
		//sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.75f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}