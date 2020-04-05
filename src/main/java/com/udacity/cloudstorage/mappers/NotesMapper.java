package com.udacity.cloudstorage.mappers;

import com.udacity.cloudstorage.models.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES")
    List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    public Notes findOne(int noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<Notes> findByUserId(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    public int insertNote(Notes note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public int deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    public int updateNote(Notes note);

}
