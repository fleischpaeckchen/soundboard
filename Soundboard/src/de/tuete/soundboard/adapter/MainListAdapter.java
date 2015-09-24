package de.tuete.soundboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.tuete.soundboard.R;
import de.tuete.soundboard.model.Sound;

public class MainListAdapter extends ArrayAdapter<Sound>{

	private Context context;
	private Sound[] sounds;
	
	public MainListAdapter(Context context, int resource, Sound[] sounds) {
		super(context, resource, sounds);
		this.context = context;
		this.sounds = sounds;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.row_mainlist, parent, false);
		
		ImageView img = (ImageView) view.findViewById(R.id.img_sound);
		img.setImageResource(sounds[position].getAtze().getImg_resource());
		
		TextView txt_desc = (TextView) view.findViewById(R.id.txt_desc);
		txt_desc.setText(this.sounds[position].getDesc());
		
		return view;
	}
	
}
