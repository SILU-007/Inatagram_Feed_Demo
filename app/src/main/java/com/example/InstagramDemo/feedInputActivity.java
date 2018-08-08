package com.example.InstagramDemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.InstagramDemo.Data_gettter_setters.feedData;
import com.example.InstagramDemo.Database_files.MyDatabase;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.InstagramDemo.Database_files.CRUDHelper.insertDataToDatabase;


public class feedInputActivity extends AppCompatActivity {
    private EditText desc;
    private Uri file;
    private ImageView showImage;
    private TextView postFeed;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_input);
        showImage = findViewById(R.id.postImage);
        desc=findViewById(R.id.postDesc);
       postFeed=findViewById(R.id.postButton);
       backArrow=findViewById(R.id.backButton);
       backArrow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               goHome();
           }
       });
        postFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPostData();
            }
        });
    }

   private void addPostData()
    {
        String imagePath= file.toString();
        String stringdesc = desc.getText().toString();
        if(imagePath.length()==0&&stringdesc.length()==0)
        {return;}

        Toast.makeText(this, imagePath, Toast.LENGTH_SHORT).show();
        MyDatabase Db=new MyDatabase(this);
        SQLiteDatabase db = Db.getWritableDatabase();

        ArrayList<feedData> arrayList=new ArrayList<feedData>();
        feedData f=new feedData(this ,stringdesc,imagePath);
        arrayList.add(f);
        insertDataToDatabase(db,arrayList);
    goHome();
    }

    ///////////////////---------------------- CAMERA --------------------------------////////////////////////////////

    public void takePicture(View view) {
//        Intent localIntent1 = new Intent(Intent.ACTION_GET_CONTENT);
//        localIntent1.setType("image/jpeg");
//        localIntent1.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//        startActivityForResult(Intent.createChooser(localIntent1, "UPLOAD PICTURE"), 100);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "InstaDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("InstaDemo", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Glide.with(this).load(file).into(showImage); }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void goHome()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}

