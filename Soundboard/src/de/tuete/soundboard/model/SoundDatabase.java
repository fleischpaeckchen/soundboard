package de.tuete.soundboard.model;

import android.content.Context;
import android.util.Log;
import de.tuete.soundboard.R;
import de.tuete.soundboard.db.SoundDbHelper;

public class SoundDatabase {
	
	private static final String TAG = "SoundDatabase";
	private static SoundDatabase instance;
	private static SoundDbHelper helper;
	
	private Sound[] sounds;
	private Atze[] atzen;

	private SoundDatabase(Context context) {
		helper = new SoundDbHelper(context);
		createAtzenArray();
		this.sounds = new Sound[helper.getSoundCount()];
		Log.d(TAG, "SoundDatabase object created.");
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
		Log.d(TAG, "Entering Method: updateData");
		this.sounds = helper.getAllSounds();
		Log.d(TAG, "Sound Array length: " + this.sounds.length);
		for (int i = 0; i < sounds.length; i++) {
			Log.d(TAG, "Sound Description: " + sounds[i].getDesc());
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
		Atze nobi = new Atze(1, "Nobi", R.drawable.nobke);
		Atze schlotte = new Atze(2, "Schlotte", R.drawable.schlotte);
		
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