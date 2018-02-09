package com.unionx.wantuo.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.EntityConvertMap;
import com.unionx.wantuo.utils.Pager;

public class BaseServiceImpl<T> {

	@Autowired
	private BaseDao<T> baseDao;

	/**
	 * @Title: save 
	 * @Description: TODO(保存对象) 
	 * @param @param t
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer save(T t) {
		return baseDao.save(t);
	}

	/**
	 * @Title: queryByList
	 * @Description: TODO(分页查询)手机端
	 * @author abner
	 * @return Pager<T> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Pager<T> queryByList(T t,Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		Pager<T> pager = new Pager<T>();
		Integer count = getCount(t,c);
		pager.setCount(count);
		c.setRowCount(count);
		try {
			paramMap = EntityConvertMap.convertBean(t,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
//		System.out.print(paramMap);
		pager.setList(baseDao.queryByList(paramMap));
		return pager;
	}
	
	/**
	 * @Title: queryByList
	 * @Description: TODO(分页查询)web端
	 * @author abner
	 * @return Pager<T> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Pager<T> queryByListWeb(T t,Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		Pager<T> pager = new Pager<T>();
//		Integer count = getCount(t,c);
		Integer count = getCountWeb(t, c);
		pager.setCount(count);
		c.setRowCount(count);
		try {
			paramMap = EntityConvertMap.convertBean(t,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
//		System.out.print(paramMap);
		pager.setList(baseDao.queryByListWeb(paramMap));
		return pager;
	}

	/**
	 * @Title: update
	 * @Description: TODO(修改数据)
	 * @author abner
	 * @return Integer 返回类型
	 * @throws
	 */
	public Integer update(T t) {
		return baseDao.update(t);
	}

	/**
	 * @Title: getCount
	 * @Description: TODO(查询全部条数)
	 * @author abner
	 * @return Integer 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Integer getCount(T t,Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		try {
			paramMap = EntityConvertMap.convertBean(t,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
		return baseDao.getCount(paramMap);
	}
	/**
	 * @Title: getCount  web端
	 * @Description: TODO(查询全部条数)
	 * @author abner
	 * @return Integer 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Integer getCountWeb(T t,Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		try {
			paramMap = EntityConvertMap.convertBean(t,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
		return baseDao.getCountWeb(paramMap);
	}
	
	/**
	 * @Title: getById 
	 * @Description: TODO(根据id查询对象) 
	 * @param @param m
	 * @author abner
	 * @return T 返回类型 
	 * @throws
	 */
	public T getById(T t) {
		return baseDao.getById(t);
	}
}
