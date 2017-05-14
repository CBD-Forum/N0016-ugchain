package com.ugc.gameserver.mapper;

import com.ugc.gameserver.domain.DermaOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by fanjl on 2017/4/6.
 */
@Mapper
public interface DermaOrderMapper {
    final String columns = "order_id,token,seller,game_id,derma_id,derma_name,derma_prices,status";
    final String entity = "#{dermaOrder.orderId},#{dermaOrder.token},#{dermaOrder.seller},#{dermaOrder.gameId},#{dermaOrder.derma.id},#{dermaOrder.derma.name}" +
            ",#{dermaOrder.derma.prices},#{dermaOrder.status}";

    @Results(id="dermaOrder",value={
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "token", column = "token"),
            @Result(property = "seller", column = "seller"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "derma.id", column = "derma_id"),
            @Result(property = "derma.name", column = "derma_name"),
            @Result(property = "derma.prices", column = "derma_prices"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "status", column = "status")
    })
    @Select("SELECT * FROM derma_order WHERE token = #{token}")
    List<DermaOrder> getOrderByToken(String token);

    @ResultMap("dermaOrder")
    @Select("SELECT * FROM derma_order WHERE order_id = #{orderId}")
    DermaOrder getOrderById(@Param("orderId")int orderId);

    @Insert("INSERT INTO derma_order("+columns+") VALUES("+entity+")")
    int insertDermaOrder(@Param("dermaOrder") DermaOrder order);

    @Update("update derma_order set status=#{status} where order_id=#{orderId}")
    int updateDermaOrder(@Param("status") int status,@Param("orderId") int orderId);

}

/*
CREATE TABLE `derma_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL ,
  `token` varchar(50) NOT NULL,
  `seller` varchar(50) NOT NULL,
  `game_id` int(11) NOT NULL default '0',
  `derma_id` int(11) NOT NULL default '0',
  `derma_name` varchar(50) NOT NULL,
  `derma_prices` decimal(10,5) NOT NULL,
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin

CREATE TABLE `derma_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL ,
  `token` varchar(50) NOT NULL,
  `seller` varchar(50) NOT NULL,
  `game_id` int(11) NOT NULL default '0',
  `derma_id` int(11) NOT NULL default '0',
  `derma_name` varchar(50) NOT NULL,
  `derma_prices` decimal(10,5) NOT NULL,
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP ,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin
*/
