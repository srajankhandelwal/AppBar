package com.example.appbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar mtoolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;

    private fragment_explore explorefragment;
    private fragment_flights flightsfragment;
    private fragment_travel travelfragment;
    private Button button;
    private Snackbar snackbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                snackbar = Snackbar.make(explorefragment.getView(), "Hi this is snackbar", BaseTransientBottomBar.LENGTH_LONG);
                snackbar.show();
                return true;
            case R.id.logout:
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                        alertbox.setMessage("Are you sure?")
                        .setTitle("Logout")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Shut up! Stay here.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "No problem", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                        return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mtoolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mtoolbar);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);
        fab = findViewById(R.id.fab);


        explorefragment = new fragment_explore();
        flightsfragment = new fragment_flights();
        travelfragment = new fragment_travel();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addfragments(explorefragment,"Netflix");
        viewPagerAdapter.addfragments(flightsfragment,"Prime Video");
        viewPagerAdapter.addfragments(travelfragment,"Hotstar");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.netflix);
        tabLayout.getTabAt(1).setIcon(R.drawable.prime);
        tabLayout.getTabAt(2).setIcon(R.drawable.search);

        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable.setNumber(12);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"YOu clicked it",Toast.LENGTH_SHORT).show();
            }
        });




    }






















    public  class ViewPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addfragments(Fragment fragment,String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

}