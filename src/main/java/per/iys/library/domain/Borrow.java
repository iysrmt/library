package per.iys.library.domain;

public class Borrow {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_borrow.id
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_borrow.book_id
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    private String bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_borrow.user_id
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_borrow.create_time
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_borrow.deadline
     *
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    private String deadline;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_borrow.id
     *
     * @return the value of tbl_borrow.id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_borrow.id
     *
     * @param id the value for tbl_borrow.id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_borrow.book_id
     *
     * @return the value of tbl_borrow.book_id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_borrow.book_id
     *
     * @param bookId the value for tbl_borrow.book_id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_borrow.user_id
     *
     * @return the value of tbl_borrow.user_id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_borrow.user_id
     *
     * @param userId the value for tbl_borrow.user_id
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_borrow.create_time
     *
     * @return the value of tbl_borrow.create_time
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_borrow.create_time
     *
     * @param createTime the value for tbl_borrow.create_time
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_borrow.deadline
     *
     * @return the value of tbl_borrow.deadline
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_borrow.deadline
     *
     * @param deadline the value for tbl_borrow.deadline
     * @mbg.generated Fri Feb 24 15:22:19 CST 2023
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline == null ? null : deadline.trim();
    }
}