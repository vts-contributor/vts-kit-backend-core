package vn.com.viettel.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class NetworkUtils {
	private static Logger logger = LoggerFactory.getLogger(NetworkUtils.class);
	public static String getCurrentHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
			return "default";
		}
	}
}
