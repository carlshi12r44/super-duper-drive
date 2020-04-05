package com.udacity.cloudstorage.mappers;

import com.udacity.cloudstorage.models.AppUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppUserMapper {

    @Select("SELECT * FROM USERS")
    List<AppUser> findAll();

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    public AppUser findOne(int userid);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public AppUser findByUsername(String username);

    @Insert("INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
    public int insertUser(AppUser appUser);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    public int deleteUser(String username);

    @Update("UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    public int updateUser(AppUser appUser);

}
