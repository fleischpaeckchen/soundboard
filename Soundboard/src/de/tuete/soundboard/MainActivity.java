package de.tuete.soundboard;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.tuete.soundboard.adapter.MainListAdapter;
import de.tuete.soundboard.model.Sound;

public class MainActivity extends Activity implements OnItemClickListener{

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
		
		MainListAdapter adapter = new MainListAdapter(this, R.layout.row_mainlist, descriptions);
		this.lst_main.setAdapter(adapter);
		this.lst_main.setOnItemClickListener(this);
		
		Toast.makeText(this, this.lst_main.getCount() + "", Toast.LENGTH_SHORT).show();
		
//		MediaPlayer player = MediaPlayer.create(this, this.sounds[0].getRaw());
//		player.start();
	}

	private void initList() {
		this.sounds = new Sound[this.descriptions.length];
		
		//set up sounds
		Sound s0 = new Sound();
		s0.setDesc(this.descriptions[0]);
		s0.setRaw(R.raw.jonas_lachen);
		
		Sound s1 = new Sound();
		s1.setDesc(this.descriptions[1]);
		s1.setRaw(R.raw.jonas_fickdichjunge);
		
		Sound s3 = new Sound();
		s3.setDesc(this.descriptions[2]);
		s3.setRaw(R.raw.jonas_kauderwelsch);
		
		//adding
		this.sounds[0] = s0;
		this.sounds[1] = s1;
		this.sounds[2] = s3;
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
	
}
