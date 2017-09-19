package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface GzhsJtkqMapper<T> extends BaseMapper<T> {
	
	/**
	 *查询全部的
	 * @return
	 */
	public List<T> queryAllList();

    /**
	 * 查询sbmc,kqrq,bc
	 * @return
	 */
    public T queryBySbmcAndKqrqAndBc(String sbmc,java.sql.Date kqrq,Integer bc);

}
