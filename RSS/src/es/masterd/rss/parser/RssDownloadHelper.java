package es.masterd.rss.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import android.content.ContentResolver;
import android.util.Log;

public class RssDownloadHelper {
	private static RssHandler rssHandler;
	private static SAXParser saxParser;
	private static XMLReader xr;
	private static URL url;
	private static InputSource is;
	
	public static void updateRssData(String rssUrl,ContentResolver contentResolver) {
		try {
			url = new URL(rssUrl);
			// Obtenemos el SAXParser
			SAXParserFactory spf = SAXParserFactory.newInstance();
			saxParser = spf.newSAXParser();
			// Creamos el Handler
			rssHandler = new RssHandler(contentResolver);
			// Definimos el manejador léxico
			saxParser.setProperty("http://xml.org/sax/properties/lexical-handler",rssHandler);
			// Obtenemos el Reader
			xr = saxParser.getXMLReader();
			xr.setContentHandler(rssHandler);
			// Parseamos el contenido
			is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			xr.parse(is);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			Log.d("updateRssData", "Error al abrir el archivo");
			Log.d("updateRssData,Lo que dice e1",e.getMessage());
			Log.d("updateRssData,Lo que dice e2",e.toString());
		} catch (SAXException e){
			e.printStackTrace();
			e.getException();
			Log.d("updateRssData", "Error al parsear el documento");
			Log.d("updateRssData,Lo que dice e1",e.getMessage());
			Log.d("updateRssData,Lo que dice e2",e.toString());	
		} catch (IOException e){
			e.printStackTrace();
			Log.d("updateRssData", "Error al leer el archivo");
			Log.d("updateRssData,Lo que dice e1",e.getMessage());
			Log.d("updateRssData,Lo que dice e2",e.toString());
		} catch (ParserConfigurationException e){
			e.printStackTrace();
			Log.d("updateRssData", "Error al Configurar el Parser");
			Log.d("updateRssData,Lo que dice e1",e.getMessage());
			Log.d("updateRssData,Lo que dice e2",e.toString());
		} catch (Exception e) {
			Log.d("updateRssData,Lo que dice e1",e.getMessage());
			Log.d("updateRssData,Lo que dice e2",e.toString());
			Log.d("updateRssData", "Error al en otra cosilla");
		}
		// El parseo ha concluido
	}
}