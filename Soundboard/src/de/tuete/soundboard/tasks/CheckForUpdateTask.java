package de.tuete.soundboard.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class CheckForUpdateTask extends AsyncTask<String, Long, Boolean> {

	private int version;
	private int server_version;
	private Context context;
	
	public CheckForUpdateTask(int version, Context con) {
		this.version = version;
		this.context = con;
	}
	@Override
	protected void onProgressUpdate(Long... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		String path = "/sdcard/soundboard_version.txt";
		try {
			URL url = new URL(params[0]);
			URLConnection connection = url.openConnection();
			
			//download version file
			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream(path);
			
			byte data[] = new byte[1024];
			long total = 0;
			int count;
			while ((count = input.read(data)) != -1) {
				total += count;
	            output.write(data, 0, count);
			}
			
			output.flush();
			output.close();
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		File file = new File("/sdcard/soundboard_version.txt");
		StringBuilder text = new StringBuilder();
		String msg = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = reader.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(msg.isEmpty()) Toast.makeText(context, "Bla bla bla", Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Version: " + msg, Toast.LENGTH_SHORT).show();
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
