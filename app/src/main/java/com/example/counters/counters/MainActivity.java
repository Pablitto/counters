package com.example.counters.counters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	
	private int mCounterValue = 0;
	
	private ImageView mIcon;
	private TextView mCounterText;
	
	private ImageView mIcon2;
	private TextView mCounterText2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		initCounterElements(menu);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		MenuItem menuItem = menu.findItem(R.id.action_counter);
		menuItem.setIcon(LayoutToDrawableConverter.convertToImage(this, mCounterValue, R.drawable.icon));
		addActivityCounter(menu, this);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_counter:
				updateCounter(mCounterValue + 1);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void updateCounter(int newCounterValue) {
		mCounterValue = newCounterValue;
		invalidateOptionsMenu();
	}
	
	public void addActivityCounter(Menu menu, Context context) {
		
		View counter = LayoutInflater.from(context)
		                             .inflate(R.layout.badge_with_counter, null);
		counter.setOnClickListener(v -> onThirdCounterClick());
		mIcon = counter.findViewById(R.id.icon_badge);
		mCounterText = counter.findViewById(R.id.counter);
		MenuItem counterMenuItem = menu.add(context.getString(R.string.counter));
		counterMenuItem.setActionView(counter);
		counterMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
		updateThirdCounter(mCounterValue);
	}
	
	public void initCounterElements(Menu menu){
		MenuItem counterItem = menu.findItem(R.id.action_counter_2);
		View counter = counterItem.getActionView();
		
		//mIcon2 = counter.findViewById(R.id.icon);
		//mCounterText2 = counter.findViewById(R.id.counter);
		
		//counter.setOnClickListener(v -> onSecondCounterClick());
	}
	
	private void onThirdCounterClick(){
		updateThirdCounter(mCounterValue ++);
	}
	
	private void onSecondCounterClick(){
		updateThirdCounter(mCounterValue ++);
	}
	
	private void updateThirdCounter(int newCounterValue) {
		
		if (mIcon == null || mCounterText == null) {
			return;
		}
		
		if (newCounterValue == 0) {
			mIcon.setImageResource(R.drawable.icon);
			mCounterText.setVisibility(View.GONE);
		} else {
			mIcon.setImageResource(R.drawable.icon);
			mCounterText.setVisibility(View.VISIBLE);
			mCounterText.setText(String.valueOf(newCounterValue));
		}
	}
	
	private void updateSecondCounter(int newCounterValue) {
		
		if (mIcon == null || mCounterText == null) {
			return;
		}
		
		if (newCounterValue == 0) {
			mIcon2.setImageResource(R.drawable.icon);
			mCounterText2.setVisibility(View.GONE);
		} else {
			mIcon2.setImageResource(R.drawable.icon);
			mCounterText2.setVisibility(View.VISIBLE);
			mCounterText2.setText(String.valueOf(newCounterValue));
		}
	}
	
	
}
