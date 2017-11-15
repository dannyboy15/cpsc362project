package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ClassFragment.OnFragmentInteractionListener,
        ProfFragment.OnFragmentInteractionListener, ProfInfoFragment.OnFragmentInteractionListener,
        ClubsFragment.OnFragmentInteractionListener, ClubInfoFragment.OnFragmentInteractionListener,
        TutoringFragment.OnFragmentInteractionListener, ClassInfoFragment.OnFragmentInteractionListener,
        NotesFragment.OnFragmentInteractionListener, EventsFragment.OnFragmentInteractionListener {

    private String userName;
    private String userEmail;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout
        setContentView(R.layout.activity_main22);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        // Set Floating Action Button
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Set drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set navigatio menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        session = SessionManager.getInstance(getApplicationContext());
        Log.i("MainActivity", session.toString());

        getUserInfo();

        // Set the user's name
        TextView nav_user = (TextView)hView.findViewById(R.id.user_name);
        nav_user.setText(userName);

        // Set the user's email
        TextView nav_email = (TextView)hView.findViewById(R.id.user_email);
        nav_email.setText(userEmail);

        // Set classes fragment main fragment
        // get fragment manager
        FragmentManager fm = getFragmentManager();
        ClassFragment classFragment = new ClassFragment();

        // add
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentArea, classFragment);
        ft.commit();

    }

    private void getUserInfo() {
//        SharedPreferences pref = getSharedPreferences("TitanimitePref", Context.MODE_PRIVATE);
        userName = session.getFirstName(); //pref.getString("firstName", "");
        userName += " " + session.getLastName(); //pref.getString("lastName", "");
        userEmail = session.getEmail(); //pref.getString("email", "");

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//
//        Log.i("Main Activty", "My Prefs: " + pref.getAll());
//        Log.i("Main Activty", "Default Prefs: " + prefs.getAll() + "\n" + prefs.getString("disp_name", "nothing there"));
//
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("example_text", userName);
//        editor.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main22, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;

        if (id == R.id.nav_classes) {
            FragmentManager fm = getFragmentManager();
            ClassFragment classFragment = new ClassFragment();

            // add
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, classFragment);
            ft.commit();
        } else if (id == R.id.nav_prof) {
            FragmentManager fm = getFragmentManager();
            ProfFragment profFragment = new ProfFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, profFragment);
            ft.commit();
        } else if (id == R.id.nav_tutoring) {
            FragmentManager fm = getFragmentManager();
            TutoringFragment tutorFragment = new TutoringFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, tutorFragment);
            ft.commit();

        } else if (id == R.id.nav_clubs) {
            // get fragment manager
            FragmentManager fm = getFragmentManager();
            ClubsFragment clubsFragment = new ClubsFragment();

            // add
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, clubsFragment);
            ft.commit();

        } else if (id == R.id.nav_logout) {
            session.logoutUser();

            intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_events) {
            FragmentManager fm = getFragmentManager();
            EventsFragment eventsFragment = new EventsFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, eventsFragment);
            ft.commit();

        } else if (id == R.id.nav_notes) {
            FragmentManager fm = getFragmentManager();
            NotesFragment notesFragment = new NotesFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.contentArea, notesFragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
