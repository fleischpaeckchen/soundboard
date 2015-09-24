package de.tuete.soundboard;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.tuete.soundboard.adapter.MainListAdapter;
import de.tuete.soundboard.model.Atze;
import de.tuete.soundboard.model.Sound;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener{

	private ListView lst_main;
	private Sound[] sounds;
	private String[] descriptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.lst_main = (ListView) findViewById(R.id.lst_main);
		Resources res = getResources();
		this.descriptions = res.getStringArray(R.array.descriptions);
		
		initList();
		
		MainListAdapter adapter = new MainListAdapter(this, R.layout.row_mainlist, this.sounds);
		this.lst_main.setAdapter(adapter);
		this.lst_main.setOnItemClickListener(this);
		this.lst_main.setOnItemLongClickListener(this);
		

	}

	private void initList() {
		Atze[] atzen = new Atze[3];
		Atze jonas = new Atze("Jonas", R.drawable.jonas);
		Atze nobi = new Atze("Nobi", R.drawable.nobke);
		Atze schlotte = new Atze("Schlotte", R.drawable.schlotte);
		
		atzen[0] = jonas;
		atzen[1] = nobi;
		atzen[2] = schlotte;
		
		this.sounds = new Sound[this.descriptions.length];
		
		//set up sounds
		Sound s0 = new Sound();
		s0.setDesc(this.descriptions[0]);
		s0.setRaw(R.raw.jonas_lachen);
		s0.setAtze(jonas);
			
		Sound s1 = new Sound();
		s1.setDesc(this.descriptions[1]);
		s1.setRaw(R.raw.jonas_fickdichjunge);
		s1.setAtze(jonas);
		
		Sound s2 = new Sound();
		s2.setDesc(this.descriptions[2]);
		s2.setRaw(R.raw.jonas_kauderwelsch);
		s2.setAtze(jonas);
		
		Sound s3 = new Sound();
		s3.setDesc(this.descriptions[3]);
		s3.setRaw(R.raw.nobi_nein);
		s3.setAtze(nobi);
		
		Sound s4 = new Sound();
		s4.setDesc(this.descriptions[4]);
		s4.setRaw(R.raw.schlotte_anrufbeantworter);
		s4.setAtze(schlotte);
		
		//adding
		this.sounds[0] = s0;
		this.sounds[1] = s1;
		this.sounds[2] = s2;
		this.sounds[3] = s3;
		this.sounds[4] = s4;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MediaPlayer player = MediaPlayer.create(this, this.sounds[position].getRaw());
		player.start();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("audio/*");
		share.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://de.tuete.soundboard/" + sounds[position].getRaw()));	
		startActivity(Intent.createChooser(share, "Share Sound File"));
		return true;
	}
	
}
