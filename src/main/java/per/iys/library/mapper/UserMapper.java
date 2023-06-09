package per.iys.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.library.domain.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int insert(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int insertSelective(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    User selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int updateByPrimaryKeySelective(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int updateByPrimaryKey(User row);

    /**
     * 通过用户名查找用户
     *
     * @param account
     * @return
     */
    User selectUserByAccount(String account);

    /**
     * 根据 user.account 分页模糊查找
     *
     * @param map key: user, beginNo, pageSize
     * @return
     */
    List<User> selectUserListByConditionForPage(Map<String, Object> map);

    /**
     * 根据 user.account 查找总记录条目
     *
     * @param user 使用到 account 属性
     * @return
     */
    int selectUserCountByConditionForPage(User user);

    /**
     * 根据用户 id 查找用户信息 (连接查找)
     *
     * @param id
     * @return
     */
    User selectUserById(String id);
}