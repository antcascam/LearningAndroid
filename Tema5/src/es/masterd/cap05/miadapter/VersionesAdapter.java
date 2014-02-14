package es.masterd.cap05.miadapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import es.masterd.cap05.dialogo.R;


public class VersionesAdapter extends BaseAdapter {
	private ArrayList<AndroidVersion> versiones;
	private LayoutInflater mInflater;

	public VersionesAdapter(Context context, ArrayList<AndroidVersion> vers) {
		this.mInflater = LayoutInflater.from(context);
		this.versiones = vers;
	}

	public int getCount() {
		return versiones.size();
	}

	public AndroidVersion getItem(int position) {
		return versiones.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listaversion_item, null);
			holder = new ViewHolder();
			holder.hNombre = (TextView) convertView.findViewById(R.id.idNombre);
			holder.hVersion = (TextView) convertView.findViewById(R.id.idVersion);
			holder.hImage = (ImageView) convertView.findViewById(R.id.idLogo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AndroidVersion version = getItem(position);
		holder.hNombre.setText(version.getNombre());
		holder.hVersion.setText(version.getVersion());
		holder.hImage.setImageResource(version.getLogo());
		return convertView;
	}

	class ViewHolder {
		TextView hNombre;
		TextView hVersion;
		ImageView hImage;
	}
}