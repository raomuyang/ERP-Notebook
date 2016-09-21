package org.jufe.erp.utils.mapdb.db;


import org.jufe.erp.utils.FileUtils;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapDBVolume<E extends MapDBInfo> {


	private Map<String, E> map_db;

	private DB db = null;

	private String db_file_name = "file";

	private String table_name;


	public MapDBVolume(Class<?> clazz) {
		String splits[] = clazz.getName().split("\\.");
		this.table_name = splits[splits.length - 1];
		this.db_file_name = splits[splits.length - 1];
		FileUtils.mkParentDir(new File(getRoot() + File.separator + db_file_name));
	}

	public Map<String, E> getFileMap() {
		init();
		return map_db;
	}

	public E getInfoByEnID(String entity_id) {
		try {
			init();
			return map_db.get(entity_id);
		} catch (Throwable e) {
			resetDB();
			return null;
		}
	}

	public void alter(E info) {
		try {
			init();
			map_db.put(info.getKey(), info);
			commit();
		} catch (Throwable e) {
			resetDB();
		}
	}

	public void save(Collection<E> infos) {
		try {
			if (infos == null || infos.size() < 1)
				return;
			init();
			for (E info : infos)
				map_db.put(info.getKey(), info);
			commit();
		} catch (Throwable e) {
			resetDB();
		}
	}

	public void removeByID(String entity_id) {
		try {
			init();
			if (map_db.containsKey(entity_id))
				map_db.remove(entity_id);
			commit();
		} catch (Throwable e) {

			resetDB();
		}
	}

	public void removeALL() {
		try {
			init();
			map_db.clear();
			commit();
		} catch (Throwable e) {

			resetDB();
		}
	}

	// 从mapdb中获取map
	private void init() {
		try {
			if (db == null)
				db = getMapDBInstance();

			if (map_db == null) {
				BTreeMap t = db.getTreeMap(this.table_name);
				map_db =  db.getTreeMap(this.table_name);
			}
		} catch (Throwable e) {
			db = resetDB();
			if (db == null) {
				db = getMapDBInstance();
			}
			if (db == null && map_db == null) {
				map_db = new HashMap<String, E>();
			}
			if (map_db == null) {
				map_db = db.getTreeMap(this.table_name);
			}
		}
	}

	private void commit() {
		try {
			db.commit();
		} catch (Throwable e) {
		}
	}

	private DB getMapDBInstance() {
		if (null == db) {
			FileUtils.mkParentDir(new File(getRoot() + File.separator + db_file_name));
			try {
				db = DBMaker.newFileDB(new File(getRoot() + File.separator + db_file_name))
						.closeOnJvmShutdown().make();
			} catch (Throwable e) {
				return resetDB();
			}
		}
		return db;
	}

	private DB resetDB() {
		try {
			new File(getRoot()).delete();
			new File(getRoot() + File.separator + db_file_name).delete();
			new File(getRoot() + File.separator + db_file_name + ".p").delete();
			new File(getRoot() + File.separator + db_file_name + ".t").delete();
			FileUtils.mkParentDir(new File(getRoot() + File.separator + "ss"));
			db = DBMaker.newFileDB(new File(getRoot() + File.separator + db_file_name)).closeOnJvmShutdown()
					.make();
		} catch (Throwable e) {
			db = null;
		}
		return db;
	}


	public static String getRoot(){
		String path = System.getProperties().getProperty("user.home") + File.separator + ".mapdbs";
		return path;
	}
}
