package de.tuete.soundboard.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import de.tuete.soundboard.helper.ViewHolderMain;

public class MainListTask extends AsyncTask<ViewHolderMain, Void, Bitmap>{
	
	private static final String TAG = "MainListTask";
	
	private ViewHolderMain viewHolder;
	private int position;
	private Context context;
	
	public MainListTask(int position, ViewHolderMain viewHolder, Context context) {
		this.position = position;
		this.viewHolder = viewHolder;
		this.context = context;
	}

	@Override
	protected Bitmap doInBackground(ViewHolderMain... params) {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), viewHolder.getImg_resource());
		Bitmap result = Bitmap.createScaledBitmap(bmp, 100, 100, true);
		return result;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(viewHolder.getPosition() == position){
			viewHolder.getImage().setImageBitmap(result);
		}
	}

}
