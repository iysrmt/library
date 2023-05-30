package per.iys.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.library.domain.User;
import per.iys.library.mapper.UserMapper;
import per.iys.library.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    @Override
    public int modifyUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> queryUserListByConditionForPage(User user, Integer pageNo, Integer pageSize) {
        Integer beginNo = (pageNo - 1) * pageSize;

        Map<String, Object> map = new HashMap<>();

        map.put("user", user);
        map.put("beginNo", beginNo);
        map.put("pageSize", pageSize);

        return userMapper.selectUserListByConditionForPage(map);
    }

    @Override
    public int getUserCountByConditionForPage(User user) {
        return userMapper.selectUserCountByConditionForPage(user);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int removeUserById(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
