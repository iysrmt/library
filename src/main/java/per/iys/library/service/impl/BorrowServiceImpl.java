package per.iys.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.library.domain.Borrow;
import per.iys.library.domain.Inventory;
import per.iys.library.mapper.BorrowMapper;
import per.iys.library.mapper.InventoryMapper;
import per.iys.library.service.BorrowService;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowMapper borrowMapper;
    private final InventoryMapper inventoryMapper;

    @Autowired
    public BorrowServiceImpl(BorrowMapper borrowMapper, InventoryMapper inventoryMapper) {
        this.borrowMapper = borrowMapper;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional
    @Override
    public boolean saveBorrow(Borrow borrow) {
        String bookId = borrow.getBookId();

        // 根据图书id 找到对应的库存
        Inventory inventory = inventoryMapper.selectInventoryByBookId(bookId);

        Integer count = Integer.valueOf(inventory.getCount());

        // 库存为 0 时不可借阅
        if (count <= 0) {
            return false;
        }

        // 库存 -1
        count--;
        inventory.setCount(String.valueOf(count));

        // 更新库存
        inventoryMapper.updateByPrimaryKeySelective(inventory);

        // 增加借阅记录
        borrowMapper.insertSelective(borrow);

        return true;
    }

    @Override
    public List<Borrow> queryBorrowListByUserId(String userId) {
        return borrowMapper.selectBorrowListByUserId(userId);
    }

    @Override
    public Borrow getBorrowById(String id) {
        return borrowMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void removeBorrowById(String id) {
        // 通过借阅id获取借阅记录
        Borrow borrow = borrowMapper.selectByPrimaryKey(id);
        // 借阅记录中获取图书id
        String bookId = borrow.getBookId();

        // 通过图书id查库存
        Inventory inventory = inventoryMapper.selectInventoryByBookId(bookId);

        // 库存 +1
        Integer count = Integer.valueOf(inventory.getCount());
        count++;
        inventory.setCount(String.valueOf(count));
        inventoryMapper.updateByPrimaryKeySelective(inventory);

        // 删除借阅记录
        borrowMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Borrow> AllBorrowList() {
        return borrowMapper.selectBorrowList();
    }
}
