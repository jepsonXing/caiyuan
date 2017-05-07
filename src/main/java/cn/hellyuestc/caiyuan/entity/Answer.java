package cn.hellyuestc.caiyuan.entity;

import java.util.Date;
import java.util.List;

public class Answer {
	
	private long id;
	private long questionId;
	private long userId;
	private String content;
	private int likeCount;
	private int unlikeCount;
	private int commentCount;
	private Date gmtCreate;
	private Date gmt_modified;
	
	private User user;
	private List<CommentForAnswer> commentList;
	
}
