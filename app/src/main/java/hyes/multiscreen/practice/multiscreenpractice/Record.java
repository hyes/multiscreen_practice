package hyes.multiscreen.practice.multiscreenpractice;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by hyes on 2015. 9. 7..
 */
public class Record extends ActionBarActivity {

    MenuFragment menuFragment;
    android.support.v4.app.FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.fragment_recording);

            MainListFragment mainListFragment = new MainListFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_list, mainListFragment);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            mainListFragment.setArguments(bundle);
            fragmentTransaction.commit();


        } else {

           // setContentView(R.layout.custom_record_main_list);
            setContentView(R.layout.temp);
            MainListFragment2 mainListFragment = new MainListFragment2();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.temp, mainListFragment);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            mainListFragment.setArguments(bundle);
            fragmentTransaction.commit();


            //Button step1 = (Button) findViewById(R.id.step1);

           // step1.setOnClickListener(this);
          //  Button record_btn = (Button) findViewById(R.id.record_btn);

//            step1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "RECORD", Toast.LENGTH_SHORT).show();
////                    RecordingFragment recordingFragment = new RecordingFragment();
////
////                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////
////                    fragmentTransaction.add(R.id.container_record, recordingFragment);
////
////                    fragmentTransaction.commit();
//
//
//                }
//            });



        }
    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.step1 :
//                RecordingFragment recordingFragment = new RecordingFragment();
//                 //   if(recordingFragment != null && recordingFragment instanceof RecordingFragment) {
//                android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//                fragmentTransaction1.addToBackStack(null);
//                fragmentTransaction1.add(R.id.recordDataList, recordingFragment);
//                fragmentTransaction1.commit();
//
//        }
//    }


}
