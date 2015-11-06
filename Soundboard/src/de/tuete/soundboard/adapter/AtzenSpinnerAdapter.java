package de.tuete.soundboard.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.tuete.soundboard.R;
import de.tuete.soundboard.helper.ViewHolderMain;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.tasks.MainListTask;

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
		ViewHolderMain viewHolder = new ViewHolderMain();
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.spinner_row_atzen, parent, false);
			
			viewHolder = new ViewHolderMain();
			viewHolder.setImage((ImageView)convertView.findViewById(R.id.img_atze));
			viewHolder.setTextView((TextView)convertView.findViewById(R.id.txt_atze_name));
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolderMain) convertView.getTag();
		}

		viewHolder.getTextView().setText(atzen[position].getName());
		viewHolder.setImg_resource(atzen[position].getImg_resource());
		viewHolder.setPosition(position);
		new MainListTask(position, viewHolder, this.context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
		
		TextView txt_atze_name = (TextView) convertView.findViewById(R.id.txt_atze_name);
		txt_atze_name.setText(atzen[position].getName());
		
		return convertView;
	}
}
