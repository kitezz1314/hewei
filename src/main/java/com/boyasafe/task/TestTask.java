package com.boyasafe.task;

import java.util.Date;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boyasafe.wechat.Menu.MainMenu;


/**
 * spring task 定时任务测试，适用于单系统
 * 注意：不适合用于集群
 * @author L.cm
 */
@Component
public class TestTask {
	protected Logger logger = LogManager.getLogger(getClass());
	
	/*@Autowired private CacheManager cacheManager;*/
	
//	@Autowired private RedisTemplate<String, Object> redisTemplate;	
	 @Scheduled(cron="0/10 * *  * * ? ")
	public void cronTest() {
		/*// 测试手动存储cache
		Cache cache = cacheManager.getCache("hour");
		Integer xx = cache.get("x", new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return 111111;
			}			
		});
		// 测试redis
//		redisTemplate.boundListOps("xxxx").leftPush("xxxx");
		
		// 测试注解
		
		logger.debug(xx);
		logger.debug(new Date());*/
		
		/*MainMenu mainMenu = new MainMenu();
		try {
			mainMenu.createMenu();
			System.out.println("123456456464645646546");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
}
