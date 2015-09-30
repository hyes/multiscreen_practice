package hyes.multiscreen.practice.multiscreenpractice;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import hyes.multiscreen.practice.multiscreenpractice.VisualizerCustom.VisualizerView;
import hyes.multiscreen.practice.multiscreenpractice.VisualizerCustom.VisualizerView2;


public class RecordingFragment2 extends Fragment implements View.OnClickListener{

    private static final float VISUALIZER_HEIGHT_DIP = 50f;
    private static final String TAG = "AudioFxDemo";

    private static final String RECORDED_FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BonCoeur/";

    //final private static String RECORDED_FILEPATH = "/storage/emulated/0/";
    MediaPlayer player;
    MediaRecorder recorder;
    private String fileName = null;
    private Uri outUri,realUri;
    private String filename, filePath;
    private SimpleDateFormat timestamp, timestampDate;
    private Camera camera = null;
    private ImageView pic_frame;
    private int idx, id;
    private LinearLayout visualizer;
    private String name;
    private String outUriStr;


    //audiofxview
    private VisualizerView mVisualizerView;
    private VisualizerView2 mVisualizerView2;
    private Visualizer mVisualizer;

    private Handler mHandler;
    private Runnable mRunnable;

    private int[] waveform = new int[ 1024 ];
    private byte[] prev_chunk;

    private String selected_position;
  //  private RecordDialog recordDialog;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.record_main, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Button save_button = (Button) getActivity().findViewById(R.id.save);
        Button cancel_button = (Button) getActivity().findViewById(R.id.cancel_btn);
        ImageButton record_btn = (ImageButton) getActivity().findViewById(R.id.record);
        ImageButton play_btn = (ImageButton) getActivity().findViewById(R.id.play);
        ImageButton stop_btn = (ImageButton) getActivity().findViewById(R.id.stop);

        record_btn.setOnClickListener(this);
        play_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        save_button.setOnClickListener(this);
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
            case R.id.record:

                Toast.makeText(getActivity(), "recording~~", Toast.LENGTH_SHORT).show();
//                RecordingFragment recordingFragment = new RecordingFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.main_record, recordingFragment);
//                fragmentTransaction.commit();

                break;
            case R.id.play:
                Toast.makeText(getActivity(), "play~~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:

                Toast.makeText(getActivity(), "save~~", Toast.LENGTH_SHORT).show();
               // getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();


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
