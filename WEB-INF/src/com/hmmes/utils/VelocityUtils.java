package com.hmmes.utils;

import org.apache.velocity.VelocityContext;

public class VelocityUtils{
	
	public static VelocityContext getContext(){
		VelocityContext context = new VelocityContext();
		context.put("PathUtil", new PathUtil());
		context.put("DateUtil", new DateUtil());
		context.put("StringUtil", new StringUtil());
		context.put("basePath", PathUtil.getBasePath());
		return context;
	}
	
}

