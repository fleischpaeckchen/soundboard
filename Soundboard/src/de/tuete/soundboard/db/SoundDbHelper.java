package de.tuete.soundboard.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.tuete.soundboard.R;
import de.tuete.soundboard.model.Atze;

public class SoundDbHelper extends SQLiteOpenHelper{

	private Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "soundboard";
    
    private static final String ATZEN_TABLE_NAME = "atze";
    private static final String ATZEN_ID = "_id";
    private static final String ATZEN_IMAGE = "image";
    private static final String ATZEN_NAME = "name";
    private static final String ATZEN_TABLE_CREATE = "CREATE TABLE " + ATZEN_TABLE_NAME + "("
    				+ ATZEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
    				+ ATZEN_IMAGE + " INTEGER NOT NULL,"
    				+ ATZEN_NAME + " TEXT NOT NULL);";
    
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
		db.execSQL(ATZEN_TABLE_CREATE);
		db.execSQL(SOUND_TABLE_CREATE);
		
		addAtze("Jonas", R.drawable.jonas, db);
		addAtze("Nobi", R.drawable.nobke, db);
		addAtze("Schlotte", R.drawable.schlotte, db);
	}
	
	private void addAtze(String name, int raw, SQLiteDatabase db){
		String sql = "INSERT INTO " + ATZEN_TABLE_NAME + "(" + ATZEN_NAME + "," + ATZEN_IMAGE + ")"
				+ " VALUES ('" + name + "', " + raw + ");";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public Atze[] getAllAtzen(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(ATZEN_TABLE_NAME, new String[]{ATZEN_IMAGE, ATZEN_NAME}, null, null, null, null, null);
		Atze[] atzen = new Atze[cursor.getCount()];
		int count = 0;
		while(cursor.moveToNext()){
			Atze atze = new Atze(cursor.getString(1), cursor.getInt(0));
			atzen[count] = atze;
			count++;
		}
		return atzen;
	}

}
