package de.tuete.soundboard.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.tuete.soundboard.R;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;

public class SoundDbHelper extends SQLiteOpenHelper{

	private Context context;

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "soundboard";
    
//    private static final String ATZEN_TABLE_NAME = "atze";
//    private static final String ATZEN_ID = "_id";
//    private static final String ATZEN_IMAGE = "image";
//    private static final String ATZEN_NAME = "name";
//    private static final String ATZEN_TABLE_CREATE = "CREATE TABLE " + ATZEN_TABLE_NAME + "("
//    				+ ATZEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//    				+ ATZEN_IMAGE + " INTEGER NOT NULL,"
//    				+ ATZEN_NAME + " TEXT NOT NULL);";
    
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
//		db.execSQL(ATZEN_TABLE_CREATE);
		db.execSQL(SOUND_TABLE_CREATE);
		
//		addAtze("Jonas", R.drawable.jonas, db);
//		addAtze("Nobi", R.drawable.nobke, db);
//		addAtze("Schlotte", R.drawable.schlotte, db);
	}
	
//	private long addAtze(String name, int raw, SQLiteDatabase db){
//		String sql = "INSERT INTO " + ATZEN_TABLE_NAME + "(" + ATZEN_NAME + "," + ATZEN_IMAGE + ")"
//				+ " VALUES ('" + name + "', " + raw + ");";
//		
//		ContentValues values = new ContentValues();
//		values.put(ATZEN_NAME, name);
//		values.put(ATZEN_IMAGE, raw);
//		return db.insert(ATZEN_TABLE_NAME, null, values);
//	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
//	public Atze[] getAllAtzen(){
//		SQLiteDatabase db = getReadableDatabase();
//		Cursor cursor = db.query(ATZEN_TABLE_NAME, new String[]{ATZEN_IMAGE, ATZEN_NAME}, null, null, null, null, null);
//		Atze[] atzen = new Atze[cursor.getCount()];
//		int count = 0;
//		while(cursor.moveToNext()){
//			Atze atze = new Atze(cursor.getString(1), cursor.getInt(0));
//			atzen[count] = atze;
//			count++;
//		}
//		return atzen;
//	}
	
	public void saveSound(long atze, String name, String path){
		SQLiteDatabase db = getWritableDatabase();
		long atzen_id = atze;
		
//		String sql_getID = "SELECT " + ATZEN_ID + " FROM " + ATZEN_TABLE_NAME + " WHERE " + ATZEN_NAME + " = '" + atze.getName() + "';";
		
//		Cursor cursor = db.rawQuery(sql_getID, null);
		
//		while (cursor.moveToNext()) {
//			atzen_id = cursor.getInt(0);
//		}
		
		String sql = "INSERT INTO " + SOUND_TABLE_NAME + "(" + SOUND_NAME + "," + SOUND_ATZE + "," + SOUND_PATH + ")"
				+ "VALUES('" + name + "', " + atzen_id + ", '" + path + "');";
		
		db.execSQL(sql);
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
		}
		
		return sounds;
	}

}
