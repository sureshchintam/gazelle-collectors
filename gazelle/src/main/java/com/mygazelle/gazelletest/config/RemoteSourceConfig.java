package com.mygazelle.gazelletest.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class RemoteSourceConfig extends Configuration{
	
	
	private List<RemoteSource> sourceList;

	 @JsonProperty
	public List<RemoteSource> getSourceList() {
		return sourceList;
	}
}
