package org.ezool.iqx;


import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Bitmap.Config;
import android.os.Looper;
import android.util.Log;

public class FuturesAnalysisApplication extends Application{
		private static final String TAG = FuturesAnalysisApplication.class.getName();

		// ������Activity����
		public List<Activity> mActivityList = new ArrayList<Activity>();

		/** ��������ʱ�Ĵ��� */
		@Override
		public void onCreate() {
			super.onCreate();
			// ����Ӧ�ü��쳣ʱ�Ĵ���
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread thread, Throwable throwable) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// ��������ǿ���˳��ĶԻ���
						if (mActivityList.size() > 0) {
							Looper.prepare();
							new AlertDialog.Builder(getCurrentActivity())
									.setTitle(R.string.app_name)
									.setMessage(R.string.err_fatal)
									.setPositiveButton(
											R.string.confirm,
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// ǿ���˳�����
													finish();
												}
											}).show();
							Looper.loop();
						}
					}
				}).start();

				// ����LOG
				Log.e(TAG, throwable.getMessage(), throwable);
				}
			});
			init();
		}

		private void init()  {
			try {
				// �˴�Ϊ�˼ӿ��ٶ�
				Class.forName("org.ezool.iqx.events.EventBusWrapper");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
			
			/*DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(true)
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT) 
			.bitmapConfig(Config.RGB_565)  // ��ֹ�ڴ���� ��Bitmap.Config.ARGB_8888
			//.showImageOnLoading(R.drawable.load_flower)   //Ĭ��ͼƬ       
			//.showImageOnFail(R.drawable.k2k2k2k)// ����ʧ����ʾ��ͼƬ
			//.displayer(new RoundedBitmapDisplayer(5))  //Բ�ǣ�����Ҫ��ɾ��
			.build();


	


			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			//.memoryCacheExtraOptions(480, 800)  // �������ڴ��ͼƬ�Ŀ�͸߶�
			//.discCacheExtraOptions(480, 800, CompressFormat.PNG, 70,null) //CompressFormat.PNG���ͣ�70������0-100��
			.memoryCache(new WeakMemoryCache()) 
			.memoryCacheSize(2 * 1024 * 1024)  //���浽�ڴ���������
			.discCacheSize(100 * 1024 * 1024)  //���浽�ļ����������
			.discCacheFileCount(1000)  //�ļ�����
			.defaultDisplayImageOptions(options).  //�����options����һЩ��������
			build();
			
			ImageLoader.getInstance().init(config);  //��ʼ��
			*/
		}

		/**
		 * �رճ������е�Activity
		 */
		public void clearActivityList() {
			for (int i = 0; i < mActivityList.size(); i++) {
				mActivityList.get(i).finish();
			}
			mActivityList.clear();
		}

		/**
		 * �ر�ʱ�Ĵ���
		 */
		public void finish() {
			// �رճ������е�Activity
			clearActivityList();

			// �˳�
			System.exit(0);
		}

		/**
		 * ��õ�ǰ��ǰ�˵�Activity
		 */
		public Activity getCurrentActivity() {
			if (mActivityList.size() > 0) {
				return mActivityList.get(mActivityList.size() - 1);
			}
			return null;
		}
}

