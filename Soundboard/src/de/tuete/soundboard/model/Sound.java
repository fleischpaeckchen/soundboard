package de.tuete.soundboard.model;

public class Sound implements Comparable<Sound>{

	private String desc, path;
	private int raw;
	private long atze;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRaw() {
		return raw;
	}
	public void setRaw(int raw) {
		this.raw = raw;
	}
	public long getAtze() {
		return atze;
	}
	public void setAtze(long atze) {
		this.atze = atze;
	}
	@Override
	public int compareTo(Sound another) {
		return this.desc.compareTo(another.desc);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
