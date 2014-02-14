package es.masterd.rss.activities;

import android.app.Application;



public class MasterdApplication extends Application {

	public String getRssUrl() {
		return "http://www.masterd.es/blog/feed/rss/";
	}
	
}
