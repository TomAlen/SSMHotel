package com.zwhzzz.Service;

import com.zwhzzz.DTO.BookOrderDTO;
import com.zwhzzz.Mapper.BookOrderDao;
import com.zwhzzz.Pojo.BookOrder;
import com.zwhzzz.Pojo.BookOrderExample;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Service
public class BookOrderService {

    private final BookOrderDao bookOrderDao;

    public BookOrderService(BookOrderDao bookOrderDao) {
        this.bookOrderDao = bookOrderDao;
    }


    public List<BookOrder> getBookOrderList(BookOrderDTO bookOrderDTO) {
        return bookOrderDao.getBookOrderList(bookOrderDTO);
    }

    public List<BookOrder> getList(Map<String,Object> queryMap) {
        return bookOrderDao.getList(queryMap);
    }

    public int insertBookOrder(BookOrder bookOrder) {
        return bookOrderDao.insert(bookOrder);
    }

    public BookOrder findById(Integer id) {
        BookOrderExample bookOrderExample = new BookOrderExample();
        bookOrderExample.createCriteria()
                .andIdEqualTo(id);
        //时间倒叙
        bookOrderExample.setOrderByClause("createTime desc");
        List<BookOrder> bookOrders = bookOrderDao.selectByExample(bookOrderExample);
        if(bookOrders.size() > 0) {
            return bookOrders.get(0);
        }
        return null;
    }

    public int updateBookOrder(BookOrder bookOrder) {
        return bookOrderDao.updateByPrimaryKeySelective(bookOrder);
    }

    public List<BookOrder> selectByAccountId(Integer id) {

        BookOrderExample example = new BookOrderExample();
        example.createCriteria()
                .andAccountidEqualTo(id);
        example.setOrderByClause("createTime desc");
        List<BookOrder> bookOrders = bookOrderDao.selectByExample(example);
        return bookOrders;
    }

    public int deleteBookOrder(Integer bookOrderId) {
        return bookOrderDao.deleteByPrimaryKey(bookOrderId);
    }
}
