package cn.hellyuestc.caiyuan.entity;

import java.util.Date;
import java.util.List;

public class Passage {

	private long id;
	private long expertId;
	private String title;
	private String content;
	private int scanCount;
	private int commentCount;
	private int likeCount;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User expert;
	private List<CommentForPassage> commentList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getExpertId() {
		return expertId;
	}
	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScanCount() {
		return scanCount;
	}
	public void setScanCount(int scanCount) {
		this.scanCount = scanCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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
	public User getExpert() {
		return expert;
	}
	public void setExpert(User expert) {
		this.expert = expert;
	}
	public List<CommentForPassage> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentForPassage> commentList) {
		this.commentList = commentList;
	}
	
}
