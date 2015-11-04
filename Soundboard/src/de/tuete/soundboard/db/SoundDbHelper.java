package de.tuete.soundboard.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.tuete.soundboard.R;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;

public class SoundDbHelper extends SQLiteOpenHelper{

	private Context context;

	private static final String TAG = "SoundDbHelper";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "soundboard";
    
    private static final String SOUND_TABLE_NAME = "sound";
    private static final String SOUND_ID = "_id";
    private static final String SOUND_NAME = "name";
    private static final String SOUND_ATZE = "atze";
    private static final String SOUND_PATH = "path";
    private static final String SOUND_TABLE_CREATE = "CREATE TABLE " + SOUND_TABLE_NAME + "("
			+ SOUND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ SOUND_NAME + " TEXT NOT NULL,"
			+ SOUND_ATZE + " INTEGER NOT NULL,"
			+ SOUND_PATH + " TEXT NOT NULL);";

	public SoundDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SOUND_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "Entering Method: upgradeDatabase");
		db.rawQuery("DROP TABLE " + SOUND_TABLE_NAME + ";", null);
		
	}
	
	public void saveSound(long atze, String name, String path){
		SQLiteDatabase db = getWritableDatabase();
		long atzen_id = atze;
		
		String sql = "INSERT INTO " + SOUND_TABLE_NAME + "(" + SOUND_NAME + "," + SOUND_ATZE + "," + SOUND_PATH + ")"
				+ "VALUES('" + name + "', " + atzen_id + ", '" + path + "');";
		
		db.execSQL(sql);
	}
	
	public int getSoundCount(){
		String sql = "SELECT * FROM " + SOUND_TABLE_NAME + ";";
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery(sql, null);
		return cursor.getCount();
	}

	public Sound[] getAllSounds() {
		SQLiteDatabase db = getReadableDatabase();
		String sound_query = "SELECT * FROM " + SOUND_TABLE_NAME + ";";
		
		Cursor cursor = db.rawQuery(sound_query, null);
		Sound[] sounds = new Sound[cursor.getCount()];
		int count = 0;
			
		while(cursor.moveToNext()){
			Sound sound = new Sound();
			sound.setDesc(cursor.getString(cursor.getColumnIndex(SOUND_NAME)));
			sound.setPath(cursor.getString(cursor.getColumnIndex(SOUND_PATH)));
			sound.setAtze(cursor.getInt(cursor.getColumnIndex(SOUND_ATZE)));
			sounds[count] = sound;
			count++;
		}
		
		return sounds;
	}

}
