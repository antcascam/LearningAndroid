package com.masterD.practicas17y18;

import java.io.IOException;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class GrabayReproduceActivity extends Activity {
	private MediaRecorder recorder;
	private MediaPlayer mediaPlayer;
	private int posicion;
	private static final String archivoAudio = Environment.getExternalStorageDirectory().getPath()+"/audio.3gp";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ToggleButton btn = (ToggleButton) findViewById(R.id.rec);
		Button play = (Button) findViewById(R.id.play);
		Button pause = (Button) findViewById(R.id.pause);
		Button stop = (Button) findViewById(R.id.stop);
		
		//Al pulsar el botón de grabar:
		btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				try {
					if (isChecked) {
						grabar();
					} else {
						pararGrabar();
						mediaPlay();
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		});
		
		//Al pulsar el botón Play:
		play.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaPlay();
			}
		});
		
		//Al pulsar el botón Pause:
		pause.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaPausa();
			}
		});
		
		//Al pulsar el botón Stop:
		stop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaStop();
			}
		});
	}

	// Definimos todos los métodos necesarios:
	
	protected void grabar() {
		recorder = new MediaRecorder();
		recorder.setOutputFile(archivoAudio);
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			recorder.prepare();
			recorder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void pararGrabar() {
		recorder.stop();
		recorder.release();
		recorder = null;
	}
	
	public void mediaPlay() {
		mediaStop();
		mediaPlayer = new MediaPlayer();
		if (mediaPlayer == null) {
			Toast.makeText(GrabayReproduceActivity.this,"Error al cargar el MediaPlayer", Toast.LENGTH_LONG).show();
			finish();
		}
		try {
			mediaPlayer.setDataSource(archivoAudio);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mediaStop() {
		if (mediaPlayer != null) {
			try {
				mediaPlayer.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void mediaPausa() {
		if (mediaPlayer == null) {
			return;
		}
		if (mediaPlayer.isPlaying()) {
			posicion = mediaPlayer.getCurrentPosition();
			mediaPlayer.pause();
		} else {
			mediaPlayer.seekTo(posicion);
			mediaPlayer.start();
		}
	}
	
}

