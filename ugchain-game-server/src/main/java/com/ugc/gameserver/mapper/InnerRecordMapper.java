package com.ugc.gameserver.mapper;

import com.ugc.gameserver.domain.InnerRecord;
import org.apache.ibatis.annotations.*;

/**
 * Created by fanjl on 2017/4/6.
 */
@Mapper
public interface InnerRecordMapper {
    final String columns = "inner_record_id,from_address,to_address,amount,nonce,status";
    final String entity = "#{innerRecord.innerRecordId},#{innerRecord.fromAddress},#{innerRecord.toAddress}" +
            ",#{innerRecord.amount},#{innerRecord.nonce},#{innerRecord.status}";
    @Select("SELECT exists (select user_id FROM user_info WHERE identity = #{identity})")
    boolean isIdentityExists(String id);

    @Results(id="innerRecord",value={
            @Result(property = "innerRecordId", column = "inner_record_id"),
            @Result(property = "fromAddress", column = "from_address"),
            @Result(property = "toAddress", column = "to_address"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "nonce", column = "nonce"),
            @Result(property = "status", column = "status")
    })
    @Select("SELECT * FROM user_info WHERE user_id = #{userId}")
    InnerRecord findUserInfoByUserId(int userId);

    @Insert("INSERT INTO user_info("+columns+") VALUES("+entity+")")
    int insertUserAuth(@Param("userInfo") InnerRecord userInfo);

}

/*
内部转账表
CREATE TABLE `inner_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inner_record_id` int(11) NOT NULL ,
  `from_address` varchar(50) NOT NULL,
  `to_address` varchar(50) NOT NULL,
  `amount` decimal(10,5) NOT NULL,
  `nonce` int(11) NOT NULL,
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`),
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin
//转账前查询账户是否存在。查询amount是否足够。
//防止双花，转账前查询nonce和当前nonce是否一致，交易后nonce+1.
//交易时使用事物以及行级锁，transactional，for update 。
*/
