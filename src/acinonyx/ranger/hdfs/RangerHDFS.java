package acinonyx.ranger.hdfs;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.apache.commons.codec.binary.Base64;

public class RangerHDFS {

	public List<PolicyDetails> fetchPolicies(String link) {
		// TODO Auto-generated method stub
		String urlStr = link; // "http://192.168.131.129:6080/service/public/api/policy";
		String user = "admin";
		String pass = "admin";

		List<PolicyDetails> policyDetailsList = null;

		try {

			URL url = new URL(urlStr);
			String authStr = user + ":" + pass;

			String authEncoded = Base64.encodeBase64String(authStr.getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("Content-Type", "application/json");

			InputStream in = (InputStream) connection.getInputStream();
			InputStreamReader istr = new InputStreamReader(in);
			JsonReader jsonReader = Json.createReader(istr);
			JsonObject jsonObject = jsonReader.readObject();

			// JsonNumber totalCount = jsonObject.getJsonNumber("totalCount") ;
			// JsonNumber resultSize = jsonObject.getJsonNumber("resultSize") ;

			JsonArray vXPolicies = jsonObject.getJsonArray("vXPolicies");

			policyDetailsList = new ArrayList<PolicyDetails>(jsonObject.getJsonNumber("resultSize").intValue());

			for (int policies = 0; policies < vXPolicies.size(); policies++) {

				PolicyDetails policyDetails = new PolicyDetails();

				JsonObject vXPoliciesObj = vXPolicies.getJsonObject(policies);

				policyDetails.setPolicyId(vXPoliciesObj.getInt("id"));
				policyDetails.setPolicyName(vXPoliciesObj.getString("policyName"));
				policyDetails.setResourceName(vXPoliciesObj.getString("resourceName"));
				policyDetails.setRecursive(vXPoliciesObj.getBoolean("isRecursive"));

				JsonArray permMapList = vXPolicies.getJsonObject(policies).getJsonArray("permMapList");

				List<PermList> al1 = new ArrayList<PermList>(permMapList.size());

				for (int perms = 0; perms < permMapList.size(); perms++) {

					JsonObject vXPoliciesPerms = permMapList.getJsonObject(perms);
					JsonArray userList = vXPoliciesPerms.getJsonArray("userList");
					JsonArray groupList = vXPoliciesPerms.getJsonArray("groupList");
					JsonArray permList = vXPoliciesPerms.getJsonArray("permList");

					PermList permListObj = new PermList();
					permListObj.setuList(userList);
					permListObj.setgList(groupList);
					permListObj.setpList(permList);

					al1.add(permListObj);
				}
				policyDetails.setPermList(al1);
				policyDetailsList.add(policyDetails);
			}
			// System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return policyDetailsList;
	}

}
