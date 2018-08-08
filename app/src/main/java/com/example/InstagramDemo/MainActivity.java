package com.example.InstagramDemo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.example.InstagramDemo.Data_Adapter.RecyclerViewAdapter;
import com.example.InstagramDemo.Data_Adapter.RecyclerViewAdapterStatus;
import com.example.InstagramDemo.Database_files.MyDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.InstagramDemo.Database_files.CRUDHelper.getAllRecords;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.camera_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCamera();
            }
        });
        //DATABASE
        MyDatabase Db = new MyDatabase(this);
        final SQLiteDatabase db = Db.getWritableDatabase();


        //FEED
        RecyclerView recyclerView = findViewById(R.id.recycler_feed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        RecyclerView.LayoutManager layoutManagerOfrecyclerView = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);
        RecyclerView.Adapter recyclerViewadapter = new RecyclerViewAdapter(this, getAllRecords(db));
        recyclerView.setAdapter(recyclerViewadapter);

        // STATUS
        RecyclerView recyclerViewStatus = findViewById(R.id.recycler_status);
        recyclerViewStatus.setHasFixedSize(true);
        recyclerViewStatus.setItemViewCacheSize(20);
        recyclerViewStatus.setDrawingCacheEnabled(true);
        recyclerViewStatus.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        RecyclerView.LayoutManager layoutManagerOfrecyclerViewStatus = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL
                , false);
        recyclerViewStatus.setLayoutManager(layoutManagerOfrecyclerViewStatus);
        RecyclerView.Adapter recyclerViewadapterSatus = new RecyclerViewAdapterStatus(this, getAllRecords(db));
        recyclerViewStatus.setAdapter(recyclerViewadapterSatus);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.you.name", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    private void goToCamera() {
        startActivity(new Intent(this, feedInputActivity.class));
    }


}
