package de.tuete.soundboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import de.tuete.soundboard.adapter.AtzenSpinnerAdapter;
import de.tuete.soundboard.model.Atze;

public class NewSoundActivity extends Activity {
	
	private Atze[] atzen;
	private EditText txt_soundname;
	private Spinner sp_atzen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_sound);
		
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		
		createAtzenArray();
		this.txt_soundname = (EditText) findViewById(R.id.txt_sound_name);
		this.sp_atzen = (Spinner) findViewById(R.id.sp_atzen);
		
		AtzenSpinnerAdapter adapter = new AtzenSpinnerAdapter(this, R.layout.spinner_row_atzen, atzen);
		this.sp_atzen.setAdapter(adapter);
		
		if(Intent.ACTION_SEND.equals(action) && type != null){
			if("audio/*".equals(type)){
				handleAudio(intent);
			}
		}
	}
	
	private File copyFileToInternal(File aacFile) throws IOException{
		File internalFile = new File(getFilesDir(), "test.aac");
		
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(aacFile).getChannel();
			outChannel = new FileOutputStream(internalFile).getChannel();
			
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {
			if(inChannel != null)
				inChannel.close();
			if(outChannel != null)
				outChannel.close();
		}
		
		return internalFile;
	}
	
	public void saveSound(View view){
		
	}
	
	private void handleAudio(Intent intent){
		Uri uri = (Uri)intent.getParcelableExtra(Intent.EXTRA_STREAM);
		File src = new File(uri.getPath());

		File dst = null;
		try {
			dst = copyFileToInternal(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		MediaPlayer player = MediaPlayer.create(this, Uri.fromFile(dst));
		player.start();
	}
	
	private void createAtzenArray(){
		Atze jonas = new Atze("Jonas", R.drawable.jonas);
		Atze nobi = new Atze("Nobi", R.drawable.nobke);
		Atze schlotte = new Atze("Schlotte", R.drawable.schlotte);
		
		this.atzen = new Atze[3];
		atzen[0] = jonas;
		atzen[1] = nobi;
		atzen[2] = schlotte;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_sound, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
