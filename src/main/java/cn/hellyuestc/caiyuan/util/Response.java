package cn.hellyuestc.caiyuan.util;

public class Response {
	
	private Status status;
	private Object data;
	
	public Response(Status status) {
		super();
		this.status = status;
	}

	public Response(Status status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
}
