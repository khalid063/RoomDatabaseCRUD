package com.example.roomdatabasesimplecrud.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    public void studentInsertation(StudentInformation studentInformation);

    @Query(" Select * from StudentInformation ")
    List<StudentInformation> getStudentData();

    //Query("Update TableName set TableVariableName = :NewVariableName
    // where giveIdWhichYouWantChangeWhichDeclearedIntoTableClass = :NewIdVariable")
    @Query("Update StudentInformation set name = :sName where id = :sId")
    void updateSInfo(String sName,int sId);

    @Query("Delete from StudentInformation where id = :sInfoDeleteId")
    void deleteSInfo(int sInfoDeleteId);

}
