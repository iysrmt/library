package per.iys.library.service;

import per.iys.library.domain.Book;
import per.iys.library.domain.Inventory;

import java.util.List;

public interface BookService {

    /**
     * 根据条件查询图书
     *
     * @param book     图书
     * @param pageNo   页码 beginNo = (pageNo - 1) * pageSize
     * @param pageSize 每页条目
     * @return
     */
    List<Book> queryBookListByConditionForPage(Book book, Integer pageNo, Integer pageSize);

    /**
     * 根据条件查询图书条目数
     *
     * @param book 图书
     * @return
     */
    int getBookCountByConditionForPage(Book book);


    /**
     * 添加图书
     *
     * @param book      图书
     * @param inventory 库存
     * @return
     */
    void saveBook(Book book, Inventory inventory);

    /**
     * 查看是否已存在 isbn 编号的图书
     *
     * @param isbn
     * @return
     */
    int getBookCountByIsbn(String isbn);

    /**
     * 根据 id 获取图书
     *
     * @param id
     * @return
     */
    Book getBookById(String id);

    /**
     * 根据图书 id 删除图书 与 库存
     *
     * @param id
     * @return
     */
    void removeBookById(String id);

    /**
     * 根据 id 获取图书 用于编辑修改 (不连接 category)
     *
     * @param id
     * @return
     */
    Book getBookByIdForEdit(String id);


    /**
     * 修改图书信息
     *
     * @param book      图书
     * @param inventory 库存
     */
    void modifyBookById(Book book, Inventory inventory);
}
