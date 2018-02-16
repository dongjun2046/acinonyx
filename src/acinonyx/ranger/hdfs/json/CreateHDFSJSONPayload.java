package acinonyx.ranger.hdfs.json;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class CreateHDFSJSONPayload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// {"id":3,"createDate":"2017-01-10T10:07:52Z","updateDate":"2017-01-10T14:14:18Z","owner":"Admin","updatedBy":"Admin","policyName":"Policy_01","resourceName":"/org/abc","description":"","repositoryName":"hemant_hadoop","repositoryType":"hdfs","permMapList":[{"userList":["hemant"],"groupList":[],"permList":["Read"]},{"userList":["yarn"],"groupList":[],"permList":["Execute"]},{"userList":["zookeeper","hemant"],"groupList":[],"permList":["Read","Write"]},{"userList":["rangerusersync"],"groupList":[],"permList":["Read","Write","Execute"]},{"userList":["keyadmin"],"groupList":[],"permList":["Read","Execute"]},{"userList":["hdfs"],"groupList":[],"permList":["Write","Execute"]},{"userList":["hadoop"],"groupList":[],"permList":["Write"]}],"isEnabled":true,"isRecursive":true,"isAuditEnabled":true,"version":"9","replacePerm":false}

		String users = "hemant,hdfs,kumar";
		// String groups = "hemant,hdfs,kumar";
		List<String> usersList = new ArrayList<String>(Arrays.asList(users.split("\\s*,\\s*")));
		// List<String> groupList = new
		// ArrayList<String>(Arrays.asList(groups.split("\\s*,\\s*")));

		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String user : usersList) {
			builder.add(user);
		}

		JsonObject permMapObject = Json.createObjectBuilder().add("userList", builder)
				.add("groupList", Json.createArrayBuilder().add("hemant").add("hadoop"))
				.add("permList", Json.createArrayBuilder().add("read").add("write")).build();

		JsonObject policyObject = Json.createObjectBuilder().add("policyName", "John").add("resourceName", "/org/dsfs")
				.add("description", "").add("repositoryName", "hemant_hadoop").add("repositoryType", "hdfs")
				.add("isEnabled", "true").add("isRecursive", "true").add("isAuditEnabled", "true")
				.add("permMapList", Json.createArrayBuilder().add(permMapObject)).build();

		System.out.println(policyObject);
		updatePolicy();
	}

	public static boolean updatePolicy() {

		String jsonString = "{\"id\":3,\"createDate\":\"2017-01-10T10:07:52Z\",\"updateDate\":\"2017-01-10T14:14:18Z\",\"owner\":\"Admin\",\"updatedBy\":\"Admin\",\"policyName\":\"Policy_01\",\"resourceName\":\"/org/abc\",\"description\":\"\",\"repositoryName\":\"hemant_hadoop\",\"repositoryType\":\"hdfs\",\"permMapList\":[{\"userList\":[\"hemant\"],\"groupList\":[],\"permList\":[\"Read\"]},{\"userList\":[\"zookeeper\",\"hemant\"],\"groupList\":[],\"permList\":[\"Read\",\"Write\"]},{\"userList\":[\"yarn\"],\"groupList\":[],\"permList\":[\"Execute\"]},{\"userList\":[\"rangerusersync\"],\"groupList\":[],\"permList\":[\"Read\",\"Write\",\"Execute\"]},{\"userList\":[\"keyadmin\"],\"groupList\":[],\"permList\":[\"Read\",\"Execute\"]},{\"userList\":[\"hdfs\"],\"groupList\":[],\"permList\":[\"Write\",\"Execute\"]},{\"userList\":[\"hadoop\"],\"groupList\":[],\"permList\":[\"Write\"]}],\"isEnabled\":true,\"isRecursive\":true,\"isAuditEnabled\":true,\"version\":\"9\",\"replacePerm\":false}";

		String users = "hemant,hdfs,kumar";
		String groups = "hemant,hdfs,kumar";
		String reqperms = "read,write";

		List<String> requsersList = new ArrayList<String>(Arrays.asList(users.split("\\s*,\\s*")));
		List<String> reqgroupsList = new ArrayList<String>(Arrays.asList(groups.split("\\s*,\\s*")));
		List<String> reqPermsList = new ArrayList<String>(Arrays.asList(reqperms.split("\\s*,\\s*")));

		Collections.sort(reqPermsList);

		boolean policyWithSamePermsExists = false;

		InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
		InputStreamReader istr = new InputStreamReader(stream);

		JsonReader jsonReader = Json.createReader(istr);
		JsonObject jsonObject = jsonReader.readObject();

		JsonArray permMapList = jsonObject.getJsonArray("permMapList");

		JsonArrayBuilder permMapListToCreate = Json.createArrayBuilder();

		for (int perm = 0; perm < permMapList.size(); perm++) {

			JsonObject vXPoliciesPerms = permMapList.getJsonObject(perm);

			JsonArray userList = vXPoliciesPerms.getJsonArray("userList");
			JsonArray groupList = vXPoliciesPerms.getJsonArray("groupList");
			JsonArray permList = vXPoliciesPerms.getJsonArray("permList");
			List<String> polParmsList = new ArrayList<String>();

			// String[] polParms = new String[reqPerms.length];

			if (reqPermsList.size() == permList.size()) {
				for (int var = 0; var < permList.size(); var++) {
					polParmsList.add(permList.getString(var).toLowerCase());
				}
				Collections.sort(polParmsList);
			}

			JsonArrayBuilder userBuilder = Json.createArrayBuilder();
			JsonArrayBuilder groupBuilder = Json.createArrayBuilder();
			JsonArrayBuilder permBuilder = Json.createArrayBuilder();

			if (reqPermsList.equals(polParmsList)) {
				policyWithSamePermsExists = true;
				for (String user : getJsonArrayBuld(userList, requsersList)) {
					userBuilder.add(user);
				}
				for (String group : getJsonArrayBuld(groupList, reqgroupsList)) {
					groupBuilder.add(group);
				}
				for (String group : getJsonArrayBuld(permList, new ArrayList<String>())) {
					permBuilder.add(group);
				}
			} else {
				for (String user : getJsonArrayBuld(userList, new ArrayList<String>())) {
					userBuilder.add(user);
				}
				for (String group : getJsonArrayBuld(groupList, new ArrayList<String>())) {
					groupBuilder.add(group);
				}
				for (String group : getJsonArrayBuld(permList, new ArrayList<String>())) {
					permBuilder.add(group);
				}
			}

			JsonObject permMapObject = Json.createObjectBuilder().add("userList", userBuilder.build())
					.add("groupList", groupBuilder.build()).add("permList", permList).build();

			permMapListToCreate.add(permMapObject);
		}

		if (!policyWithSamePermsExists) {
			JsonArrayBuilder newuserBuilder = Json.createArrayBuilder();
			JsonArrayBuilder newgroupBuilder = Json.createArrayBuilder();
			JsonArrayBuilder newpermBuilder = Json.createArrayBuilder();

			JsonArray array = Json.createArrayBuilder().build();

			for (String user : getJsonArrayBuld(array, requsersList)) {
				newuserBuilder.add(user);
			}
			for (String group : getJsonArrayBuld(array, reqgroupsList)) {
				newgroupBuilder.add(group);
			}
			for (String perm : getJsonArrayBuld(array, reqPermsList)) {
				newpermBuilder.add(perm);
			}

			JsonObject newPermMapObject = Json.createObjectBuilder().add("userList", newuserBuilder.build())
					.add("groupList", newgroupBuilder.build()).add("permList", newpermBuilder.build()).build();
			permMapListToCreate.add(newPermMapObject);
		}

		JsonObject policyObjectToCreate = Json.createObjectBuilder()
				.add("policyName", jsonObject.getString("policyName"))
				.add("resourceName", jsonObject.getString("resourceName"))
				.add("description", jsonObject.getString("description"))
				.add("repositoryName", jsonObject.getString("repositoryName"))
				.add("repositoryType", jsonObject.getString("repositoryType"))
				.add("isEnabled", jsonObject.getBoolean("isEnabled"))
				.add("isRecursive", jsonObject.getBoolean("isRecursive"))
				.add("isAuditEnabled", jsonObject.getBoolean("isAuditEnabled")).add("permMapList", permMapListToCreate)
				.build();

		System.out.println(policyObjectToCreate);
		return true;
	}

	public static ArrayList<String> getJsonArrayBuld(JsonArray array, List<String> reqList) {

		ArrayList<String> listdata = new ArrayList<String>();

		for (String user : reqList) {
			listdata.add(user);
		}
		for (int i = 0; i < array.size(); i++) {
			listdata.add(array.getString(i));
		}

		Set<String> hs = new HashSet<>();
		hs.addAll(listdata);
		listdata.clear();
		listdata.addAll(hs);

		return listdata;
	}
}
