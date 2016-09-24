package org.jufe.erp.utils.mapdb.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jufe.erp.utils.mapdb.lock.Lock;
import org.jufe.erp.utils.mapdb.lock.MapDBLock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapDBDriver<T extends MapDBInfo> {
	Log log = LogFactory.getLog(getClass());
	String lockname;
	Class<?> clazz;

	public MapDBDriver(Class<?> clazz) {
		String splits[] = clazz.getName().split("\\.");
		this.lockname = MapDBVolume.getRoot() + File.separator + splits[splits.length - 1];
		try {
			this.lockname = new File(lockname).getCanonicalPath();
		} catch (IOException e) {
		}
		this.clazz = clazz;
	}

	public T getByEntityID(String entity_id) {
		try {
			ExecutorService service = Executors.newCachedThreadPool();
			Callable<T> thread = FlushDBThreadFactory.getByKeyCallable(this.clazz, entity_id, this.lockname);
			Future<T> future = service.submit(thread);
			T t = future.get();
			return t;
		} catch (Exception e) {
			log.error("Get info fail " + entity_id + " " + e.getMessage(), e);
			return null;
		}
	}



	/**
	 * 同步存储到MapDB
	 * @param info
	 * @return
     */
	public boolean save_sync(T info) {
		if (info == null)
			return false;

		Lock lock = MapDBLock.get(this.lockname);
		lock.obtain();
		try {
			MapDBVolume<T> mapdb = new MapDBVolume<T>(clazz);
			mapdb.alter(info);
		} catch (Throwable e) {
			return false;
		} finally {
			lock.unlock();
		}
		return true;
	}

	public List<T> getAll_sync(){
		try {
			MapDBVolume<T> mapdb = new MapDBVolume<T>(clazz);
			Collection<T> datas = mapdb.getFileMap().values();
			if(datas == null || datas.size() == 0)
				return new ArrayList<>();
			return (List) Arrays.asList(datas.toArray());
		} catch (Throwable e) {
			return new ArrayList<>();
		}
	}

	public T getById(String id){
		MapDBVolume<T> mapdb = new MapDBVolume<T>(clazz);
		return mapdb.getInfoByEnID(id);
	}


	public void removeAll_sync(){
		Lock lock = MapDBLock.get(this.lockname);
		lock.obtain();
		Collection<T> w = null;
		try {
			MapDBVolume<T> mapdb = new MapDBVolume<T>(clazz);
			w = mapdb.getFileMap().values();
			if (w == null)
				return;
			mapdb.removeALL();
		} catch (Throwable e) {
		}
		lock.unlock();
	}


}