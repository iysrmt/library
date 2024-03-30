package per.iys.library.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import per.iys.library.commons.domain.Result;
import per.iys.library.domain.*;
import per.iys.library.service.BookService;
import per.iys.library.service.BorrowService;
import per.iys.library.service.DictValueService;
import per.iys.library.service.InventoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/workbench")
public class WorkbenchController {

    private final BookService bookService;
    private final DictValueService dictValueService;
    private final InventoryService inventoryService;
    private final BorrowService borrowService;

    @Autowired
    public WorkbenchController(BookService bookService, DictValueService dictValueService, InventoryService inventoryService, BorrowService borrowService) {
        this.bookService = bookService;
        this.dictValueService = dictValueService;
        this.inventoryService = inventoryService;
        this.borrowService = borrowService;
    }

    /**
     * 工作台主页
     */
    @GetMapping("/index")
    public String toWorkbench() {
        return "workbench/index";
    }

    /**
     * 添加图书页
     */
    @GetMapping("/book/add")
    public String toAddBook(HttpSession session, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        // 普通用户禁止访问
        if ("3".equals(sessionUser.getPermissions())) {
            return "redirect:/workbench/index";
        }

        List<DictValue> categoryList = dictValueService.queryDictValueListByTypeCode("category");
        model.addAttribute("categoryList", categoryList);

        return "workbench/book/add";
    }


    /**
     * 添加图书
     *
     * @param book      图书
     * @param inventory 库存
     */
    @PostMapping("/book/add")
    public @ResponseBody Result<?> addBook(Book book, Inventory inventory, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 普通用户禁止添加图书
        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        if (book.getIsbn().length() != 13) {
            return Result.fail().message("ISBN长度应为13位!");
        }

        int bookCountByIsbn = bookService.getBookCountByIsbn(book.getIsbn());
        if (bookCountByIsbn == 1) {
            return Result.fail().message("ISBN: " + book.getIsbn() + " 已存在!");
        }

        // 二次封装
        book.setId(IdUtil.fastSimpleUUID());
        book.setCreateBy(sessionUser.getId());
        book.setCreateTime(DateUtil.now());

        inventory.setId(IdUtil.fastSimpleUUID());
        inventory.setBookId(book.getId());

        bookService.saveBook(book, inventory);

        return Result.ok();
    }

    /**
     * 图书列表
     */
    @GetMapping("/book/list")
    public String toBookList(Model model) {

        List<DictValue> categoryList = dictValueService.queryDictValueListByTypeCode("category");
        int count = bookService.getBookCountByConditionForPage(null);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("count", count);

        return "workbench/book/list";
    }

    /**
     * 根据条件分页响应图书
     */
    @GetMapping("/book/queryBookListByConditionForPage")
    public @ResponseBody Result<?> queryBookListByConditionForPage(Book book, int pageNo, int pageSize) {

        List<Book> bookList = bookService.queryBookListByConditionForPage(book, pageNo, pageSize);
        int count = bookService.getBookCountByConditionForPage(book);

        Map<String, Object> map = new HashMap<>();
        map.put("bookList", bookList);
        map.put("count", count);

        return Result.ok(map);
    }

    /**
     * 跳转到图书详情页
     *
     * @param id 图书 id
     */
    @GetMapping("/book/detail")
    public String toDetail(String id, Model model) {

        Book book = bookService.getBookById(id);
        Inventory inventory = inventoryService.getInventoryByBookId(id);
        List<DictValue> categoryList = dictValueService.queryDictValueListByTypeCode("category");

        model.addAttribute("book", book);
        model.addAttribute("inventory", inventory);
        model.addAttribute("categoryList", categoryList);

        return "workbench/book/detail";
    }

    /**
     * 删除图书
     */
    @DeleteMapping("/book/remove")
    public @ResponseBody Result<?> removeBook(String id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 普通用户禁止删除图书
        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        bookService.removeBookById(id);

        return Result.ok();
    }

    /**
     * 获取图书信息用于编辑
     */
    @GetMapping("/book/edit")
    public @ResponseBody Result<?> editBookShow(String id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 普通用户禁止修改图书
        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        Book book = bookService.getBookByIdForEdit(id);
        Inventory inventory = inventoryService.getInventoryByBookId(id);

        Map<String, Object> map = new HashMap<>();
        map.put("book", book);
        map.put("inventory", inventory);

        return Result.ok(map);
    }

    /**
     * 修改图书信息
     *
     * @param book  图书
     * @param count 库存
     */
    @PutMapping("/book/edit")
    public @ResponseBody Result<?> editBook(Book book, String count, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 普通用户禁止修改图书
        if ("3".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        Inventory inventory = inventoryService.getInventoryByBookId(book.getId());
        inventory.setCount(count);
        book.setEditBy(sessionUser.getId());
        book.setEditTime(DateUtil.now());

        bookService.modifyBookById(book, inventory);

        return Result.ok();
    }

    /**
     * 借问图书
     *
     * @param id 图书 id
     */
    @PutMapping("/book/borrow")
    public @ResponseBody Result<?> borrowBook(String id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 添加借问记录
        Borrow borrow = new Borrow();
        borrow.setId(IdUtil.fastSimpleUUID());
        borrow.setBookId(id);
        borrow.setUserId(sessionUser.getId());
        borrow.setCreateTime(DateUtil.now());
        borrow.setDeadline(DateUtil.nextMonth().toString());

        if (borrowService.saveBorrow(borrow)) {
            return Result.ok().message("借问成功, 请于: " + borrow.getDeadline() + " 前归还!");
        }

        return Result.fail().message("库存不足！");

    }

    /**
     * 跳转到借阅清单页
     */
    @GetMapping("/book/borrow")
    public String toBorrow(HttpSession session, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        List<Borrow> borrowList;

        if (!"3".equals(sessionUser.getPermissions())) {
            // 管理员查看所有借阅记录
            borrowList = borrowService.AllBorrowList();
        } else {
            // 普通用户查看自己的借阅记录
            borrowList = borrowService.queryBorrowListByUserId(sessionUser.getId());
        }

        model.addAttribute("borrowList", borrowList);
        return "workbench/book/borrow";
    }

    /**
     * 归还图书
     *
     * @param id 借阅记录的 id
     */
    @PutMapping("/book/revert")
    public @ResponseBody Result<?> revert(String id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        // 通过借阅id获取借阅记录
        Borrow borrow = borrowService.getBorrowById(id);

        // 禁止 普通用户通过接口还其他用户借阅的图书
        if ("3".equals(sessionUser.getPermissions())) {
            String userId = borrow.getUserId();
            if (!userId.equals(sessionUser.getId())) {
                return Result.fail().message("非法操作!");
            }
        }

        // 删除借阅记录
        borrowService.removeBorrowById(id);

        return Result.ok();
    }
}
