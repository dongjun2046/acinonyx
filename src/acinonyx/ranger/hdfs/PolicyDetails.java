package acinonyx.ranger.hdfs;

import java.util.List;

public class PolicyDetails {
	int policyId;
	String policyName;
	String resourceName;
	boolean isRecursive;
	List<PermList> permList;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public boolean getisRecursive() {
		return isRecursive;
	}

	public void setRecursive(boolean isRecursive) {
		this.isRecursive = isRecursive;
	}

	public List<PermList> getPermList() {
		return permList;
	}

	public void setPermList(List<PermList> permList) {
		this.permList = permList;
	}

	@Override
	public String toString() {
		return "PolicyDetails [policyId=" + policyId + ", policyName=" + policyName + ", resourceName=" + resourceName
				+ ", isRecursive=" + isRecursive + ", permList=" + permList + "]";
	}

}
