package com.ems.model;

public class TaskSummary {
	private int Assigned;
	private int Approved;
	private int Rejected;
	private int Submitted;
	public TaskSummary(int assigned, int approved, int rejected, int submitted) {
		super();
		Assigned = assigned;
		Approved = approved;
		Rejected = rejected;
		Submitted = submitted;
	}
	public int getAssigned() {
		return Assigned;
	}
	public void setAssigned(int assigned) {
		Assigned = assigned;
	}
	public int getApproved() {
		return Approved;
	}
	public void setApproved(int approved) {
		Approved = approved;
	}
	public int getRejected() {
		return Rejected;
	}
	public void setRejected(int rejected) {
		Rejected = rejected;
	}
	public int getSubmitted() {
		return Submitted;
	}
	public void setSubmitted(int submitted) {
		Submitted = submitted;
	}
	public TaskSummary() {
		super();
	}
	

}
