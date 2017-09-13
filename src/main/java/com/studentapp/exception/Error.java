package com.studentapp.exception;

import java.util.ArrayList;
import java.util.List;

public class Error {
	private List<String> messages = new ArrayList<>();
	private int errorCount;

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
}
