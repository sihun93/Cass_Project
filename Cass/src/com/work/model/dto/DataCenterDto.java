/**
 * 
 */
package com.work.model.dto;

import java.util.HashMap;

/**
 * @author qorwl
 *
 */
public class DataCenterDto {
	private String memberId;
	private HashMap<Integer, DataDto> dataMap = new HashMap<Integer, DataDto>();
	private char check;
	public DataCenterDto() {
	}
	/**
	 * @param memberId
	 * @param dataMap
	 * @param check
	 */
	public DataCenterDto(String memberId, HashMap<Integer, DataDto> dataMap, char check) {
		super();
		this.memberId = memberId;
		this.dataMap = dataMap;
		this.check = check;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the dataMap
	 */
	public HashMap<Integer, DataDto> getDataMap() {
		return dataMap;
	}
	/**
	 * @param dataMap the dataMap to set
	 */
	public void setData(int key, DataDto dto) {
		dataMap.put(key, dto);
	}
	public DataDto getData(int key) {
		return dataMap.get(key);
	}
	
	/**
	 * @param dataMap the dataMap to set
	 */
	public void setDataMap(HashMap<Integer, DataDto> dataMap) {
		this.dataMap = dataMap;
	}
	/**
	 * @return the check
	 */
	public char getCheck() {
		return check;
	}
	/**
	 * @param check the check to set
	 */
	public void setCheck(char check) {
		this.check = check;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[memberId=");
		builder.append(memberId);
		builder.append(", dataMap=");
		builder.append(dataMap);
		builder.append(", check=");
		builder.append(check);
		builder.append("]");
		return builder.toString();
	}
	 
}