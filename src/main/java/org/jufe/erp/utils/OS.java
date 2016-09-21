package org.jufe.erp.utils;

import java.util.Properties;
import java.util.Set;

public class OS {
	Properties props;

	public OS() {
		this.props = System.getProperties();
	}

	public Set<Object> keySet() {
		return props.keySet();
	}

	public Object getByKey(String key) {
		return this.props.get(key);
	}

	public String getOS() {
		String thisos = "unknown";
		String os = this.props.get("os.name").toString().toLowerCase();
		if (os.toLowerCase().contains("mac")) {
			thisos = "mac";
		} else if (os.toLowerCase().contains("darwin")) {
			thisos = "mac";
		} else if (os.toLowerCase().contains("ubuntu")) {
			thisos = "ubuntu";
		} else if (os.toLowerCase().contains("debian")) {
			thisos = "ubuntu";
		} else if (os.toLowerCase().contains("window")) {
			// 暂不编译win8 win10的dsrc
			try {
				String arch = this.props.get("os.arch").toString().toLowerCase();
				if (arch.contains("64")) {
					thisos = "window" + "_64";
				} else {
					thisos = "window" + "_32";
				}
			} catch (Exception e) {
				thisos = "window";
			}
		} else if (os.toLowerCase().contains("centos")) {
			thisos = "centos";
		} else if (os.toLowerCase().contains("red")) {
			thisos = "centos";
		} else if (os.toLowerCase().contains("linux")) {
			thisos = "linux";
		} else {
			thisos = "linux";
		}
		return thisos;
	}

	public static boolean isWin() {
		return new OS().getOS().toLowerCase().contains("win");
	}

	public static boolean isMac() {
		return "mac".equals(new OS().getOS());
	}

	public static boolean isCentOS() {
		return "centos".equals(new OS().getOS());
	}

	public static boolean isUbuntu() {
		return "ubuntu".equals(new OS().getOS());
	}
}
