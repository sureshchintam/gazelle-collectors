package com.mygazelle.gazelletest.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSource{
	
	private String source;
	private String fileRegex;
    private String host;
    private String userName;
    private String password;
	private String privateKeyFile;
	private String moveTo;
	private String fileType;
	
    @JsonProperty
   	public String getFileType() {
		return fileType;
	}
    
   	@JsonProperty
	public String getFileRegex() {
		return fileRegex;
	}
	
	@JsonProperty
   	public String getHost() {
		return host;
	}
	
   	@JsonProperty
	public String getUserName() {
		return userName;
	}
	
	@JsonProperty
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public String getPrivateKeyFile() {
		return privateKeyFile;
	}
	
	@JsonProperty
    public String getMoveTo() {
		return moveTo;
	}
	
	@JsonProperty
	public String getSource() {
		return source;
	}
    
	public void setSource(String source) {
		this.source = source;
	}

	public void setFileRegex(String fileRegex) {
		this.fileRegex = fileRegex;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPrivateKeyFile(String privateKeyFile) {
		this.privateKeyFile = privateKeyFile;
	}

	public void setMoveTo(String moveTo) {
		this.moveTo = moveTo;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
