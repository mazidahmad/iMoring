package com.example.imoring;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imoring.Fragment.AddDeviceFragment;
import com.example.imoring.Fragment.DashboardFragment;
import com.example.imoring.Fragment.HomeFragment;
import com.example.imoring.Fragment.NotificationFragment;
import com.example.imoring.Fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //Our View Pager
    private ViewPager viewPager;

    //Fragment
    HomeFragment homeFragment;
    DashboardFragment dashboardFragment;
    NotificationFragment notificationFragment;
    AddDeviceFragment addDeviceFragment;
    ProfileFragment profileFragment;
    MenuItem prevMenuItem;

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisiasi ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Inisiasi Bottom Navigation Bar
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_add:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.navigation_profile:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                }
                else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page","onPageSelected"+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        dashboardFragment = new DashboardFragment();
        addDeviceFragment = new AddDeviceFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(dashboardFragment);
        adapter.addFragment(addDeviceFragment);
        adapter.addFragment(notificationFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }

}
