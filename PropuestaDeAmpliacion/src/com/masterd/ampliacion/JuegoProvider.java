package com.masterd.ampliacion;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class JuegoProvider extends ContentProvider{
	public static final String PROVIDER_NAME = "es.masterd.juego";
	public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "jugadores");
	public static final String _ID = "_id";
	public static final String NOMBRE = "nombre";
	public static final String PUNTOS = "puntos";
	private static final String DATABASE_TABLE = "jugadores";
	private static final int JUGADORES = 1;
	private static final int JUGADORES_ID = 2;
	private static final UriMatcher uriMatcher;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "jugadores", JUGADORES);
		uriMatcher.addURI(PROVIDER_NAME, "jugadores/#", JUGADORES_ID);

	}
	private SQLiteDatabase jugadoresDB;
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri)){
			case JUGADORES: return "vnd.android.cursor.dir/vnd.masterd.jugadores";
			case JUGADORES_ID: return "vnd.android.cursor.item/vnd.masterd.jugadores";
			default: throw new IllegalArgumentException("Unsopported URI" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		long RowID = jugadoresDB.insert(DATABASE_TABLE, "", values);
		if (RowID > 0){
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, RowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to insert row into" + uri);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
		JuegoSQLHelper dbHelper = new JuegoSQLHelper(context, null, null, 1);
		jugadoresDB = dbHelper.getWritableDatabase();
		return (jugadoresDB == null)? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(DATABASE_TABLE);
		if(uriMatcher.match(uri) == JUGADORES_ID){
			sqlBuilder.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
		}
		if (sortOrder == null || sortOrder ==""){sortOrder = NOMBRE;}
		Cursor c = sqlBuilder.query(
			jugadoresDB, projection, selection, selectionArgs, null, null, sortOrder
		);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
