package de.tuete.soundboard.helper;

import java.util.Comparator;

import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;

public class AtzenComparator implements Comparator<Sound>{

	@Override
	public int compare(Sound lhs, Sound rhs) {
		return lhs.getAtze().getName().compareTo(rhs.getAtze().getName());
	}

}
