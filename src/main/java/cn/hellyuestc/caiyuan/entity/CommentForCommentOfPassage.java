package cn.hellyuestc.caiyuan.entity;

import java.util.Date;

public class CommentForCommentOfPassage {

	private long id;
	private long userId;
	private long commmentId;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;
	
}
