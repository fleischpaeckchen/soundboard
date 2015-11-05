package de.tuete.soundboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import de.tuete.soundboard.adapter.MainListAdapter;
import de.tuete.soundboard.helper.AtzenComparator;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;
import de.tuete.soundboard.model.SoundDatabase;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener{

	private static final String TAG = "MainActivity";
	private ListView lst_main;
	private Sound[] sounds;
	private String[] descriptions;
	private MainListAdapter adapter;
	private SoundDatabase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.lst_main = (ListView) findViewById(R.id.lst_main);
		this.database = SoundDatabase.getInstance(this);
		this.sounds = this.database.getSounds();
		
		Resources res = getResources();
		this.descriptions = res.getStringArray(R.array.descriptions);
		
//		initList();
		ArrayList<Sound> lstsounds = new ArrayList<Sound>(Arrays.asList(this.sounds));
		this.adapter = new MainListAdapter(this, R.layout.row_mainlist, lstsounds, database.getAtzen());
		this.lst_main.setAdapter(adapter);
		this.lst_main.setOnItemClickListener(this);
		this.lst_main.setOnItemLongClickListener(this);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_info) {
			
			showInfoDialog();
			
		}else if(id == R.id.action_sort_by_alphabet){
			
			Arrays.sort(this.sounds);
			this.adapter.notifyDataSetChanged();
			
		}else if(id == R.id.action_sort_by_atze){
			
			Arrays.sort(this.sounds, new AtzenComparator(this.database));
			this.adapter.notifyDataSetChanged();
			
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MediaPlayer player = MediaPlayer.create(this, Uri.fromFile(new File(this.sounds[position].getPath())));
		//player.setVolume(1f, 1f); seems to have no effect
		player.start();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Was willst du tun?");
		builder.setNegativeButton("Löschen", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteSound(position);
			}
		});
		builder.setPositiveButton("Senden", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sendSound(position);
			}
		});
		builder.create().show();
		return true;
	}
	
	private void sendSound(int position){
//		Intent share = new Intent(Intent.ACTION_SEND);
//		share.setType("audio/*");
////		share.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://de.tuete.soundboard/" + sounds[position].getRaw()));	
//		Log.d("MainActivity", "Sound Path: " + sounds[position].getPath());
//		share.putExtra(Intent.EXTRA_STREAM, Uri.parse(sounds[position].getPath()));
//		startActivity(Intent.createChooser(share, "Share Sound File"));
		
		String path = sounds[position].getPath().replace("/data/data/de.tuete.soundboard/files/", "");
		final File file = new File(getFilesDir(), path);
		Log.d(TAG, "File path: " + file.getPath());
		
		final Uri uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);
		Log.d(TAG, uri.toString());
		
		final Intent intent = ShareCompat.IntentBuilder.from(this)
		    .setType("audio/*")
		    .setStream(uri)
		    .createChooserIntent()
//		    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
		    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		 
		startActivity(intent);
	}
	
	private void deleteSound(int position){
		String filePath = sounds[position].getPath();
		database.deleteSound(sounds[position]);
		File file = new File(filePath);
		
		if(file.delete()){
			Toast.makeText(this, filePath + " gel�scht.", Toast.LENGTH_SHORT).show();
			refreshList();
		}else{
			Toast.makeText(this, "Irgendeine Schei�e ist passiert!", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void refreshList(){
		this.sounds = database.getSounds();
		ArrayList<Sound> lstSounds = new ArrayList<Sound>(Arrays.asList(this.sounds));
		this.adapter.clear();
		this.adapter.addAll(lstSounds);
		this.adapter.notifyDataSetChanged();
	}
	
	private void showInfoDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Soundboard vom Stein");
		builder.setMessage("Jemacht von Jojo,\n"
				+ "Ick sach danke zu:,\n"
				+ "Jonas, Nobi, Mark und Schlitte!\n"
				+ "Prost!");
		builder.setNeutralButton("Haick jerafft jonge!", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
	
}
