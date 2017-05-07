package cn.hellyuestc.caiyuan.entity;

import java.util.Date;

public class QuestionDraft {

	private long id;
	private long userId;
	private long topicId;
	private String title;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;
	private Topic topic;
	
}
