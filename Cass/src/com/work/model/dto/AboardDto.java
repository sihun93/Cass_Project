/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * @author 백시훈
 */
public class AboardDto {
	private String qboardNum;
	private String aboardWriter;
	private String aboardContent;
	private String aboardDate;
	
	public AboardDto() {
	}

	/**
	 * @param qboardNum
	 * @param aboardWriter
	 * @param aboardContent
	 * @param aboardDate
	 */
	public AboardDto(String qboardNum, String aboardWriter, String aboardContent, String aboardDate) {
		super();
		this.qboardNum = qboardNum;
		this.aboardWriter = aboardWriter;
		this.aboardContent = aboardContent;
		this.aboardDate = aboardDate;
	}

	/**
	 * @return the qboardNum
	 */
	public String getQboardNum() {
		return qboardNum;
	}

	/**
	 * @param qboardNum the qboardNum to set
	 */
	public void setQboardNum(String qboardNum) {
		this.qboardNum = qboardNum;
	}

	/**
	 * @return the aboardWriter
	 */
	public String getAboardWriter() {
		return aboardWriter;
	}

	/**
	 * @param aboardWriter the aboardWriter to set
	 */
	public void setAboardWriter(String aboardWriter) {
		this.aboardWriter = aboardWriter;
	}

	/**
	 * @return the aboardContent
	 */
	public String getAboardContent() {
		return aboardContent;
	}

	/**
	 * @param aboardContent the aboardContent to set
	 */
	public void setAboardContent(String aboardContent) {
		this.aboardContent = aboardContent;
	}

	/**
	 * @return the aboardDate
	 */
	public String getAboardDate() {
		return aboardDate;
	}

	/**
	 * @param aboardDate the aboardDate to set
	 */
	public void setAboardDate(String aboardDate) {
		this.aboardDate = aboardDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[qboardNum=");
		builder.append(qboardNum);
		builder.append(", aboardWriter=");
		builder.append(aboardWriter);
		builder.append(", aboardContent=");
		builder.append(aboardContent);
		builder.append(", aboardDate=");
		builder.append(aboardDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
