package com.codered.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import com.codered.dataobject.Category;
import com.codered.managebean.UserProfile;
import com.codered.rest.CodeRedServices;

@ManagedBean
public class Advertisement extends UserProfile {

	private String categroy;
	private String requestType;
	private String title;
	private String description;
	private float price;
	private int primaryKey;
	private String dateAdded;
	private String adminMapOid;
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Advertisement() {

	}

	private Map<String, String> categoryMap;
	
	private List<Category>  categoryList;

	@PostConstruct
	public void init() {
		categoryMap = new HashMap<String, String>();
		System.out.println("CAlling store procedure" + categoryMap);
		categoryList = new CodeRedServices().getALLCategory();
		//categoryList = new CodeRedServices().getALLCategoryTestData();
		for (Category catObj : categoryList) {
			categoryMap.put(catObj.getCategoryName(), catObj.getCategoryName());
		}
	}

	public Advertisement(String title, String desc) {
		this.title = title;
		this.description = desc;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, String> getCategoryMap() {
		return categoryMap;
	}

	public String getAdminMapOid() {
		return adminMapOid;
	}

	public void setAdminMapOid(String adminMapOid) {
		this.adminMapOid = adminMapOid;
	}

	public void setCategoryMap(Map<String, String> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void createAd() {
		try {
			ELContext el = FacesContext.getCurrentInstance().getELContext();
			Advertisement adObj = (Advertisement) FacesContext
					.getCurrentInstance().getApplication().getELResolver()
					.getValue(el, null, "advertisement");

			if(adObj != null && adObj.getVzId() == null) {
				adObj.setVzId("V492750");
			}
			adObj.setFirstName("Jayaraj");
			adObj.setLastName("Mani");
			
			System.out.println("CAlling store procedure===>" + adObj);
			 if(file != null) {
				 System.out.println("File " +  file.getFileName() + " is uploaded.");
			 }
			new CodeRedServices().manageAd(adObj);
			System.out.println("Done with store procedure");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Your Advertisement Posted Successfully !."));
			System.out.println("Inside Post Add" + adObj.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Advertisement [categroy=" + categroy + ", requestType="
				+ requestType + ", title=" + title + ", description="
				+ description + ", price=" + price + ", primaryKey="
				+ primaryKey + ", dateAdded=" + dateAdded + ", adminMapOid="
				+ adminMapOid + ", file=" + file + ", categoryMap="
				+ categoryMap + ", categoryList=" + categoryList + "]";
	}

	

}
