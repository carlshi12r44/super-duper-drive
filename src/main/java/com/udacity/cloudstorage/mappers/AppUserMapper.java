package com.udacity.cloudstorage.mappers;

import com.udacity.cloudstorage.models.AppUser;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppUserMapper {

  @Select("SELECT * FROM USERS")
  List<AppUser> findAll();

  @Select("SELECT * FROM USERS WHERE userid = #{userid}")
  AppUser findOne(int userid);

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  AppUser findByUsername(String username);

  @Insert(
      "INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
  int insertUser(AppUser appUser);

  @Delete("DELETE FROM USERS WHERE username = #{username}")
  int deleteUser(String username);

  @Update(
      "UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
  int updateUser(AppUser appUser);

}
