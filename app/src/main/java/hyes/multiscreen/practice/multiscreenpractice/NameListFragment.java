package hyes.multiscreen.practice.multiscreenpractice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hyes.multiscreen.practice.multiscreenpractice.DAO.Dao;
import hyes.multiscreen.practice.multiscreenpractice.Model.NameItem;

/**
 * Created by hyes on 2015. 9. 7..
 */
public class NameListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final int TARGET = 1;
    private ArrayList<NameItem> nameList;
    private ListView listView;
    private TextView tv, empty;

    public NameListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_name_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        refreshData();
        listView();
     

    }


    private void listView(){
        listView = (ListView) getActivity().findViewById(R.id.nameListView);
        Dao dao = new Dao(getActivity().getApplicationContext());
        nameList = dao.getArticleList();

        if(nameList.isEmpty()){

            Toast.makeText(getActivity().getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();

        }

        NameAdapter nameAdapter = new NameAdapter(this.getActivity(), R.layout.custom_name_list, nameList);
        listView.setAdapter(nameAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        Intent intent = new Intent(this, RecordingDataList.class);
//
//        intent.putExtra("Name", nameList.get(position).getName());
//        intent.putExtra("id", nameList.get(position).getId());
//        Log.i("test", "intent " + nameList.get(position).getId());
//        startActivity(intent);

    }


}
