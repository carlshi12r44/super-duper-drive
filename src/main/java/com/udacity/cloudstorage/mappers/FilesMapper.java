package com.udacity.cloudstorage.mappers;

import com.udacity.cloudstorage.models.Files;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES")
    List<Files> findAll();

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    public Files findOne(int fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List<Files> findByUserId(int userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    public int insertFile(Files file, int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    public int deleteFile(int fileid);

    @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contenttype}, filesize = #{filesize}, filedata = #{filedata} WHERE fileid = #{fileid}")
    public int updateFile(Files file);

}
