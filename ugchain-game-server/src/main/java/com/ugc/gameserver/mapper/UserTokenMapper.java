package com.ugc.gameserver.mapper;

import com.ugc.gameserver.domain.UserToken;
import com.ugc.gameserver.mapper.util.ListTypeHandler;
import com.ugc.gameserver.mapper.util.SimpleSelectInExtendedLanguageDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanjl on 2017/4/5.
 */
@Mapper
public interface UserTokenMapper {
	final String columns = "user_token_id,user_name,token,data,derma,create_time,update_time,status";
    final String entity = "#{userToken.userTokenId},#{userToken.userName},#{userToken.token},#{userToken.data}" +
            ",#{userToken.derma,typeHandler=com.ugc.gameserver.mapper.util.ListTypeHandler},#{userToken.createTime},#{userToken.updateTime},#{userToken.status}";

	

    @Insert("INSERT INTO user_token("+columns+") VALUES("+entity+")")
	void insertUserToken(@Param("userToken") UserToken userToken);

	@Results(id="userToken",value={
			@Result(property = "userTokenId", column = "user_token_id"),
			@Result(property = "token", column = "token"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "derma", column = "derma",typeHandler = ListTypeHandler.class,jdbcType = JdbcType.VARCHAR,javaType = ArrayList.class),
			@Result(property = "data", column = "data"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updateTime", column = "update_time"),
			@Result(property = "status", column = "status")
	})
	@Select("SELECT * FROM user_token WHERE user_token_id = #{userTokenId}")
	UserToken getUserTokenById(int userTokenId);

	@Select("SELECT * FROM user_token WHERE token = #{token}")
	@ResultMap("userToken")
	UserToken getUserTokenByToken(String token);

	@Select("SELECT * FROM user_token WHERE status in (#{status})")
	@ResultMap("userToken")
	@Lang(SimpleSelectInExtendedLanguageDriver.class)
	List<UserToken> getUserTokenList(@Param("status") List<Integer> status);

	@Select("SELECT exists(SELECT data FROM user_token WHERE token = #{token})")
	boolean isExistsUserToken(String token);
	
	@Update("update user_token set derma=#{derma,typeHandler=com.ugc.gameserver.mapper.util.ListTypeHandler} where token=#{token}")
	void updateDerma(@Param("derma") List<String> derma,@Param("token")String token);

	@Update("update user_token set data=#{data} where token=#{token}")
	void updateData(@Param("data")int data,@Param("token")String token);

	@Update("update user_token set status=#{status} where token=#{token}")
	void updateStatus(@Param("status")int status,@Param("token")String token);

	@Update("update user_token set status=#{status} , prices = #{prices} where token=#{token}")
	void updateStatusAndPrices(@Param("status")int status, @Param("prices")BigDecimal prices, @Param("token")String token);

	@Update("update user_token set  prices = #{prices} where token=#{token}")
	void updatePrices( @Param("prices")BigDecimal prices, @Param("token")String token);

}
/*

CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_token_id` int(11) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  `token` varchar(50) NOT NULL,
  `data` varchar(50) NOT NULL,
  `derma` int(11) NOT NULL default '0',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin

CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_token_id` int(11) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  `token` varchar(50) NOT NULL,
  `data` varchar(50) NOT NULL,
  `derma` int(11) NOT NULL default '0',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL default '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE = utf8_bin

*/
