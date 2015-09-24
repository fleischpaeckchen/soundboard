package de.tuete.soundboard.model;

public class Sound implements Comparable<Sound>{

	private String desc;
	private int raw;
	private Atze atze;
	
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
	public Atze getAtze() {
		return atze;
	}
	public void setAtze(Atze atze) {
		this.atze = atze;
	}
	@Override
	public int compareTo(Sound another) {
		return this.desc.compareTo(another.desc);
	}
}
