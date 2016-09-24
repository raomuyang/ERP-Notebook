package org.jufe.erp.utils.mapdb.lock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class MapDBLock implements Lock {
	private String lockFileName = null;
	FileChannel channel = null;
	private static FileLock lock = null;

	public static Lock get(String fileName) {
		MapDBLock d = new MapDBLock(new File(fileName).getAbsolutePath() + "_"
				+ "db.lock");
		return (Lock) d;
	}

	public MapDBLock(String mapdb_root) {
		this.lockFileName = mapdb_root;
	}

	/**
	 * 检测是否被锁定-不建议使用
	 * 
	 * @return true被锁定 ,false空闲
	 * 
	 * */
	public boolean isLocked() {
		File tf = new File(lockFileName);
		if (!tf.exists()) {
			return false;
		}
		FileChannel __channel = null;
		FileLock tl = null;
		try {
			__channel = new RandomAccessFile(tf, "rw").getChannel();
			tl = __channel.tryLock();
			if (tl == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		} finally {
			try {
				if (tl != null) {
					tl.release();
				}
				tl = null;
				if (__channel.isOpen()) {
					__channel.close();
				}
				__channel = null;
				tf = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取锁资源
	 * 
	 * @return true成功锁定目标资源 ,false锁定操作失败
	 * */
	public boolean obtain() {
		try {
			createLocalFile();
			File tf = new File(lockFileName);
			channel = new RandomAccessFile(tf, "rw").getChannel();
//			System.out.println("try obtain");
			lock = channel.lock();
//			System.out.println("end obtain " + lock+" "+channel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 释放锁
	 * */
	public void unlock() {
//		System.out.println("unlock " + lock+" "+channel);
		try { 
			if (this.lock != null) {
				this.lock.release();
			}
			if (channel != null && channel.isOpen()) {
//				System.out.println("is open");
				channel.close();
			}
			lock = null;
			channel = null;
			// this.deleteLocalFile();
		} catch (IOException e) {
		}
	}

	protected void finalize() throws Throwable {
		super.finalize();
	}

	private void createLocalFile() throws IOException {
		try {
			File tf = new File(lockFileName);
			if (!tf.exists()) {
				tf.createNewFile();
			}
			tf = null;
		} catch (IOException e) {
			throw e;
		}
	}

	private void deleteLocalFile() {
		File tf = new File(lockFileName);
		if (tf.exists()) {
			tf.delete();
		}
		tf = null;
	}

	@Override
	public boolean tryLock() {
//		System.out.println("try lock");
		FileChannel __channel = null;
		FileLock tl = null;
		File tf = new File(lockFileName);
		try {
			this.createLocalFile();
			if (!tf.exists()) {
				return false;
			}
			__channel = new RandomAccessFile(tf, "rw").getChannel();
			tl = __channel.tryLock();
			if (__channel != null && __channel.isOpen()) {
				try {
					__channel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (tl == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			if (__channel != null && __channel.isOpen()) {
				try {
					__channel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		}
	}
}