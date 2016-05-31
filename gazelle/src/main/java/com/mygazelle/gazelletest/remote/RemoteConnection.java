package com.mygazelle.gazelletest.remote;

import com.mygazelle.gazelletest.config.RemoteSource;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteConnection {
    
	  private RemoteSource source = null; 
	
	  public RemoteConnection(){
		  
	  }
	 
	  public RemoteConnection(RemoteSource source){
		  this.source = source;
	  }
	  
	  public RemoteSource getSource(){
		  return this.source;
	  }
	  
	  public ChannelSftp remoteConnect(){
		   String user = source.getUserName();
		   String host = source.getHost();
		   String password = source.getPassword();
		   JSch jsch=new JSch();
		   ChannelSftp sftp = null;
		   JSch.setConfig("StrictHostKeyChecking", "no");
		   Properties config = new Properties(); 
		    try{
		       config.put("StrictHostKeyChecking", "no");
		       Session session=jsch.getSession(user, host, 22);
		       session.setPassword(password);
		       session.setConfig(config);
		       session.connect();
		       sftp = (ChannelSftp)session.openChannel("sftp");
		       sftp.connect();
		    }
		    catch(JSchException e){
		    e.printStackTrace();
		    }
		   return sftp;
	  }
}
