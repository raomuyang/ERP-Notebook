package org.jufe.erp.utils.mapdb.db;


import org.jufe.erp.utils.mapdb.lock.Lock;
import org.jufe.erp.utils.mapdb.lock.MapDBLock;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * 将数据库的增查改操作放到线程中
 */
public class FlushDBThreadFactory {
	public static <W extends MapDBInfo> Callable<W> getByKeyCallable(final Class<?> clazz, final String key,
			String localName) {
		return new Callable<W>() {

			@Override
			public W call() throws Exception {
				W w = null;
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					w = mapdb.getInfoByEnID(key);
				} catch (Throwable e) {
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<Collection<W>> getAllCallable(final Class<?> clazz, String localName) {
		return new Callable<Collection<W>>() {

			@Override
			public Collection<W> call() throws Exception {
				Collection<W> w = null;
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					w = mapdb.getFileMap().values();
				} catch (Throwable e) {
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<W> getSaveCallable(final Class<?> clazz, final W w,
			final String localName) {
		return new Callable<W>() {

			@Override
			public W call() throws Exception {
				if (w == null)
					return w;

				Lock lock = MapDBLock.get(localName);
				lock.obtain();
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					mapdb.alter(w);
				} catch (Throwable e) {
				} finally {
					lock.unlock();
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<Collection<W>> getSavesCallable(final Class<?> clazz,
			final Collection<W> w, final String localName) {
		return new Callable<Collection<W>>() {

			@Override
			public Collection<W> call() throws Exception {
				if (w == null)
					return w;

				Lock lock = MapDBLock.get(localName);
				lock.obtain();
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					mapdb.save(w);
				} catch (Throwable e) {
				} finally {
					lock.unlock();
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<W> getRemoveCallable(final Class<?> clazz, final W w,
			final String localName) {
		return new Callable<W>() {

			@Override
			public W call() throws Exception {
				if (w == null)
					return w;
				Lock lock = MapDBLock.get(localName);
				lock.obtain();
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					mapdb.removeByID(w.getKey());
				} catch (Throwable e) {
				} finally {
					lock.unlock();
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<W> getRemoveByKeyCallable(final Class<?> clazz, final String key,
			final String localName) {
		return new Callable<W>() {

			@Override
			public W call() throws Exception {

				Lock lock = MapDBLock.get(localName);
				lock.obtain();
				W w = null;
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					w = mapdb.getInfoByEnID(key);
					if (w == null)
						return w;
					mapdb.removeByID(w.getKey());
				} catch (Throwable e) {
				} finally {
					lock.unlock();
				}
				return w;
			}
		};
	}

	public static <W extends MapDBInfo> Callable<Collection<W>> getRemoveAllCallable(final Class<?> clazz,
			final String localName) {

		return new Callable<Collection<W>>() {

			@Override
			public Collection<W> call() throws Exception {
				Lock lock = MapDBLock.get(localName);
				lock.obtain();
				Collection<W> w = null;
				try {
					MapDBVolume<W> mapdb = new MapDBVolume<W>(clazz);
					w = mapdb.getFileMap().values();
					if (w == null)
						return w;
					mapdb.removeALL();
				} catch (Throwable e) {
				}
				lock.unlock();
				return w;
			}
		};
	}
}
