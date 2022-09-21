package com.egiftcard.exception;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {

	private String errorMessage;
	private String errorCode;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss" )
	private LocalDateTime timeStamp;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime localDate) {
		this.timeStamp = localDate;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", timeStamp="
				+ timeStamp + "]";
	}
	
	
	
}
