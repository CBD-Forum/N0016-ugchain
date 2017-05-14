package com.ugc.gameserver.mapper;

import com.ugc.gameserver.domain.BlockRecord;
import org.apache.ibatis.annotations.*;

/**
 * Created by fanjl on 2017/4/6.
 */
@Mapper
public interface BlockRecordMapper {
    final String columns = "block_record_id,transaction_id,target_address,amount,fee,nonce,type,status";
    final String entity = "#{blockRecord.blockRecordId},#{blockRecord.transactionId},#{blockRecord.targetAddress}" +
            ",#{blockRecord.amount},#{blockRecord.fee},#{blockRecord.nonce},#{blockRecord.type},#{blockRecord.status}";


    @Results(id="blockRecord",value={
            @Result(property = "blockRecordId", column = "block_record_id"),
            @Result(property = "fromAddress", column = "from_address"),
            @Result(property = "toAddress", column = "to_address"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "nonce", column = "nonce"),
            @Result(property = "status", column = "status")
    })
    @Select("SELECT * FROM block_record WHERE block_record_id = #{blockRecordId}")
    BlockRecord getBlockRecordById(int blockRecordId);

    @Insert("INSERT INTO block_record("+columns+") VALUES("+entity+")")
    int insertBlockRecord(@Param("blockRecord") BlockRecord blockRecord);
}

/*
充值提现表
CREATE TABLE `block_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `block_record_id` int(11) NOT NULL ,
  `transaction_id` varchar(50)  ,
  `target_address` varchar(50) NOT NULL,
  `amount` bigint(10) NOT NULL,
  `fee` decimal(10,5) NOT NULL,
  `nonce` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin
//使用transactional 和 for update 保证并发情况下nonce的一致性。充值以及提现必须先从accout查询nonce和amount
//充值或者提现后异步从区块链查询交易信息，能查到后更新transaction_id,status.
//fee：手续费
//status: 0处理中，1成功，2失败。
//type: 0充值 ， 1提现

*/
