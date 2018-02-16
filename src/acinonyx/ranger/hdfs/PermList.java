package acinonyx.ranger.hdfs;

import javax.json.JsonArray;

public class PermList {
	JsonArray uList;
	JsonArray gList;
	JsonArray pList;

	public String getuList() {

		int len_arr = uList.size();
		String userList = "";

		for (int var = 0; var < len_arr; var++) {
			userList += (uList.getString(var) + " ");
		}

		return userList.trim().replace(" ", ", ");
	}

	public void setuList(JsonArray uList) {
		this.uList = uList;
	}

	public String getgList() {
		int len_arr = gList.size();
		String groupList = "";

		for (int var = 0; var < len_arr; var++) {
			groupList += (gList.getString(var) + " ");
		}

		return groupList.trim().replace(" ", ", ");
	}

	public void setgList(JsonArray gList) {
		this.gList = gList;
	}

	public String getpList() {
		int len_arr = pList.size();
		String permList = "";

		for (int var = 0; var < len_arr; var++) {
			permList += (pList.getString(var) + " ");
		}

		return permList.trim().replace(" ", ", ");

	}

	public void setpList(JsonArray pList) {
		this.pList = pList;
	}

	@Override
	public String toString() {
		return "PermList [uList=" + uList + ", gList=" + gList + ", pList=" + pList + "]";
	}

}
