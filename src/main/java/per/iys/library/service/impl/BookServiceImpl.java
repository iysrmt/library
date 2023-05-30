package per.iys.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.library.domain.Book;
import per.iys.library.domain.Inventory;
import per.iys.library.mapper.BookMapper;
import per.iys.library.mapper.InventoryMapper;
import per.iys.library.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final InventoryMapper inventoryMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, InventoryMapper inventoryMapper) {
        this.bookMapper = bookMapper;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public List<Book> queryBookListByConditionForPage(Book book, Integer pageNo, Integer pageSize) {
        Integer beginNo = (pageNo - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("book", book);
        map.put("beginNo", beginNo);
        map.put("pageSize", pageSize);
        return bookMapper.selectBookListByConditionForPage(map);
    }

    @Override
    public int getBookCountByConditionForPage(Book book) {
        return bookMapper.selectBookCountByConditionForPage(book);
    }

    @Transactional
    @Override
    public void saveBook(Book book, Inventory inventory) {
        bookMapper.insert(book);
        inventoryMapper.insert(inventory);
    }

    @Override
    public int getBookCountByIsbn(String isbn) {
        return bookMapper.selectBookCountByIsbn(isbn);
    }

    @Override
    public Book getBookById(String id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public void removeBookById(String id) {
        bookMapper.deleteByPrimaryKey(id);
        inventoryMapper.deleteInventoryByBookId(id);
    }

    @Override
    public Book getBookByIdForEdit(String id) {
        return bookMapper.selectBookByIdForEdit(id);
    }

    @Transactional
    @Override
    public void modifyBookById(Book book, Inventory inventory) {
        bookMapper.updateByPrimaryKeySelective(book);
        inventoryMapper.updateByPrimaryKeySelective(inventory);
    }
}
