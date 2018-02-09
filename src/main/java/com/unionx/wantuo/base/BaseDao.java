package com.unionx.wantuo.base;

import java.util.List;
import java.util.Map;


public interface BaseDao<T> {

	public Integer save(T t);
	
	public List<T> queryByList(Map<Object, Object> map);
	/**
	 * web
	 * @param map
	 * @return
	 */
	public List<T> queryByListWeb(Map<Object, Object> map);
	
	public Integer update(T t);
	
	public Integer getCount(Map<Object, Object> map);
	
	/**
	 * web 端
	 * @param t
	 * @return
	 */
	public Integer getCountWeb(Map<Object, Object> map);

	public T getById(T t);
	
	
}
