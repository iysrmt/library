package per.iys.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.library.domain.Borrow;

import java.util.List;

@Mapper
public interface BorrowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int insert(Borrow row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int insertSelective(Borrow row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    Borrow selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int updateByPrimaryKeySelective(Borrow row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_borrow
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    int updateByPrimaryKey(Borrow row);

    /**
     * 根据用户 id 查找借问记录, 连接查找 用于展示
     *
     * @param userId
     * @return
     */
    List<Borrow> selectBorrowListByUserId(String userId);

    /**
     * 所有借阅记录
     *
     * @return
     */
    List<Borrow> selectBorrowList();
}