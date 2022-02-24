package com.hakan.arayici.readingisgood.data.dao;

import com.hakan.arayici.readingisgood.data.model.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity,String> {

    @Query(value = "SELECT * , :customerNo as customer_no,\n" +
            "(select COUNT(*)  from CUSTOMER_ORDER OC, CUSTOMER CS WHERE OC.CUSTOMER_ID = CS.ID AND MONTH(OC.CREATE_DATE) = MONTH AND OC.CUSTOMER_ID = :customerNo ) AS ORDER_COUNT \n" +
            "FROM(\n" +
            "select MONTH(CREATE_DATE) as MONTH,\n" +
            "SUM(QUANTITY) AS TOTAL_BOOK_COUNT,SUM(QUANTITY * B.PRICE) AS TOTAL_PRICE  from CUSTOMER C,CUSTOMER_ORDER O, ORDER_BOOK OB, BOOK B\n" +
            "WHERE C.ID = O.CUSTOMER_ID AND O.ID = OB.ORDER_ID AND OB.BOOK_ID = B.ID and c.customer_no = :customerNo\n" +
            "GROUP BY MONTH(CREATE_DATE)\n" +
            ")", nativeQuery = true)
    List<StatisticsEntity> getStatisticsByCustomerNo(@Param("customerNo") String customerNo);

}
