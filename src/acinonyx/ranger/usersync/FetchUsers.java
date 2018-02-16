package acinonyx.ranger.usersync;

import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
 * ArrayList<String> listdata = new ArrayList<String>(); 
		
		for (String user : reqList) 				{	listdata.add(user);					}		
		for (int i = 0; i < array.size(); i++) 		{	listdata.add(array.getString(i));	}
		
		Set<String> hs = new HashSet<>()	;
		hs.addAll(listdata)					;
		listdata.clear()					;
		listdata.addAll(hs)					;
 */

import org.w3c.dom.Node;

public class FetchUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlStr = "http://192.168.131.129:6080/service/xusers/users/";
		String user = "admin";
		String pass = "admin";
		ArrayList<String> listUsers = new ArrayList<String>();
		try {

			URL url = new URL(urlStr);
			String authStr = user + ":" + pass;

			String authEncoded = Base64.encodeBase64String(authStr.getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("Content-Type", "application/json");

			InputStream in = (InputStream) connection.getInputStream();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(in);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("vXUsers");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				listUsers.add(eElement.getElementsByTagName("name").item(0).getTextContent());
			}
			Set<String> hs = new HashSet<>();
			hs.addAll(listUsers);
			listUsers.clear();
			listUsers.addAll(hs);
			in.close();
		} catch (Exception e) {
		}
		System.out.println("-------------------------");
		getGroups();
	}

	public static void getGroups() {
		// TODO Auto-generated method stub
		String urlStr = "http://192.168.131.129:6080/service/xusers/users/";
		String user = "admin";
		String pass = "admin";
		ArrayList<String> listGroups = new ArrayList<String>();
		try {

			URL url = new URL(urlStr);
			String authStr = user + ":" + pass;

			String authEncoded = Base64.encodeBase64String(authStr.getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("Content-Type", "application/json");

			InputStream in = (InputStream) connection.getInputStream();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(in);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("vXUsers");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				try {
					listGroups.add(eElement.getElementsByTagName("groupNameList").item(0).getTextContent());
				} catch (java.lang.NullPointerException e) {
					continue;
				}
			}
			Set<String> hs = new HashSet<>();
			hs.addAll(listGroups);
			listGroups.clear();
			listGroups.addAll(hs);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
