package com.example.src.main.bloggingapp.exception;

public class ApiResponse {
	private String msg;
	private boolean status;
	public ApiResponse(String msg, boolean status) {
		this.msg=msg;
		this.status=status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ApiResponse [msg=" + msg + ", status=" + status + "]";
	}

}
