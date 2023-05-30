package per.iys.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.library.domain.Inventory;
import per.iys.library.mapper.InventoryMapper;
import per.iys.library.service.InventoryService;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryMapper inventoryMapper;

    @Autowired
    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public Inventory getInventoryByBookId(String bookId) {
        return inventoryMapper.selectInventoryByBookId(bookId);
    }

}
