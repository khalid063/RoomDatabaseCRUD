package com.example.roomdatabasesimplecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabasesimplecrud.Room.MyDatabase;
import com.example.roomdatabasesimplecrud.Room.StudentInformation;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etAge,etUpdateName,etUpdateId,etDeleteId;
    TextView tvShowData;
    Button butInsurt,butShowData,butUpdate,butDelete;

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database Create
        myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class,"StudentDB")
                .allowMainThreadQueries().build();

        //-------------------- find view by Id ----------------------------//
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdateId = findViewById(R.id.etUpdateId);
        etDeleteId = findViewById(R.id.etDeleteId);

        tvShowData = findViewById(R.id.tvShowData);

        butInsurt = findViewById(R.id.butInsert);
        butShowData = findViewById(R.id.butShowData);
        butUpdate = findViewById(R.id.butUpdate);
        butDelete = findViewById(R.id.butDelete);


        // set Listener
        butInsurt.setOnClickListener(this);
        butShowData.setOnClickListener(this);
        butUpdate.setOnClickListener(this);
        butDelete.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        int id = v.getId();




        switch (id){
            case R.id.butInsert:
                String sName = etName.getText().toString();
                int sAge = Integer.parseInt(etAge.getText().toString());

                etName.setText("");
                etAge.setText("");

                //Creat table class object
                StudentInformation studentInformation = new StudentInformation(sName,sAge);
                myDatabase.dao().studentInsertation(studentInformation);
                Log.d("test","Name : "+ StudentInformation.class.getName());
                break;

            case R.id.butShowData:
                //Toast.makeText(this, "Read but is clicked", Toast.LENGTH_SHORT).show();
                List<StudentInformation> sInfo = myDatabase.dao().getStudentData();
                for (int i = 0; i < sInfo.size(); i++){
                    Log.d("Student_data",sInfo.get(i).getId()+": "+sInfo.get(i).getName()+" : "+ sInfo.get(i).getAge());
                }

                StringBuffer stringBuffer = new StringBuffer();
                //use foreach loop or inhenched for loop
                for(StudentInformation a: sInfo){
                    tvShowData.setText(stringBuffer.append("ID : "+a.getId()+"\n"));
                    tvShowData.setText(stringBuffer.append("Name : "+a.getName()+"\n"));
                    tvShowData.setText(stringBuffer.append("Age : "+a.getAge()+"\n"+"\n"+"\n"));
                }
                // use for loop for showing data
//                for (int i = 0; i < sInfo.size(); i++){
//                    tvShowData.setText(stringBuffer.append("ID : "+sInfo.get(i).getId()+"\n"));
//                    tvShowData.setText(stringBuffer.append("Name : "+sInfo.get(i).getName()+"\n"));
//                    tvShowData.setText(stringBuffer.append("Age : "+sInfo.get(i).getAge()+"\n"+"\n"+"\n"));
//                }
                break;

            case R.id.butUpdate:
                Toast.makeText(this, "update but is clicked", Toast.LENGTH_SHORT).show();
                String updateName = etUpdateName.getText().toString();
                int updateId = Integer.parseInt(etUpdateId.getText().toString());

                etUpdateName.setText("");
                etUpdateId.setText("");

                myDatabase.dao().updateSInfo(updateName,updateId);
                break;

            case R.id.butDelete:
                Toast.makeText(this, "Delete but is clicked", Toast.LENGTH_SHORT).show();
                int deleteId = Integer.parseInt(etDeleteId.getText().toString());

                myDatabase.dao().deleteSInfo(deleteId);

                //For Runtime update of Show data after delete
                List<StudentInformation> sInfo2 = myDatabase.dao().getStudentData();
                StringBuffer stringBuffer2 = new StringBuffer();
                for(StudentInformation a: sInfo2){
                    tvShowData.setText(stringBuffer2.append("ID : "+a.getId()+"\n"));
                    tvShowData.setText(stringBuffer2.append("Name : "+a.getName()+"\n"));
                    tvShowData.setText(stringBuffer2.append("Age : "+a.getAge()+"\n"+"\n"+"\n"));
                }

                break;


        }

    }
}