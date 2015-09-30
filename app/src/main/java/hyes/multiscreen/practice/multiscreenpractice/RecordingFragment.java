package hyes.multiscreen.practice.multiscreenpractice;

import android.content.ContentValues;
import android.hardware.Camera;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import hyes.multiscreen.practice.multiscreenpractice.VisualizerCustom.VisualizerView;
import hyes.multiscreen.practice.multiscreenpractice.VisualizerCustom.VisualizerView2;


public class RecordingFragment extends Fragment implements View.OnClickListener{

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
    private RecordDialog recordDialog;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        timestamp = new SimpleDateFormat("yyyyMMddHHmmss");
        timestampDate = new SimpleDateFormat("yyyyMMdd");


        view = inflater.inflate(R.layout.record_main, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (recorder == null) {
            recorder = new MediaRecorder();
        }
        if(player == null){
            player = new MediaPlayer();
        }
        visualizer = (LinearLayout)getActivity().findViewById(R.id.visualizer_draw);
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



               // visualizer.removeView(mVisualizerView);

                recordDialog = new RecordDialog();
                recordDialog.show(getActivity().getFragmentManager(), "recording");

                fileName = name + "_" + timestamp.format(new Date()).toString() + "REC.mp4";
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILEPATH + fileName);
                Log.i("title test", RECORDED_FILEPATH + fileName);




                try {
                    recorder.prepare();
                    recorder.start();
//                    Toast.makeText(getApplicationContext(), "녹음 시작~", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("RECORDER TEST", "EXCEPTION ", e);
                }

                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        recorder.stop();
                        recorder.release();
//                        Toast.makeText(getApplicationContext(), "녹음 중지~", Toast.LENGTH_SHORT).show();
                        recorder = null;

                        ContentValues values = new ContentValues(10);

                        values.put(MediaStore.MediaColumns.TITLE, RECORDED_FILEPATH + fileName);
                        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
                        values.put(MediaStore.Audio.Media.DATA, RECORDED_FILEPATH + fileName);

