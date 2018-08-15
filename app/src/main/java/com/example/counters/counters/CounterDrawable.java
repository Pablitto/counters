package com.example.counters.counters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class CounterDrawable extends Drawable {
	
	private Paint mBadgePaint;
	private Paint mTextPaint;
	private Rect mTxtRect = new Rect();
	
	private String mCount = "";
	private boolean mWillDraw;
	
	private Context mContext;
	
	public CounterDrawable(Context context) {
		
		mContext = context;
		
		float mTextSize = context.getResources()
		                         .getDimension(R.dimen.counter_text_size);
		
		mBadgePaint = new Paint();
		mBadgePaint.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.counter_background_color));
		mBadgePaint.setAntiAlias(true);
		mBadgePaint.setStyle(Paint.Style.FILL);
		
		mTextPaint = new Paint();
		mTextPaint.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.counter_text_color));
		mTextPaint.setTypeface(Typeface.DEFAULT);
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		if (!mWillDraw) {
			return;
		}
		float radius = mContext.getResources()
		                       .getDimension(R.dimen.counter_badge_radius);
		float counterLeftMargin = mContext.getResources()
		                                  .getDimension(R.dimen.counter_left_margin);
		
		float horizontalPadding = mContext.getResources()
		                                  .getDimension(R.dimen.counter_text_horizontal_padding);
		float verticalPadding = mContext.getResources()
		                                .getDimension(R.dimen.counter_text_vertical_padding);
		
		mTextPaint.getTextBounds(mCount, 0, mCount.length(), mTxtRect);
		float textHeight = mTxtRect.bottom - mTxtRect.top;
		float textWidth = mTxtRect.right - mTxtRect.left;
		
		float badgeWidth = Math.max(textWidth + 2 * horizontalPadding, 2 * radius);
		float badgeHeight = Math.max(textHeight + 2 * verticalPadding, 2 * radius);
		
		canvas.drawCircle(counterLeftMargin + radius, radius, radius, mBadgePaint);
		canvas.drawCircle(counterLeftMargin + radius, badgeHeight - radius, radius, mBadgePaint);
		canvas.drawCircle(counterLeftMargin + badgeWidth - radius, badgeHeight - radius, radius, mBadgePaint);
		canvas.drawCircle(counterLeftMargin + badgeWidth - radius, radius, radius, mBadgePaint);
		canvas.drawRect(counterLeftMargin + radius, 0, counterLeftMargin + badgeWidth - radius, badgeHeight, mBadgePaint);
		canvas.drawRect(counterLeftMargin, radius, counterLeftMargin + badgeWidth, badgeHeight - radius, mBadgePaint);
		
		// for API 21 and more:
		//canvas.drawRoundRect(counterLeftMargin, 0, counterLeftMargin + badgeWidth, badgeHeight, radius, radius, mBadgePaint);
		
		canvas.drawText(mCount, counterLeftMargin + badgeWidth / 2, verticalPadding + textHeight, mTextPaint);
	}
	
	public void setCount(String count) {
		mCount = count;
		
		mWillDraw = !count.equalsIgnoreCase("0");
		invalidateSelf();
	}
	
	@Override
	public void setAlpha(int alpha) {
		// do nothing
	}
	
	@Override
	public void setColorFilter(ColorFilter cf) {
		// do nothing
	}
	
	@Override
	public int getOpacity() {
		return PixelFormat.UNKNOWN;
	}
}
