package com.codered.dataobject;

public class Category {

	private int categoryOid;
	private String categoryName;

	public int getCategoryOid() {
		return categoryOid;
	}

	public void setCategoryOid(int categoryOid) {
		this.categoryOid = categoryOid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryOid=" + categoryOid + ", categoryName=" + categoryName + "]";
	}

}
