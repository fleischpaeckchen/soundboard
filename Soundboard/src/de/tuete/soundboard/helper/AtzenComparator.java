package de.tuete.soundboard.helper;

import java.util.Comparator;

import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;
import de.tuete.soundboard.model.SoundDatabase;

public class AtzenComparator implements Comparator<Sound>{
	
	private SoundDatabase database;
	
	public AtzenComparator(SoundDatabase db) {
		this.database = db;
	}

	@Override
	public int compare(Sound lhs, Sound rhs) {
		return database.getAtzeByID(lhs.getAtze()).getName().compareTo(database.getAtzeByID(rhs.getAtze()).getName());
	}

}
