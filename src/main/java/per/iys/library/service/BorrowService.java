package per.iys.library.service;

import per.iys.library.domain.Borrow;

import java.util.List;

public interface BorrowService {

    /**
     * 保存借问记录
     *
     * @param borrow
     * @return
     */
    boolean saveBorrow(Borrow borrow);

    /**
     * 根据用户 id 查找借阅记录 (连接查找 用于展示)
     *
     * @param userId
     * @return
     */
    List<Borrow> queryBorrowListByUserId(String userId);

    /**
     * 根据 id 获取对应借阅记录
     *
     * @param id
     * @return
     */
    Borrow getBorrowById(String id);

    /**
     * 根据 id 删除对应借阅记录, 还书
     *
     * @param id
     * @return
     */
    void removeBorrowById(String id);

    /**
     * 所有借阅记录
     *
     * @return
     */
    List<Borrow> AllBorrowList();
}
