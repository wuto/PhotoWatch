package com.example.show;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imagedemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class ShowJsImages extends Activity {
	private NoPreloadViewPager mViewPager;
	private ImageView loading_;
	private TextView mPageShwo;
	private int mPosition = 0;

	// private int[] mImgs = new int[] { R.drawable.tbug, R.drawable.a,
	// R.drawable.xx ,R.drawable.a222,R.drawable.a333};

	// private String mImgs[] = {
	// "http://img.my.csdn.net/uploads/201407/17/1405567749_8669.jpg",
	// "http://img1.poplex.cn/isopen/campaign/d/u/8/1459408011899733.jpg",
	// "http://img1.poplex.cn/isopen/campaign/d/u/8/1459408011899733.jpg",
	// "http://img1.poplex.cn/isopen/campaign/d/u/8/1459408011899733.jpg" };
	private ArrayList<String> mImgs;
	private ZoomImageView[] mImageViews;

	ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showStubImage(R.drawable.loading_)
			.showImageOnFail(R.drawable.loading_)
			.showImageForEmptyUri(R.drawable.loading_).cacheOnDisc(true)
			.cacheInMemory(true).build();;
	PagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageLoader.init(ImageLoaderConfiguration
				.createDefault(ShowJsImages.this));
		setContentView(R.layout.vp);

		Intent intent = getIntent();
		mPosition = intent.getIntExtra("position", 0);
		// mPosition=3;
		mImgs = intent.getStringArrayListExtra("imagesUrl");
		mImageViews = new ZoomImageView[mImgs.size()];

		loading_ = (ImageView) findViewById(R.id.loading_);
		mPageShwo = (TextView) findViewById(R.id.tv_page);
		mViewPager = (NoPreloadViewPager) findViewById(R.id.id_viewpager);
		mViewPager.setOnPageChangeListener(mPageChangeListener);
		mViewPager.setOffscreenPageLimit(0);
		updateShowInfo();

		adapter = new PagerAdapter() {

			@Override
			public Object instantiateItem(final ViewGroup container,
					final int position) {
				final ZoomImageView imageView = new ZoomImageView(
						ShowJsImages.this);
				imageLoader.displayImage(mImgs.get(position), imageView,
						options, new SimpleImageLoadingListener() {

							@Override
							public void onLoadingStarted(String imageUri,
									View view) {
								loading_.setVisibility(View.VISIBLE);
							}

							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								loading_.setVisibility(View.GONE);
								container.addView(imageView);
								mImageViews[position] = imageView;
								super.onLoadingComplete(imageUri, view,
										loadedImage);
							}

							@Override
							public void onLoadingFailed(String imageUri,
									View view, FailReason failReason) {
								loading_.setVisibility(View.GONE);
								super.onLoadingFailed(imageUri, view,
										failReason);
							}

						});
				return imageView;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				ZoomImageView imageView = mImageViews[position];
				if (imageView != null) {
					container.removeView(imageView);
				}
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return mImgs.size();
			}

			@Override
			public int getItemPosition(Object object) {
				// TODO Auto-generated method stub
				return POSITION_NONE;
			}

		};
		mViewPager.setAdapter(adapter);
		mViewPager.setCurrentItem(mPosition);

	}

	NoPreloadViewPager.OnPageChangeListener mPageChangeListener = new NoPreloadViewPager.OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			// // Log.d(TAG, "onPageSelected" + position + ", prePosition: "
			// // + prePosition);
			// ImageViewTouch preImageView =
			// mPagerAdapter.views.get(prePosition);
			// if (preImageView != null) {
			// preImageView.setImageBitmapResetBase(
			// preImageView.mBitmapDisplayed.getBitmap(), true);
			// }
			mPosition = position;

			// updateZoomButtonsEnabled();
			updateShowInfo();
			// ZoomImageView zView = (ZoomImageView) mViewPager
			// .getChildAt(position);
			// zView.setinit();
			// /**
			// * 最大值最小值判断
			// */
			// if (scaleFactor * scale < initScale)
			// {
			// scaleFactor = initScale / scale;
			// }
			// if (scaleFactor * scale > SCALE_MAX)
			// {
			// scaleFactor = SCALE_MAX / scale;
			// }
			// /**
			// * 设置缩放比例
			// */
			// mScaleMatrix.postScale(scale, scale, getWidth() / 2,
			// getHeight() / 2);
			// checkBorderAndCenterWhenScale();
			// setImageMatrix(mScaleMatrix);

			// updatePreNextButtonEnable();
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// Log.d(TAG, "onPageScrolled");
			// mOnPagerScoll = true;
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// Log.d(TAG, "onPageScrollStateChanged: " + state);
			// if (state == ViewPager.SCROLL_STATE_DRAGGING) {
			// mOnPagerScoll = true;
			// } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
			// mOnPagerScoll = false;
			// } else {
			// mOnPagerScoll = false;
			// }
		}

	};

	private void updateShowInfo() {
		if (mImgs.size() > 0) {
			mPageShwo.setText(String.format("%d/%d", mPosition + 1,
					mImgs.size()));
			// mPicName.setText(getPositionFileName(mPosition));
		}
	}

}
