package cn.hellyuestc.caiyuan.entity;

import java.util.Date;
import java.util.List;

public class CommentForAnswer {
	
	private long id;
	private long userId;
	private long answerId;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;
	private List<CommentForCommentOfAnswer> commentList;
	
}
