package per.iys.library.service;

import per.iys.library.domain.Inventory;

public interface InventoryService {

    /**
     * 通过图书 id 获取库存
     *
     * @param bookId
     * @return
     */
    Inventory getInventoryByBookId(String bookId);

}
