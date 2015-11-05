package de.tuete.soundboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.tuete.soundboard.R;
import de.tuete.soundboard.model.Atze;

public class AtzenSpinnerAdapter extends ArrayAdapter<Atze> {
	
	private Context context;
	private Atze[] atzen;

	public AtzenSpinnerAdapter(Context context, int resource, Atze[] objects) {
		super(context, resource, objects);
		this.context = context;
		this.atzen = objects;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}
	
	public View getCustomView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spinner_row_atzen, parent, false);
		
//		ImageView img_atze = (ImageView) row.findViewById(R.id.img_atze);
//		img_atze.setImageResource(atzen[position].getImg_resource());
		
		TextView txt_atze_name = (TextView) row.findViewById(R.id.txt_atze_name);
		txt_atze_name.setText(atzen[position].getName());
		
		return row;
	}
}
