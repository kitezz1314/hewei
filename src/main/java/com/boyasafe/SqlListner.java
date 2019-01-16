package com.boyasafe;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.boyasafe.mybatis.BaseMapperTest;
import com.boyasafe.wechat.Menu.MainMenu;

public class SqlListner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		BaseMapperTest.init();
		MainMenu mainMenu = new MainMenu();
		try {
			mainMenu.createMenu();
			System.out.println("123456456464645646546");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
