package com.mar.controller.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {
	private List<Integer> msgids = new ArrayList<>();
	public Message() {
		this.success = false;
	}

	private boolean success; //是否操作成功
	private Map result = new HashMap(0); //返回对象
	private String message; //操作后提示信息

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean getSuccess() {
		return success;
	}

	public List<Integer> getMsgids() {
		return msgids;
	}

	public void setMsgids(List<Integer> msgids) {
		this.msgids = msgids;
	}
}
