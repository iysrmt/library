package per.iys.library.service;

import per.iys.library.domain.Inventory;

public interface InventoryService {

    /**
     * 通过图书 id 获取库存
     */
    Inventory getInventoryByBookId(String bookId);

}
