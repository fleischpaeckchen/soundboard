package de.tuete.soundboard.helper;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderMain {
	private ImageView image;
	private TextView textView;
	private int position;
	private int img_resource;
	
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
	public int getImg_resource() {
		return img_resource;
	}
	public void setImg_resource(int img_resource) {
		this.img_resource = img_resource;
	}
	
}
