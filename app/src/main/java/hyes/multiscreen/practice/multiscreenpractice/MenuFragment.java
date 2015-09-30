package hyes.multiscreen.practice.multiscreenpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment implements View.OnClickListener{

    public MenuFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Button send_btn = (Button) getActivity().findViewById(R.id.send_btn);
        Button record_btn = (Button) getActivity().findViewById(R.id.record_btn);

        send_btn.setOnClickListener(this);
        record_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.send_btn:
                NameListFragment nameListFragment = new NameListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.right, nameListFragment);
                fragmentTransaction.commit();

                break;
            case R.id.record_btn:
                TargetFragment targetFragment = new TargetFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.add(R.id.right, targetFragment);
                fragmentTransaction1.commit();
                break;
        }


    }


}
