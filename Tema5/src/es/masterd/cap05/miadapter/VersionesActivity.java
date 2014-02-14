package es.masterd.cap05.miadapter;

import java.util.ArrayList;
import android.app.ListActivity;
import android.os.Bundle;
import es.masterd.cap05.dialogo.R;

public class VersionesActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplelist);
		ArrayList<AndroidVersion> versiones = new ArrayList<AndroidVersion>();
		versiones.add(new AndroidVersion("Android", "", R.drawable.logo));
		versiones.add(new AndroidVersion("CupCake", "1.5", R.drawable.cupcake));
		versiones.add(new AndroidVersion("Donut", "1.6", R.drawable.donut));
		versiones.add(new AndroidVersion("Eclair", "2.0/2.1", R.drawable.eclair));
		VersionesAdapter adapter = new VersionesAdapter(VersionesActivity.this,versiones);
		setListAdapter(adapter);
	}
}