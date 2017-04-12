package com.team.goyea.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yea.orm.dao.AbstractBaseDAO;

@Repository
public class CommonDao<T> extends AbstractBaseDAO<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2321684065292509290L;
	@Autowired
	private SqlSessionTemplate readSessionTemplate;
	
	@Autowired
	private SqlSessionTemplate writeSessionTemplate;
	
	public SqlSessionTemplate getReadSessionTemplate() {
		return this.readSessionTemplate;
	}
	
	public SqlSessionTemplate getWriteSessionTemplate() {
		return this.writeSessionTemplate;
	}
}
