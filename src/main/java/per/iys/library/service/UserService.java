package per.iys.library.service;

import per.iys.library.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名获取用户信息
     */
    User getUserByAccount(String account);

    /**
     * 修改用户信息
     */
    int modifyUserById(User user);

    /**
     * 保存新用户
     */
    int saveUser(User user);

    /**
     * 分页查询用户列表
     *
     * @param pageNo   页码 beginNo = (pageNo - 1) * pageSize
     * @param pageSize 每页条目
     */
    List<User> queryUserListByConditionForPage(User user, Integer pageNo, Integer pageSize);

    /**
     * 根据 user.account 查找总记录条目
     *
     * @param user 使用到 account 属性
     */
    int getUserCountByConditionForPage(User user);

    /**
     * 根据 id 查找用户
     */
    User getUserById(String id);

    /**
     * 根据 id 删除用户
     */
    int removeUserById(String id);
}
