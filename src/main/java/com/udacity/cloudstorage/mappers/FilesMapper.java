package com.udacity.cloudstorage.mappers;

import com.udacity.cloudstorage.models.Files;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FilesMapper {

  @Select("SELECT * FROM FILES")
  List<Files> findAll();

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
  Files findOne(int fileid);

  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<Files> findByUserId(int userid);

  @Insert(
      "INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
  int insertFile(Files file, int userid);

  @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
  int deleteFile(int fileid);

}
