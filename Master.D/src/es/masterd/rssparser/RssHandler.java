package es.masterd.rssparser;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;
import es.masterd.rss.db.FeedsDB;

public class RssHandler extends DefaultHandler implements LexicalHandler {
	
	ContentValues rssItem;

	private boolean in_item = false;
	private boolean in_title = false;
	private boolean in_link = false;
	private boolean in_comments = false;
	private boolean in_pubDate = false;
	private boolean in_dcCreator = false;
	private boolean in_description = false;

	private boolean in_CDATA;

	private ContentResolver contentProv;
	Uri uri = Uri.parse("content://es.masterd.blog/post");

    public RssHandler(ContentResolver contentResolver) {
		this.contentProv = contentResolver;
	}

    @Override
    public void startElement(String namespaceURI, String localName,
                    String qName, Attributes atts) throws SAXException {

    	if(localName.equalsIgnoreCase("item")) {
    		in_item = true;
    		rssItem = new ContentValues();
    	} else if(localName.equalsIgnoreCase("title")) {
    		in_title = true;
    	} else if(localName.equalsIgnoreCase("link")) {
    		in_link = true;
    	} else if(localName.equalsIgnoreCase("comments")){
    		in_comments = true;
    	} else if(localName.equalsIgnoreCase("pubDate")) {
    		in_pubDate = true;
    	} else if(localName.equalsIgnoreCase("dc:creator")) {
    		in_dcCreator = true;
    	} else if(localName.equalsIgnoreCase("description")) {
    		in_description = true;
    	}
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
                    throws SAXException {
    	

    	if(localName.equalsIgnoreCase("item")) {

			Log.d("guardado item", ""+rssItem);
    		   		
			contentProv.insert(uri, rssItem);  
			
    		rssItem = new ContentValues();
    		
    		in_item = false;
 
    	} else if(localName.equalsIgnoreCase("title")) {
    		in_title = false;
    	} else if(localName.equalsIgnoreCase("link")) {
    		in_link = false;
    	} else if(localName.equalsIgnoreCase("comments")){
    		in_comments = false;
    	} else if(localName.equalsIgnoreCase("pubDate")) {
    		in_pubDate = false;
    	} else if(localName.equalsIgnoreCase("dc:creator")) {
    		in_dcCreator = false;
    	} else if(localName.equalsIgnoreCase("description")) {
    		in_description = false;
    	}
    }
    
	@Override
	public void characters(char ch[], int start, int length) {
		if (in_item) { 
			if (in_title) {
				rssItem.put(FeedsDB.Posts.TITLE, new String(ch, start, length));
			} else if (in_link) {
				rssItem.put(FeedsDB.Posts.LINK, new String(ch, start, length));
			} else if (in_description) {
				rssItem.put(FeedsDB.Posts.DESCRIPTION, new String(ch, start,
						length));
			} else if (in_pubDate) {
				String strDate = new String(ch, start, length);
				try {
					long fecha = Date.parse(strDate);
					rssItem.put(FeedsDB.Posts.PUB_DATE, fecha);
				} catch (Exception e) {
					Log.d("RssHandler", "Error al parsear la fecha");
				}
			}

		}
	}



	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endCDATA() throws SAXException {
		in_CDATA = false;
	}

	@Override
	public void endDTD() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startCDATA() throws SAXException {
		in_CDATA = true;
	}

	@Override
	public void startDTD(String name, String publicId, String systemId)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
}
