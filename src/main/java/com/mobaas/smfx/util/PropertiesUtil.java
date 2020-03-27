package com.mobaas.smfx.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class PropertiesUtil {

	private static Properties properties;
	
	public static Properties load(Environment env) {
		if (properties != null)
			return properties;
		
		properties = new Properties();
		try {
			properties.load( new ClassPathResource("application.properties").getInputStream() );
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String profile = env.getProperty("spring.profiles.active"); // 优先从环境变量加载外部配置
		if (profile != null && profile.length() > 0) {
			try {
				Properties props2 = new Properties();
				props2.load( new FileSystemResource("/Users/billyzh/mobaas/smfx/config/application-" + profile + ".properties").getInputStream() );
				for (Object key : props2.keySet()) {
					properties.put(key, props2.get(key));
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			profile = properties.getProperty("spring.profiles.active"); // 加载ClassPath内的配置
			if (profile != null && profile.length() > 0) {
				try {
					Properties props2 = new Properties();
					props2.load( new ClassPathResource("application-" + profile + ".properties").getInputStream() );
					for (Object key : props2.keySet()) {
						properties.put(key, props2.get(key));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return properties;
	}
}
