package com.team.goyea.launcher;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.yea.remote.netty.server.NettyServer;

public class ServerLauncher {
	private static NettyServer nettyServer;
	private static ClassPathXmlApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext("classpath:application-launcher.xml");
		context.registerShutdownHook();
		nettyServer = (NettyServer) context.getBean("nettyServer");
	}
	
	public static void main(String[] args) throws Throwable {
		if(!StringUtils.isEmpty(System.getProperty("server.port"))){
			int port = Integer.valueOf(System.getProperty("server.port"));
			nettyServer.setPort(port);
		}
		nettyServer.bind();
	}
}
