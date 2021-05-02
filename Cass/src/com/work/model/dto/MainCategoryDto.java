/**
 * 
 */
package com.work.model.dto;

/**
 * @author Administrator
 *
 */
public class MainCategoryDto {
	/** 메인 카테고리 번호*/
	private String mcategoryNum;
	/** 메인 카테고리명*/
	private String mcategoryName;
	public MainCategoryDto() {
	}
	/**
	 * @param mcategoryNum
	 * @param mcategoryName
	 */
	public MainCategoryDto(String mcategoryNum, String mcategoryName) {
		super();
		this.mcategoryNum = mcategoryNum;
		this.mcategoryName = mcategoryName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[mcategoryNum=");
		builder.append(mcategoryNum);
		builder.append(", mcategoryName=");
		builder.append(mcategoryName);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the mcategoryNum
	 */
	public String getMcategoryNum() {
		return mcategoryNum;
	}
	/**
	 * @param mcategoryNum the mcategoryNum to set
	 */
	public void setMcategoryNum(String mcategoryNum) {
		this.mcategoryNum = mcategoryNum;
	}
	/**
	 * @return the mcategoryName
	 */
	public String getMcategoryName() {
		return mcategoryName;
	}
	/**
	 * @param mcategoryName the mcategoryName to set
	 */
	public void setMcategoryName(String mcategoryName) {
		this.mcategoryName = mcategoryName;
	}
	
	
}
