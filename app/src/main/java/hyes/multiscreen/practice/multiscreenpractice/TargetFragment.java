package hyes.multiscreen.practice.multiscreenpractice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hyes.multiscreen.practice.multiscreenpractice.DAO.Dao;


public class TargetFragment extends Fragment {
    private static final int TARGET_NAME = 1;
    String target_name = null, temp_age;
    int target_age;
    EditText name_edit_text, age_edit_text;
//


//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuFragment newInstance(String param1, String param2) {
//        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    public TargetFragment() {
        // Required empty public constructor
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.target, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onStart() {
        super.onStart();
        name_edit_text = (EditText) getActivity().findViewById(R.id.target_name);
        age_edit_text = (EditText) getActivity().findViewById(R.id.target_age);
        Button start_btn = (Button) getActivity().findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                target_name = name_edit_text.getText().toString();
                temp_age = age_edit_text.getText().toString();


                if (target_name.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();

                } else if(temp_age.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter age", Toast.LENGTH_SHORT).show();

                } else{

                    try {
                        target_age = Integer.parseInt(temp_age);

                        Dao dao = new Dao(getActivity().getApplicationContext());
                        dao.targetInsert(target_name, target_age);

                        Intent intent = new Intent(getActivity().getApplicationContext(), Record.class);
                        intent.putExtra("name", target_name);
                        startActivityForResult(intent, TARGET_NAME);


                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity().getApplicationContext(), "Age should be numerical number only", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

    //
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
