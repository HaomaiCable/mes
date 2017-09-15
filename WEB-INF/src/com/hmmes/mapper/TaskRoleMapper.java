package com.hmmes.mapper;
import java.util.List;
import java.util.Map;
import com.hmmes.model.TaskRoleModel;

/**
 * TaskRole Mapper
 * @author Administrator
 *
 */
public interface TaskRoleMapper<T> extends BaseMapper<T> {

		/**
	 * 查所有
	 * @return
	 */
	public List<T> queryByAll();
    public List<T> queryByGlzId(Integer roleId);
    public T queryByUserId(Integer userId);

    /**
	 * 管理任务查看分类
	 * @param taskroleId
	 * @param gysId
	 */	
	public void addTaskRoleRel(Map<String,Object> map);


	public void deleteTaskRoleRel(Integer roleId);


}
