package com.example.counters.counters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LayoutToDrawableConverter {
	
	public static Drawable convertToImage(Context context, int count, int drawableId) {
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.badge_with_counter_icon, null);
		((ImageView) view.findViewById(R.id.icon_badge)).setImageResource(drawableId);
		TextView textView = view.findViewById(R.id.counter);
		if (count == 0) {
			textView.setVisibility(View.GONE);
		} else {
			textView.setText(String.valueOf(count));
		}
		
		view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
		             View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		
		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);
		return new BitmapDrawable(context.getResources(), bitmap);
	}
}
