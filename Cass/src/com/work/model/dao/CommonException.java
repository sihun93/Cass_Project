package com.work.model.dao;

import com.work.model.dto.MessageEntity;

public class CommonException extends Exception {
	private MessageEntity entity;
	
	public CommonException() {}
	
	public CommonException(MessageEntity entity) {
		this.entity = entity;
	}
	
	public MessageEntity getMessageEntity() {
		return entity;
	}
}
