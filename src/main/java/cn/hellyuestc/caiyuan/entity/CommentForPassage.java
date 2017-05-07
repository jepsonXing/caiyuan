package cn.hellyuestc.caiyuan.entity;

import java.util.Date;
import java.util.List;

public class CommentForPassage {

	private long id;
	private long userId;
	private long passageId;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;
	private List<CommentForCommentOfPassage> commentList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPassageId() {
		return passageId;
	}
	public void setPassageId(long passageId) {
		this.passageId = passageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CommentForCommentOfPassage> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentForCommentOfPassage> commentList) {
		this.commentList = commentList;
	}
	
	
	
}
