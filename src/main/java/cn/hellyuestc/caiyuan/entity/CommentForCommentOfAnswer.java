package cn.hellyuestc.caiyuan.entity;

import java.util.Date;

public class CommentForCommentOfAnswer {
	
	private long id;
	private long userId;
	private long commentId;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;

}