                        Uri audioUri = getActivity().getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);

                        if (audioUri == null) {
                            Log.i("SampleAudioRecorder", "Audio insert failed");
                            return;
                        }
                    }
                };
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 30000);

                new Handler().postDelayed( new Runnable() {
                    public void run() {

                        try {

                            visualizer.removeView(mVisualizerView);

                            Log.i( "test", "decode start" );
                            MediaExtractor extractor = new MediaExtractor();
                            extractor.setDataSource( RECORDED_FILEPATH + fileName );

                            MediaFormat format = extractor.getTrackFormat( 0 );
                            String mime = format.getString(MediaFormat.KEY_MIME);

                            // the actual decoder
                            MediaCodec codec = MediaCodec.createDecoderByType(mime);
                            codec.configure(format, null /* surface */, null /* crypto */, 0 /* flags */);
                            codec.start();
                            ByteBuffer[] codecInputBuffers = codec.getInputBuffers();
                            ByteBuffer[] codecOutputBuffers = codec.getOutputBuffers();

                            // get the sample rate to configure AudioTrack
                            int sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);
                            long mediaLength = format.getLong( MediaFormat.KEY_DURATION );
                            int mediaLength2 = ( int )( mediaLength / 1000000.0 * sampleRate );
                            int spacing = mediaLength2 / 1024 + 1;

                            extractor.selectTrack(0);

                            // start decoding
                            final long kTimeOutUs = 10000;
                            MediaCodec.BufferInfo BufInfo = new MediaCodec.BufferInfo();
                            boolean sawInputEOS = false;
                            boolean sawOutputEOS = false;

                            int inputBufIndex;

                            int counter=0, nread = 0, waveform_counter = 0, lastwaveform_pos = -spacing;
                            while (!sawOutputEOS) {


                                counter++;
                                if (!sawInputEOS) {
                                    inputBufIndex = codec.dequeueInputBuffer(kTimeOutUs);
                                    // Log.d(LOG_TAG, " bufIndexCheck " + bufIndexCheck);
                                    if (inputBufIndex >= 0) {
                                        ByteBuffer dstBuf = codecInputBuffers[inputBufIndex];

                                        int sampleSize = extractor
                                                .readSampleData(dstBuf, 0 /* offset */);

                                        long presentationTimeUs = 0;

                                        if (sampleSize < 0) {

                                            sawInputEOS = true;
                                            sampleSize = 0;
                                        } else {

                                            presentationTimeUs = extractor.getSampleTime();
                                        }
                                        // can throw illegal state exception (???)

                                        codec.queueInputBuffer(inputBufIndex, 0 /* offset */,
                                                sampleSize, presentationTimeUs,
                                                sawInputEOS ? MediaCodec.BUFFER_FLAG_END_OF_STREAM
                                                        : 0);

                                        if (!sawInputEOS) {
                                            extractor.advance();
                                        }
                                    } else {
                                        Log.e("sohail", "inputBufIndex " + inputBufIndex);
                                    }
                                }

                                int res = codec.dequeueOutputBuffer(BufInfo, kTimeOutUs);

                                if (res >= 0) {
//                                    Log.i("sohail","decoding: deqOutputBuffer >=0, counter="+counter);
                                    // Log.d(LOG_TAG, "got frame, size " + info.size + "/" +
                                    // info.presentationTimeUs);
                                    if (BufInfo.size > 0) {
                                        // noOutputCounter = 0;
                                    }

                                    int outputBufIndex = res;
                                    ByteBuffer buf = codecOutputBuffers[outputBufIndex];

                                    final byte[] chunk = new byte[BufInfo.size];
                                    buf.get(chunk);
                                    buf.clear();
//                                    byte[] long_chunk = new byte[ chunk.length + prev_chunk.length ];

                                    if (chunk.length > 0) {
//                                        Log.i( "test", "len : " + chunk.length );
                                        int tmp[] = new int[ ( int )Math.ceil( chunk.length / 2.0 ) ];
//                                        Log.i( "test", "sam : " + chunk[ 0 ] + ", " + chunk[ 1 ] + " -> " + ( chunk[ 1 ] << 8 | ( chunk[ 0 ] & 0xFF ) ) );
                                        for( int aa = 0; aa < chunk.length / 2; aa++ ){

                                            tmp[ aa ] = ( int )( chunk[ aa * 2 + 1 ] << 8 | ( chunk[ aa * 2 ] & 0xFF ) );
                                        }
                                        while( nread + chunk.length / 2 > lastwaveform_pos + spacing ){

//                                            int v = ( int )( chunk[ ( lastwaveform_pos - nread + spacing ) * 2 + 1 ] << 8 | ( chunk[ ( lastwaveform_pos - nread + spacing ) * 2 ] & 0xFF ) );
//                                            waveform[ waveform_counter++ ] = v;
                                            waveform[ waveform_counter++ ] = tmp[ lastwaveform_pos - nread + spacing ];
                                            lastwaveform_pos += spacing;
                                        }
                                        nread += chunk.length / 2;
                                    }
                                    prev_chunk = chunk;
                                    codec.releaseOutputBuffer(outputBufIndex, false /* render */);
                                    if ((BufInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                                        Log.i("sohail", "saw output EOS.");
                                        sawOutputEOS = true;
                                    }
                                } else if (res == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                                    codecOutputBuffers = codec.getOutputBuffers();

                                    Log.i("sohail", "output buffers have changed.");
                                } else if (res == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                                    MediaFormat oformat = codec.getOutputFormat();

                                    Log.i("sohail", "output format has changed to " + oformat);
                                } else {
                                    Log.i("sohail", "dequeueOutputBuffer returned " + res);
                                }
                            }

                            codec.stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.i( "test", "decode end" );

//                        mVisualizerView2 = new VisualizerView2(getActivity());
//                        mVisualizerView2.setLayoutParams(new ViewGroup.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                (int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)));
//                        visualizer.addView(mVisualizerView2);
//                        mVisualizerView2.updateVisualizer(waveform);
//                        mVisualizer.setEnabled(true);

                        // When the stream ends, we don't need to collect any more data. We don't do this in
                        // setupVisualizerFxAndUI because we likely want to have more, non-Visualizer related code
                        // in this callback.

                    }
                }, 5200 );



                break;
            case R.id.play:
                Toast.makeText(getActivity(), "play~~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:

                Toast.makeText(getActivity(), "save~~", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();


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
