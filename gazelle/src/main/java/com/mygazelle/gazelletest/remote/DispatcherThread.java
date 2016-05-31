package com.mygazelle.gazelletest.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.mygazelle.gazelletest.config.RemoteSource;

public class DispatcherThread implements Runnable{

	//private static ArrayList<RemoteSource> allSourcesArrayList = new ArrayList<RemoteSource>();
	private ArrayList<String> allFilesArrayList = null;
	private RemoteConnection connection;
	
	    public DispatcherThread(){
	    	
	    }
	    
	    public DispatcherThread(RemoteConnection connection){
	    	this.connection = connection;
	    }

	    public void run(){
		    // TODO Auto-generated method stub
	    	System.out.println("inside run");
		    System.out.println("Fetch the files from the remote folders");
		    ChannelSftp sftp = connection.remoteConnect();
		    getAllFiles(sftp,connection.getSource());
		    System.out.println(allFilesArrayList.size());
		    ExecutorService executor=Executors.newFixedThreadPool(5);
		    for(int i=0;i<allFilesArrayList.size();i++)
		        {
			       Runnable worker=new WorkerThread(allFilesArrayList.get(i),connection);
			       executor.execute(worker);
		        }
		    executor.shutdown();
		}
	
		public void getAllFiles(ChannelSftp sftp,RemoteSource remoteSource) {
			allFilesArrayList = new ArrayList<String>();
	        String folderPath = remoteSource.getFileRegex();
	        Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)(txt|doc|csv|pdf))$)");
	        try{
	        List list = sftp.ls(folderPath);
	        for(Object obj:list){
	        	LsEntry entry = (LsEntry)obj;
	        	Matcher m = pattern.matcher(entry.getFilename());
	        	if(m.matches()){
	        		allFilesArrayList.add(folderPath+"/"+entry.getFilename());
	        	}
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		}
		/*
		 public static void main(String args[]){
		    	RemoteSource source1 = new RemoteSource();
		    	source1.setUserName("suresh");
		    	source1.setHost("slave");
		    	source1.setFileRegex("/home/suresh/temp");
		    	source1.setPassword("root123");
		    	RemoteSource source2 = new RemoteSource();
		    	source2.setUserName("suresh");
		    	source2.setHost("slave1");
		    	source2.setFileRegex("/home/suresh/temp");
		    	source2.setPassword("root123");
		    	allSourcesArrayList.add(source1);
		    	allSourcesArrayList.add(source2);
		    	ExecutorService executor = Executors.newFixedThreadPool(5);
		    	for(int i=0;i<allSourcesArrayList.size();i++){
			    Runnable dispatcher=new DispatcherThread(new RemoteConnection(allSourcesArrayList.get(i)));
			    executor.execute(dispatcher);
		    	}
		    	executor.shutdown();
		    }*/
		
	}


		
	

