package per.iys.library.service;

import per.iys.library.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    int modifyUserById(User user);

    /**
     * 保存新用户
     *
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * @param user
     * @param pageNo   页码 beginNo = (pageNo - 1) * pageSize
     * @param pageSize 每页条目
     * @return
     */
    List<User> queryUserListByConditionForPage(User user, Integer pageNo, Integer pageSize);

    /**
     * 根据 user.account 查找总记录条目
     *
     * @param user 使用到 account 属性
     * @return
     */
    int getUserCountByConditionForPage(User user);

    /**
     * 根据 id 查找用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 根据 id 删除用户
     *
     * @param id
     * @return
     */
    int removeUserById(String id);
}
