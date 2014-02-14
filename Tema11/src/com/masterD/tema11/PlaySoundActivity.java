package com.masterD.tema11;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PlaySoundActivity extends Activity {
	private String URL_SONIDO = "http://www.soundjay.com/human/sounds/applause-3.mp3";
	private MediaPlayer mediaPlayer;
	private int posicion;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaPlay();
			}
		});
		Button pause = (Button) findViewById(R.id.pause);
		pause.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaPausa();
			}
		});
		Button stop = (Button) findViewById(R.id.stop);
		stop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mediaStop();
			}
		});
	}

	public void mediaPlay() {
		mediaStop();
		mediaPlayer = new MediaPlayer();
		if (mediaPlayer == null) {
			Toast.makeText(PlaySoundActivity.this,
					"Error al cargar el MediaPlayer", Toast.LENGTH_LONG).show();
			finish();
		}
		try {
			mediaPlayer.setDataSource(URL_SONIDO);
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