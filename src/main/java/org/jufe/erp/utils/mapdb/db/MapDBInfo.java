package org.jufe.erp.utils.mapdb.db;

import java.io.Serializable;

public class MapDBInfo implements Serializable{ 
	private static final long serialVersionUID = -6959059881346584903L;
	String key;

	public MapDBInfo(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
