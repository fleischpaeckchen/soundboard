package de.tuete.soundboard.model;

public class Atze {

	private String name;
	private int img_resource;
	
	public Atze(String name, int img){
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
	
	
}
