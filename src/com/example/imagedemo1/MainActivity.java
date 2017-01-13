package com.example.imagedemo1;

import java.util.ArrayList;

import com.example.imagedemo.R;
import com.example.imagedemo.R.id;
import com.example.imagedemo.R.layout;
import com.example.photowatch.ImagePagerActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * 简单用法
 *
 */
public class MainActivity extends Activity {

	private ArrayList<String> urls2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.listview);
		initData();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ImagePagerActivity.class);
				// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
				intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
				intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 1);
				startActivity(intent);
			}
		});


	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		urls2 = new ArrayList<String>();
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698871_3655.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
		urls2.add("http://pic.w2bc.com/upload/201505/19/201505191035551206.jpg");
		urls2.add("http://avatar.csdn.net/6/1/1/2_wuto_.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_7507.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698865_3560.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
		urls2.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");

	}
}
