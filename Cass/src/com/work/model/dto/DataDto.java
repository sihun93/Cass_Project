/**
 * 
 */
package com.work.model.dto;

/**
 * @author 백시훈
 */
public class DataDto {
	private int count;
	private String date;
	public DataDto() {
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @param count
	 * @param date
	 */
	public DataDto(int count, String date) {
		super();
		this.count = count;
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[count=");
		builder.append(count);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
	
}
