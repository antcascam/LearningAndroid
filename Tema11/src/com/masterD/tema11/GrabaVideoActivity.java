package com.masterD.tema11;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GrabaVideoActivity extends Activity implements
		SurfaceHolder.Callback {
	private Camera cam;
	private SurfaceView camView;
	private SurfaceHolder camHolder;
	private MediaRecorder recorder;
	private boolean previewRunning;

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (previewRunning) {
			cam.stopPreview();
		}
		Camera.Parameters parameters = cam.getParameters();
		List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();

		Camera.Size cs = sizes.get(0);
		parameters.setPreviewSize(cs.width, cs.height);
		cam.setParameters(parameters);
		try {
			cam.setPreviewDisplay(holder);
			cam.startPreview();
			previewRunning = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			cam = Camera.open();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Camara no disponible! ",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		cam.stopPreview();
		previewRunning = false;
		cam.release();
		cam = null;
		if (recorder != null) {
			recorder.release();
			recorder = null;
		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_rec);
		camView = (SurfaceView) findViewById(R.id.zonaVideo);
		camHolder = camView.getHolder();
		camHolder.addCallback(this);
		camHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		ToggleButton btn = (ToggleButton) findViewById(R.id.rec);
		btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				try {
					if (isChecked) {
						grabar();
					} else {
						pararGrabar();
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void grabar() throws IllegalStateException {
		cam.unlock();
		recorder = new MediaRecorder();
		recorder.setCamera(cam);
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		String filename = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/miVideo.3gp";
		recorder.setOutputFile(filename);
		recorder.setMaxDuration(2000);
		recorder.setMaxFileSize(10240);
		recorder.setPreviewDisplay(camHolder.getSurface());
		try {
			recorder.prepare();
			recorder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void pararGrabar() {
		recorder.stop();
		recorder.reset();
		recorder.release();
	}
}