package es.masterd.rss.db;

import android.provider.BaseColumns;

public class FeedsDB {

	public static final String DB_NAME = "feeds.db";

	public static final int DB_VERSION = 1;
	
	private FeedsDB () {}
	
	public static final class Posts implements BaseColumns{
		
		private Posts() {}
	
		public static final String DEFAULT_SORT_ORDER = "_ID DESC";
	
	public static final String NOMBRE_TABLA = "feeds";
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String LINK = "link";
	public static final String COMMENTS = "comments";
	public static final String PUB_DATE = "pub_date";
	public static final String CREATOR = "creator";
	public static final String DESCRIPTION = "description";
	public static final String _COUNT = "7";
	}
}

