package com.mygazelle.gazelletest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.mygazelle.gazelletest.config.RemoteSourceConfig;
import com.mygazelle.gazelletest.config.HelloConfiguration;
import com.mygazelle.gazelletest.config.RemoteSource;
import com.mygazelle.gazelletest.health.TemplateHealthCheck;
import com.mygazelle.gazelletest.remote.DispatcherThread;
import com.mygazelle.gazelletest.remote.RemoteConnection;
import com.mygazelle.gazelletest.resources.HelloResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloApplication extends Application<RemoteSourceConfig>{
	public static void main(String[] args) throws Exception {
        new HelloApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RemoteSourceConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(RemoteSourceConfig configuration,
                    Environment environment) {
    	System.out.println("inside run");
    	List remoteSourceList = configuration.getSourceList();
    	ExecutorService executor = Executors.newFixedThreadPool(5);
    	for(int i=0;i<remoteSourceList.size();i++){
    		RemoteConnection remoteConnection = new RemoteConnection(
    				(RemoteSource)remoteSourceList.get(i));
		    Runnable dispatcher=new DispatcherThread(remoteConnection);
		    System.out.println("dispatcher");
		    executor.execute(dispatcher);
	    	}
        // nothing to do yet
    	/*Yaml yaml = new Yaml();
    	System.out.println(configuration.getConfigPath());
    	InputStream in = null;
    	try{
    	in = new FileInputStream(configuration.getConfigPath());
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
    	System.out.println(in.hashCode());
    	AppConfig appConfig = yaml.loadAs(in, AppConfig.class);
    	List list = appConfig.getSourceList();
    	System.out.println(list.size());
    	Sources src = (Sources)list.get(0);
    	System.out.println(src.getFileRegex());
    	System.out.println(configuration.getTemplate());*/
    	final HelloResource resource = new HelloResource(
    	        //configuration.getTemplate(),
    	        //configuration.getDefaultName()
    			"Hello","Hi"
    	    );
    	 final TemplateHealthCheck healthCheck =
    		        new TemplateHealthCheck("Hi");
    	 environment.healthChecks().register("template", healthCheck);
    	 environment.jersey().register(resource);
    	 
    }

}
