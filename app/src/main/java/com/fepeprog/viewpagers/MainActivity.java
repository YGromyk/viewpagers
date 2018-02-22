package com.fepeprog.viewpagers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            /*
            switch (item.getItemId()) {
                case R.id.navigation_home:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, FragmentPage.newInstance(item.getTitle().toString()))
                            .commit();
                    return true;
                }
                case R.id.navigation_dashboard:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, FragmentHome.newInstance())
                            .commit();
                    return true;
                }
                case R.id.navigation_notifications:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, FragmentPage.newInstance(item.getTitle().toString()))
                            .commit();
                    return true;
                }
            }
            */
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service = new Intent(this, MyService.class);
        service.setAction(MyService.ACTION);
        startService(service);
        /*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, FragmentPage.newInstance("hiiii"))
                .commit();
                */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

}
