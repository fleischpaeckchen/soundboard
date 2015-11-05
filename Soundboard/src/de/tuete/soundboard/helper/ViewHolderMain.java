package de.tuete.soundboard.helper;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderMain {
	private ImageView image;
	private TextView textView;
	private int position;
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public TextView getTextView() {
		return textView;
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
