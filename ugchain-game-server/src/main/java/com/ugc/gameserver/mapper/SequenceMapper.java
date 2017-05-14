package com.ugc.gameserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by yuanshichao on 2016/11/14.
 */
@Mapper
public interface SequenceMapper {

    String INNER_RECORD = "INNER_RECORD";

    String BLOCK_RECORD = "BLOCK_RECORD";

    String ACCOUNT = "ACCOUNT";

    String USER_TOKEN = "USER_TOKEN";

    String DERMA_ORDER = "DERMA_ORDER";



    @Select("SELECT nextval('${table}')")
    int nextId(@Param("table")String table);


}

/*

CREATE TABLE `sys_sequence` (
   `NAME` varchar(20) NOT NULL,
   `CURRENT_VALUE` int(11) NOT NULL DEFAULT '0',
   `INCREMENT` int(11) NOT NULL DEFAULT '1',
   PRIMARY KEY (`NAME`)
) DEFAULT CHARSET=latin1;

INSERT INTO sys_sequence(NAME,CURRENT_VALUE,INCREMENT) VALUES('ACCOUNT', 1,1)
INSERT INTO sys_sequence(NAME,CURRENT_VALUE,INCREMENT) VALUES('BLOCK_RECORD', 1,1)
INSERT INTO sys_sequence(NAME,CURRENT_VALUE,INCREMENT) VALUES('INNER_RECORD', 1,1)
INSERT INTO sys_sequence(NAME,CURRENT_VALUE,INCREMENT) VALUES('USER_TOKEN', 1,1)
INSERT INTO sys_sequence(NAME,CURRENT_VALUE,INCREMENT) VALUES('DERMA_ORDER', 1,1)


DELIMITER $$

DROP FUNCTION IF EXISTS `currval`$$

CREATE DEFINER=`blockchain`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS INT(11)
BEGIN
DECLARE VALUE INTEGER;
SET VALUE=0;
SELECT current_value INTO VALUE
FROM sys_sequence
WHERE NAME=seq_name;
RETURN VALUE;
END$$

DELIMITER ;


DELIMITER $$
DROP FUNCTION IF EXISTS `nextval`$$

CREATE DEFINER=`blockchain`@`%` FUNCTION `nextval`(seq_name varchar(50)) RETURNS int(11)
BEGIN
UPDATE sys_sequence
SET CURRENT_VALUE = CURRENT_VALUE + INCREMENT
where name=seq_name;
return currval(seq_name);
END$$

DELIMITER ;
 */