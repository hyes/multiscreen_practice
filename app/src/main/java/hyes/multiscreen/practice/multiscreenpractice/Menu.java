package hyes.multiscreen.practice.multiscreenpractice;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyes on 2015. 9. 7..
 */
public class Menu extends ActionBarActivity {

    MenuFragment menuFragment;
    android.support.v4.app.FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuFragment = new MenuFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.fragment_menu);
            fragmentTransaction.add(R.id.left, menuFragment);
            fragmentTransaction.commit();


        } else {

            setContentView(R.layout.menu);
            Button send_btn = (Button) findViewById(R.id.send_btn);
            Button record_btn = (Button) findViewById(R.id.record_btn);

            send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NameListFragment nameListFragment = new NameListFragment();
                    fragmentTransaction.add(R.id.container, nameListFragment);
                    fragmentTransaction.commit();
                }
            });

            record_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TargetFragment targetFragment = new TargetFragment();
                    fragmentTransaction.add(R.id.container, targetFragment);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
