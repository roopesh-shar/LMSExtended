package in.sg.rpc.common.domain;

import java.io.File;

public class UploadData {

	String courseName;
	String uploadModule;
	File fileToUpload;

	UploadData(){
		
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUploadModule() {
		return uploadModule;
	}

	public void setUploadModule(String uploadModule) {
		this.uploadModule = uploadModule;
	}

	public File getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

}

