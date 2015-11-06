package de.tuete.soundboard.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.tuete.soundboard.R;
import de.tuete.soundboard.helper.ViewHolderMain;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;
import de.tuete.soundboard.tasks.MainListTask;

public class MainListAdapter extends ArrayAdapter<Sound>{

	private static final String TAG = "MainListAdapter";
	private Context context;
	private ArrayList<Sound> sounds;
	private Atze[] atzen;


	public MainListAdapter(Context context, int resource, ArrayList<Sound> sounds, Atze[] atzen) {
		super(context, resource, sounds);
		this.context = context;
		this.sounds = sounds;
		this.atzen = atzen;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolderMain viewHolder = new ViewHolderMain();
		
		Sound sound = sounds.get(position);
		Atze atze = null;
		for (Atze a : atzen) {
			if (a.get_id() == sound.getAtze())
				atze = a;
		}
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_mainlist, parent, false);
			
			viewHolder = new ViewHolderMain();
			viewHolder.setImage((ImageView)convertView.findViewById(R.id.img_sound));
			viewHolder.setTextView((TextView)convertView.findViewById(R.id.txt_desc));
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolderMain) convertView.getTag();
		}
		
		
		viewHolder.getTextView().setText(sound.getDesc());
		viewHolder.setImg_resource(atze.getImg_resource());
		
		viewHolder.setPosition(position);
		//using async shit to load the image
		new MainListTask(position, viewHolder, this.context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);

		return convertView;
	}
	

}
