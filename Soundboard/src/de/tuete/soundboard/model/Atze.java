package de.tuete.soundboard.model;

public class Atze {

	private long _id;
	private String name;
	private int img_resource;
	
	public Atze(long id, String name, int img){
		this._id = id;
		this.name = name;
		this.img_resource = img;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImg_resource() {
		return img_resource;
	}
	public void setImg_resource(int img_resource) {
		this.img_resource = img_resource;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	
}
