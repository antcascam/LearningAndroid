package es.masterD.tema7;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class DbActivity extends ListActivity {
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jugadores);
		final String[] columnas = new String[] { JuegoProvider._ID, // 0
				JuegoProvider.NOMBRE, // 1
				JuegoProvider.PUNTOS, // 2
		};
		Uri uri = Uri.parse("content://es.masterD.tema7/jugadores/*");
		cursor = managedQuery(uri, columnas, null, null, null);
		String[] camposDb = new String[] { JuegoProvider.NOMBRE,JuegoProvider.PUNTOS };
		int[] camposView = new int[] { android.R.id.text1, android.R.id.text2 };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item, cursor, camposDb,camposView);
		setListAdapter(adapter);
	}
	@Override
	protected void onStart() {
		super.onStart();
		startManagingCursor(cursor);
	}

	@Override
	protected void onStop() {
		stopManagingCursor(cursor);
		super.onStop();
	}
}