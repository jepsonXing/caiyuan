package cn.hellyuestc.caiyuan.entity;

import java.util.Date;

public class AnswerDraft {
	
	private long id;
	private long userId;
	private long questionId;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	
	private User user;

}
