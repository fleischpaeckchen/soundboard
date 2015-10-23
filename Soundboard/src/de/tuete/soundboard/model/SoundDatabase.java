package de.tuete.soundboard.model;

import android.content.Context;
import de.tuete.soundboard.R;
import de.tuete.soundboard.db.SoundDbHelper;

public class SoundDatabase {
	
	private static SoundDatabase instance;
	private static SoundDbHelper helper;
	
	private Sound[] sounds;
	private Atze[] atzen;

	private SoundDatabase(Context context) {
		helper = new SoundDbHelper(context);
		createAtzenArray();
	}
	
	public static SoundDatabase getInstance(Context context){
		if(instance != null){
			return instance;
		}else{
			instance = new SoundDatabase(context);
			return instance;
		}
	}
	
	public void updateData(){
		this.sounds = helper.getAllSounds();
		for (int i = 0; i < sounds.length; i++) {
			for (int j = 0; j < atzen.length; j++) {
				if(sounds[i].getAtze() == atzen[j].get_id())
					sounds[j].setAtze(atzen[j].get_id());
			}
		}
	}
	
	public Atze[] getAtzen(){
		return this.atzen;
	}
	
	private void createAtzenArray(){
		Atze jonas = new Atze(0, "Jonas", R.drawable.jonas);
		Atze nobi = new Atze(0, "Nobi", R.drawable.nobke);
		Atze schlotte = new Atze(0, "Schlotte", R.drawable.schlotte);
		
		this.atzen = new Atze[3];
		this.atzen[0] = jonas;
		this.atzen[1] = nobi;
		this.atzen[2] = schlotte;
	}

	public Sound[] getSounds() {
		updateData();
		return this.sounds;
	}
	
	public Atze getAtzeByID(long id){
		for (Atze atze : this.atzen) {
			if(atze.get_id() == id)
				return atze;
		}
		return null;
	}
}
