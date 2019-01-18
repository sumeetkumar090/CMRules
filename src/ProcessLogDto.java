
public class ProcessLogDto {

	String timeStamp;
	String caseID;
	String taskID;
	String reason;

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the caseID
	 */
	public String getCaseID() {
		return caseID;
	}

	/**
	 * @param caseID the caseID to set
	 */
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	/**
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * @return the postConditions
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param postConditions the postConditions to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ProcessLogDto [timeStamp=" + timeStamp + ", caseID=" + caseID + ", taskID=" + taskID
				+ ", postConditions=" + reason + ", getTimeStamp()=" + getTimeStamp() + ", getCaseID()=" + getCaseID()
				+ ", getTaskID()=" + getTaskID() + ", getPostConditions()=" + getReason() + "]";
	}

}
