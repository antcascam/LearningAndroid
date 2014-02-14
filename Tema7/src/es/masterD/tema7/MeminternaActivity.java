package es.masterD.tema7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

//Importante: da FC la primera vez siempre, para que funcione, hay que crear misdatos.txt en data/data/tema7/files

public class MeminternaActivity extends Activity {
    /** Called when the activity is first created. */
	private String fichero = "misdatos.txt";
	private EditText editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);
        editor = (EditText)findViewById(R.id.editText);
        String texto = loadData();
        editor.setText(texto);
    }
    
    protected String loadData() {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		String texto = "";
		try {
			fis = openFileInput(fichero);
			InputStreamReader reader = new InputStreamReader(fis);
			BufferedReader buffreader = new BufferedReader(reader);
			String linea = "";
			while((linea = buffreader.readLine()) != null){texto += linea + "\n";}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return texto;
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		String texto = editor.getText().toString();
		saveData (texto);		
		super.onStop();
	}
	private void saveData(String texto) {
		// TODO Auto-generated method stub
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(fichero,Context.MODE_PRIVATE);
			fos.write(texto.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error al guardar el fichero", Toast.LENGTH_SHORT).show();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}