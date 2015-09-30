package hyes.multiscreen.practice.multiscreenpractice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import hyes.multiscreen.practice.multiscreenpractice.Model.RecordItem;


public class MainListFragment extends Fragment implements View.OnClickListener{

    private String name;
    private int id;
    private TextView name_tv;
    private Button submit_btn;
    private RecordItem record, data;
    private static final int REVISECODE1 = 1;
    private static final int REVISECODE2 = 2;
    private static final int REVISECODE3 = 3;
    private static final int REVISECODE4 = 4;
    private static final int REVISECODE5 = 5;
    private static final int REVISECODE6 = 6;
    public ImageView pic1, pic2, pic3, pic4, pic5, pic6;
    private Button play1, play2, play3, play4, play5, play6;
    public String[] filePaths;

    private MediaPlayer player;
    private boolean p1, p2, p3, p4, p5, p6;

    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;

    private ProgressDialog progressDialog;
    private AlertDialog alert;

    private Spinner spinner, spinner2;
    private String[] positions = {"Optional", "Mitral valve", "Triscuspid valve", "Pulmonic valve", "Aortic valve"};
    private String selected_position, selected_position2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        name = bundle.getString("name");

        return inflater.inflate(R.layout.custom_record_main_list, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView name_tv = (TextView) getActivity().findViewById(R.id.name);
        name_tv.setText(name);
        pic1 = (ImageView) getActivity().findViewById(R.id.cap1);
        pic2 = (ImageView) getActivity().findViewById(R.id.cap2);
        Button step1 = (Button) getActivity().findViewById(R.id.step1);
        step1.setOnClickListener(this);
//        pic3 = (ImageView) findViewById(R.id.cap3);
//        pic4 = (ImageView) findViewById(R.id.cap4);
//        pic5 = (ImageView) findViewById(R.id.cap5);
//        pic6 = (ImageView) findViewById(R.id.cap6);
//
//        play1 = (Button) findViewById(R.id.audio1);
//        play2 = (Button) findViewById(R.id.audio2);
//        play3 = (Button) findViewById(R.id.audio3);
//        play4 = (Button) findViewById(R.id.audio4);
//        play5 = (Button) findViewById(R.id.audio5);
//        play6 = (Button) findViewById(R.id.audio6);

        //name_tv.setText(name);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.step1:

                Toast.makeText(getActivity().getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
                RecordingFragment recordingFragment = new RecordingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.main_record, recordingFragment);
                fragmentTransaction.commit();

                break;
//            case R.id.record_btn:
//                TargetFragment targetFragment = new TargetFragment();
//                FragmentManager fragmentManager1 = getFragmentManager();
//                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//                fragmentTransaction1.add(R.id.right, targetFragment);
//                fragmentTransaction1.commit();
                //break;
        }


    }
}
