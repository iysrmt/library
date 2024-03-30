package per.iys.library.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import per.iys.library.commons.domain.Result;
import per.iys.library.domain.User;
import per.iys.library.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/system/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 跳转到登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String toLogin(HttpSession session) {
        // 判断是否已登录, 如果已登录直接跳转工作页
        Object sessionUser = session.getAttribute("sessionUser");
        if (sessionUser != null) {
            return "redirect:/workbench/index";
        }
        return "system/user/login";
    }

    /**
     * 登录
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public @ResponseBody Result<?> login(User user, HttpSession session) {

        User userByAccount = null;

        // 查用户
        try {
            userByAccount = userService.getUserByAccount(user.getAccount());
        } catch (Exception e) {
            log.error("error: {}", StringUtils.hasText(e.getMessage()) ? e.getMessage() : "系统繁忙, 请稍后重试...", e);
            return Result.fail().message("系统繁忙, 请稍后重试...");
        }

        // 判断用户是否存在
        if (userByAccount == null) {
            return Result.fail().message("用户名不存在!");
        }

        // 用户是否被冻结
        if ("4".equals(userByAccount.getPermissions())) {
            return Result.fail().message("该用户冻结不可用!");
        }

        // 用户存在判断密码
        String userPassword = SecureUtil.md5(user.getPassword());
        if (!userByAccount.getPassword().equals(userPassword)) {
            return Result.fail().message("密码错误!");
        }

        session.setAttribute("sessionUser", userByAccount);
        log.info("用户: {}, 登录系统!", user.getAccount());

        return Result.ok();
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 个人信息页
     *
     * @return
     */
    @GetMapping("/profile")
    public String toProfile() {
        return "system/user/profile";
    }

    /**
     * 更新个人信息
     *
     * @param user
     * @param session
     * @return
     */
    @PutMapping("/profile")
    public @ResponseBody Result<?> modifyUser(User user, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 二次封装
        user.setId(sessionUser.getId());
        user.setEditBy(sessionUser.getId());
        user.setEditTime(DateUtil.now());

        int i = userService.modifyUserById(user);

        if (i == 0) {
            return Result.fail().message("系统繁忙, 请稍后重试...");
        }

        // 更新 Session
        User userByAccount = userService.getUserByAccount(sessionUser.getAccount());
        session.setAttribute("sessionUser", userByAccount);

        return Result.ok();
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @GetMapping("/change")
    public String toChangePwd() {
        return "system/user/change";
    }

    /**
     * 修改个人密码
     *
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @param session
     * @return
     */
    @PutMapping("/change")
    public @ResponseBody Result<?> changePwd(String oldPwd, String newPwd, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        String md5Pwd = SecureUtil.md5(oldPwd);

        if (!sessionUser.getPassword().equals(md5Pwd)) {
            return Result.fail().message("密码错误!");
        }

        sessionUser.setPassword(SecureUtil.md5(newPwd));

        int i = userService.modifyUserById(sessionUser);

        if (i == 0) {
            return Result.fail().message("系统繁忙, 请稍后重试...");
        }

        return Result.ok();
    }

    /**
     * 跳转到 添加用户
     *
     * @return
     */
    @GetMapping("/admin/add")
    public String toAddUser() {
        return "system/user/admin/add";
    }

    /**
     * 添加用户
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/admin/add")
    public @ResponseBody Result<?> addUser(User user, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        // 二次封装
        user.setId(IdUtil.fastSimpleUUID());
        String password = user.getPassword();
        password = SecureUtil.md5(password);
        user.setPassword(password);
        user.setCreateBy(sessionUser.getId());
        user.setCreateTime(DateUtil.now());

        int i = userService.saveUser(user);

        if (i == 0) {
            return Result.fail().message("系统繁忙, 请稍后重试...");
        }

        return Result.ok();
    }

    /**
     * 跳转到 用户列表
     *
     * @param model
     * @return
     */
    @GetMapping("/admin/list")
    public String toList(Model model) {
        int count = userService.getUserCountByConditionForPage(null);
        model.addAttribute("count", count);
        return "system/user/admin/list";
    }

    /**
     * 用户列表
     *
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/queryUserListByConditionForPage")
    public @ResponseBody Result<?> queryUserListByConditionForPage(User user, int pageNo, int pageSize) {

        List<User> userList = userService.queryUserListByConditionForPage(user, pageNo, pageSize);
        int count = userService.getUserCountByConditionForPage(user);

        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("count", count);

        return Result.ok(map);
    }

    /**
     * 跳转到 用户操作详情
     *
     * @param model
     * @return
     */
    @GetMapping("/admin/detail")
    public String toDetail(Model model, String id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "system/user/admin/detail";
    }

    /**
     * 删除用户
     *
     * @param session
     * @param id
     * @return
     */
    @DeleteMapping("/admin/remove")
    public @ResponseBody Result<?> removeUser(HttpSession session, String id) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 非管理员用户
        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        int i = userService.removeUserById(id);

        if (i == 0) {
            return Result.fail().message("系统繁忙, 请稍后重试!");
        }

        return Result.ok();
    }

    /**
     * 更新用户身份
     *
     * @param session
     * @param user
     * @return
     */
    @PutMapping("/admin/edit")
    public @ResponseBody Result<?> editUser(HttpSession session, User user) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 非管理员用户 || 想修改为超级管理 (超级管理有且只有一个)
        if ("3".equals(sessionUser.getPermissions()) || "1".equals(user.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        // 二次封装
        user.setEditBy(sessionUser.getId());
        user.setEditTime(DateUtil.now());

        int i = userService.modifyUserById(user);

        if (i == 0) {
            return Result.fail().message("系统繁忙, 请稍后重试!");
        }

        return Result.ok();
    }
}