package com.example.counters.counters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	
	private int mCounterValue1 = 0;
	private int mCounterValue2 = 0;
	private int mCounterValue3 = 0;
	private int mCounterValue4 = 0;
	
	private LayerDrawable mIcon2;
	
	private ImageView mIcon3;
	private TextView mCounterText3;
	
	private ImageView mIcon4;
	private TextView mCounterText4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		
		// second counter
		initSecondCounter(menu);
		
		initThirdCounter(menu);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		MenuItem menuItem = menu.findItem(R.id.action_counter_1);
		
		// first counter
		menuItem.setIcon(LayoutToDrawableConverter.convertToImage(this, mCounterValue1, R.drawable.icon));
		
		// third counter
		addFourthCounter(menu, this);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_counter_1:
				updateFirstCounter(mCounterValue1 + 1);
				return true;
			case R.id.action_counter_2:
				updateSecondCounter(++mCounterValue2);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void updateFirstCounter(int newCounterValue) {
		mCounterValue1 = newCounterValue;
		invalidateOptionsMenu();
	}
	
	private void initSecondCounter(Menu menu){
		MenuItem menuItem = menu.findItem(R.id.action_counter_2);
		mIcon2 = (LayerDrawable) menuItem.getIcon();
		
		updateSecondCounter(mCounterValue2);
	}
	
	private void initThirdCounter(Menu menu){
		MenuItem counterItem = menu.findItem(R.id.action_counter_3);
		View counter = counterItem.getActionView();
		
		mIcon3 = counter.findViewById(R.id.icon_badge);
		mCounterText3 = counter.findViewById(R.id.counter);
		
		counter.setOnClickListener(v -> onThirdCounterClick());
		updateThirdCounter(mCounterValue3);
	}
	
	private void addFourthCounter(Menu menu, Context context) {
		
		View counter = LayoutInflater.from(context)
		                             .inflate(R.layout.badge_with_counter, null);
		counter.setOnClickListener(v -> onFourthCounterClick());
		mIcon4 = counter.findViewById(R.id.icon_badge);
		mCounterText4 = counter.findViewById(R.id.counter);
		MenuItem counterMenuItem = menu.add(context.getString(R.string.counter));
		counterMenuItem.setActionView(counter);
		counterMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
		updateFourthCounter(mCounterValue4);
	}
	
	private void onThirdCounterClick(){
		updateThirdCounter(++mCounterValue3);
	}
	
	private void onFourthCounterClick(){
		updateFourthCounter(++mCounterValue4);
	}
	
	private void updateSecondCounter(int newCounterValue) {
		
		CounterDrawable badge;
		
		Drawable reuse = mIcon2.findDrawableByLayerId(R.id.ic_counter);
		if (reuse != null && reuse instanceof CounterDrawable) {
			badge = (CounterDrawable) reuse;
		} else {
			badge = new CounterDrawable(this);
		}
		
		badge.setCount(String.valueOf(newCounterValue));
		mIcon2.mutate();
		mIcon2.setDrawableByLayerId(R.id.ic_counter, badge);
	}
	
	private void updateThirdCounter(int newCounterValue) {
		
		if (mIcon3 == null || mCounterText3 == null) {
			return;
		}
		
		if (newCounterValue == 0) {
			mIcon3.setImageResource(R.drawable.icon);
			mCounterText3.setVisibility(View.GONE);
		} else {
			mIcon3.setImageResource(R.drawable.icon);
			mCounterText3.setVisibility(View.VISIBLE);
			mCounterText3.setText(String.valueOf(newCounterValue));
		}
	}
	
	private void updateFourthCounter(int newCounterValue) {
		
		if (mIcon4 == null || mCounterText4 == null) {
			return;
		}
		
		if (newCounterValue == 0) {
			mIcon4.setImageResource(R.drawable.icon);
			mCounterText4.setVisibility(View.GONE);
		} else {
			mIcon4.setImageResource(R.drawable.icon);
			mCounterText4.setVisibility(View.VISIBLE);
			mCounterText4.setText(String.valueOf(newCounterValue));
		}
	}
}
