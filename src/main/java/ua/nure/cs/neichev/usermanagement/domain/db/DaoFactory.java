package ua.nure.cs.neichev.usermanagement.domain.db;

import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class DaoFactory {
	private static final String U_DAO = "dao.ua.nure.cs.neichev.usermanagement.domain.db.UserDao";
	private final Properties properties;
	
	private final static DaoFactory INSTANCE = new DaoFactory();
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	private DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ConnectionFactory getConnectionFactory() {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	
	public UserDao getUserDao() {
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(U_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
