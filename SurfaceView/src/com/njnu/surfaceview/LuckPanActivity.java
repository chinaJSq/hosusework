package com.njnu.surfaceview;



import com.network.surfaceview.R;

import android.app.Activity;

import android.app.Dialog;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;

import android.widget.Toast;

public class LuckPanActivity extends Activity {

	private LuckyPan mLuckyPan;
	private ImageView mImageStart;
    Boolean myapp = true;
    private LoveLayout mLoveLayout;
    private String[] name = new String[] { "1", "下次再来", "2", "5",
			"10", "15" };
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==0x123) {
				mLoveLayout.addLove();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_luckypan);
		
		LoveInitView();
		mLuckyPan = (LuckyPan) findViewById(R.id.id_luckyPan);
		mLuckyPan = (LuckyPan) findViewById(R.id.id_luckyPan);
		mImageStart = (ImageView) findViewById(R.id.id_start_btn);
		mImageStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mLuckyPan.isStart()) {
					if(myapp){
					    mLuckyPan.LuckyStart(1);
					}else{
						mLuckyPan.LuckyStart2(1);
					}
					mLuckyPan.setmBgBitmap2(BitmapFactory.decodeResource(getResources(),
			R.drawable.stop));
					mImageStart.setImageResource(R.drawable.stop);

				} else {
					if (!mLuckyPan.isShouldEnd()) {
						if(myapp){
						    mLuckyPan.LuckyEnd();
						    handler.postDelayed(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									 Toast.makeText(LuckPanActivity.this, mLuckyPan.text, Toast.LENGTH_SHORT).show();	 
								
								}
							}, 1000);						    
						   
						}else{
							mLuckyPan.LuckyEnd2();
						}
						mLuckyPan.setmBgBitmap2(BitmapFactory.decodeResource(getResources(),
								R.drawable.start));
						mImageStart.setImageResource(R.drawable.start);
						
					}

				}
			}
		});
	}
	private void LoveInitView() {
		mLoveLayout=(LoveLayout)findViewById(R.id.id_love_layout);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true){
						Thread.sleep(1000);
						handler.sendEmptyMessage(0x123);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
