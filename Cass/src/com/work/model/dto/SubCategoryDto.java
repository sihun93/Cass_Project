/**
 * 
 */
package com.work.model.dto;

/**
 * @author Administrator
 *
 */
public class SubCategoryDto {
	/** 서브 카테고리 번호*/
	private String scategoryNum;
	/** 메인 카테고리 번호*/
	private String mcategoryNum;
	/** 서브 카테고리명*/
	private String scategoryName;
	public SubCategoryDto() {
	}
	
	/**
	 * @param scategoryNum
	 * @param mcategoryNum
	 * @param scategoryName
	 */
	public SubCategoryDto(String scategoryNum, String mcategoryNum, String scategoryName) {
		super();
		this.scategoryNum = scategoryNum;
		this.mcategoryNum = mcategoryNum;
		this.scategoryName = scategoryName;
	}

	/**
	 * @return the scategoryNum
	 */
	public String getScategoryNum() {
		return scategoryNum;
	}
	/**
	 * @param scategoryNum the scategoryNum to set
	 */
	public void setScategoryNum(String scategoryNum) {
		this.scategoryNum = scategoryNum;
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
	 * @return the scategoryName
	 */
	public String getScategoryName() {
		return scategoryName;
	}
	/**
	 * @param scategoryName the scategoryName to set
	 */
	public void setScategoryName(String scategoryName) {
		this.scategoryName = scategoryName;
	}
	
}
