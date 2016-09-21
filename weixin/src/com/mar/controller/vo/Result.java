package com.mar.controller.vo;

import java.util.List;

public class Result {
	public Result() {
		this.setSuccess(false);
	}
	private boolean success;
	@SuppressWarnings("rawtypes")
	private List rows;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
