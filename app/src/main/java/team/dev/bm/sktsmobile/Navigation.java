package team.dev.bm.sktsmobile;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import team.dev.bm.sktsmobile.Fragments.ImportFragmentAbout;
import team.dev.bm.sktsmobile.Fragments.ImportFragmentEditProfile;
import team.dev.bm.sktsmobile.Fragments.ImportFragmentHome;
import team.dev.bm.sktsmobile.Fragments.ImportFragmentsuratkts;
import team.dev.bm.sktsmobile.Fragments.ImportFragmentsuratpindah;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new ImportFragmentHome()).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sp = getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fm.beginTransaction().replace(R.id.content_frame, new ImportFragmentHome()).commit();
            getSupportActionBar().setTitle("SKTS Mobile");
            getSupportActionBar().setSubtitle("Beranda");
        } else if (id == R.id.nav_about) {
            fm.beginTransaction().replace(R.id.content_frame, new ImportFragmentHome()).commit();

            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Navigation.this);
            builder.setMessage("SKTS Mobile\n" +
                    "\n" +
                    "Aplikasi ini dibuat agar mempermudah masyarakat Depok khususnya para pendatang untuk membuat surat izin tinggal sementara di Kota Depok.\n" +
                    "\n\n" +
                    "B.M Dev Team\n" +
                    "2017")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    });
            android.support.v7.app.AlertDialog judul = builder.create();
            judul.setTitle("About");
            judul.setIcon(R.drawable.ic_menu_about);
            judul.show();

        } else if (id == R.id.nav_camera) {
            fm.beginTransaction().replace(R.id.content_frame, new ImportFragmentEditProfile()).commit();
            getSupportActionBar().setTitle("Edit Profile");
            getSupportActionBar().setSubtitle("");
        } else if (id == R.id.nav_galery) {
            fm.beginTransaction().replace(R.id.content_frame, new ImportFragmentsuratpindah()).commit();
        } else if (id == R.id.nav_exit) {
            Navigation.this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Anda Yakin?")
                .setCancelable(true)
                .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.this.finish();
                    }
                })
                .setPositiveButton("Tidak", null)
                .show();
    }
}
