package hyes.multiscreen.practice.multiscreenpractice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class Intro extends ActionBarActivity {

    private static final String RECORDED_FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JOO/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFile();
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }

    private void setFile() {
        makeDirectory(RECORDED_FILEPATH);
        File nomedia = new File(RECORDED_FILEPATH+"/.nomedia");
        try {
            nomedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File makeDirectory(String dir_path){
        File dir = new File(dir_path);
        if (!dir.exists())
        {
            dir.mkdirs();
            Log.i("test", "!dir.exists");
        }else{
            Log.i("test", "dir.exists" );
        }

        return dir;
    }
}