package com.example.stajxml;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.stajxml.taksi.FragmentTaksi;
import com.example.stajxml.tarif.TarifFragment;
import com.example.stajxml.vipTaksi.ModelVipTaksi;
import com.example.stajxml.vipTaksi.VipTaksiAsoFragment;
import com.example.stajxml.vipTaksi.VipTaxiAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        SearchView.OnCloseListener , IOnclickListener{
   private List<ModelVipTaksi> list;
    private List<ModelVipTaksi> vipTaksiList;
    DrawerLayout drawer;
    Toolbar toolbar;
    private VipTaxiAdapter adapter;
    NavigationView navigationView;
    TextView tvUz, tvRu, tvReg;


    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);

        tvRu = header.findViewById(R.id.tvRus);
        tvUz = header.findViewById(R.id.tvUz);
        tvReg = header.findViewById(R.id.tvReg);

        tvUz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uzSlect();
            }
        });
        tvRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rusSlect();
            }
        });


        tvUz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvReg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        FragmentTaksi fragmentTaksi = new FragmentTaksi();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            .add(R.id.for_fragments, fragmentTaksi).addToBackStack(null).commit();
        }

        initNavigationMenu();

        imageView=findViewById(R.id.image_logo);

        fragmentTaksi.setListener(new FragmentTaksi.SearchViewListener() {
            @Override
            public void onClick(int positon) {
                if (positon == 0){
                    findViewById(R.id.image_logo).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.image_logo).setVisibility(View.INVISIBLE);
                }
            }
        });
//        VipTaksiAsoFragment vipTaksiAsoFragment=new VipTaksiAsoFragment();
//        vipTaksiAsoFragment.setListener(new VipTaksiAsoFragment.SearchViewListener() {
//            @Override
//            public void onClick(int positon) {
//                if (positon == 0){
//                    findViewById(R.id.image_logo).setVisibility(View.VISIBLE);
//                }else {
//                    findViewById(R.id.image_logo).setVisibility(View.INVISIBLE);
//                }
//            }
//        });
   }

    @Override
    protected void onStart() {
        super.onStart();
        vipTaksiList=new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else {
            getSupportFragmentManager().popBackStack();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnCloseListener(this);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.image_logo).setVisibility(View.GONE);
                Log.d("TTT", "search open");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.search) {
//            imageView.setVisibility(View.GONE);
//            return true;
//        } else
            if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        if (id == R.id.tarif) {
            TarifFragment tarifFragment = new TarifFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.for_fragments, tarifFragment,null);
                transaction.addToBackStack(null);
            transaction.commit();
//            getSupportFragmentManager().beginTransaction().add(R.id.for_fragments, new TarifFragment()).commit();
        } else if (id == R.id.sob) {

        } else if (id == R.id.zak) {

        } else if (id == R.id.dob) {

        } else if (id == R.id.infor) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavigationMenu() {
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                else drawer.openDrawer(GravityCompat.START);
            }
        });

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {


    }
    void uzSlect(){
        tvUz.setTextColor(getResources().getColor(R.color.colorWhite));
        tvRu.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvRu.setTextColor(getResources().getColor(R.color.colorBlack));

    }
    void rusSlect(){
        tvUz.setTextColor(getResources().getColor(R.color.colorBlack));
        tvRu.setTextColor(getResources().getColor(R.color.colorWhite));
        tvRu.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }



    @Override
    public boolean onClose() {
        findViewById(R.id.image_logo).setVisibility(View.VISIBLE);
        Log.d("TTT", "close search");
        return false;
    }

    @Override
    public void onClick(int positon) {
        if (positon == 0){
            findViewById(R.id.image_logo).setVisibility(View.INVISIBLE);
        }else {
            findViewById(R.id.image_logo).setVisibility(View.VISIBLE);
        }
    }
}
