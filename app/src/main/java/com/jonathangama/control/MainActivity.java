package com.jonathangama.control;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.FragmentManager;

import com.jonathangama.R;
import com.jonathangama.dao.GlobalVariables;
import com.jonathangama.model.Book;
import com.jonathangama.view.BookFragment;
import com.jonathangama.view.BookListFragment;
import com.jonathangama.view.Communicator;
import com.jonathangama.view.UpdatableFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Communicator {

    FloatingActionButton btnMenu;
    FragmentManager fragmentManager;
    UpdatableFragment fragment = null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initialize();
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        initialize();
     }


    private void initialize()
    {
        btnMenu = (FloatingActionButton) findViewById(R.id.fab);
        btnMenu.setOnClickListener(this);
        btnMenu.setImageResource(R.drawable.reload);

        context = getApplicationContext();

        fragmentManager = getFragmentManager();

        switchFragment(0);

    }

    private void executeBackButton()
    {
        if(GlobalVariables.getInstance().getActiveFragmentIndex()>0)
        {
            int index = GlobalVariables.getInstance().getActiveFragmentIndex();
            index = index-1;
            GlobalVariables.getInstance().setActiveFragmentIndex(index);
            switchFragment(index);
        }
        else
        {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            executeBackButton();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showBook(Book book)
    {
        switchFragment(1);
    }


    private void switchFragment(int position) {

        switch (position) {
            case 0:
                fragment = new BookListFragment();
                break;
            case 1:
                fragment = new BookFragment();
                break;
            default:
                break;
        }


        if (fragment != null) {

            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();


        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }

    }


    public void viewBook(Book book) {
        switchFragment(1);
    }

    @Override
    public void toast(String string) {

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
            case R.id.fab:
                executeMenuButton(v);
                break;
            default:
                //anyOtherMethod();
                break;
        }
    }

    private void executeMenuButton(View v)
    {
        String message="";
            if (fragment != null) {
                if (GlobalVariables.getInstance().getActiveFragmentIndex()==0) {
                    fragment.reloadData();
                    message= "Data has been reloaded";
                }
                else
                {
                    executeBackButton();
                    message = "Back to the list";
                }
                Snackbar.make(v, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

    }
}
