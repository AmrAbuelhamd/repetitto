package com.blogspot.soyamr.repetitto.views


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.model.Teachers
import com.blogspot.soyamr.repetitto.model.TecherRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class RecyclerViewActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var teacherRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        //start of initializing the recyclerView
        teacherRecyclerView = findViewById(R.id.teacher_recycler_view)
        initializeTheTecherRecyclerView()
    }

    fun initializeTheTecherRecyclerView() {
        val dataArrayList = ArrayList<Teachers>()
        //todo mkae retrofit call and recieve list of teachers
        //todo then connect it with recyclerView
        dataArrayList.add(Teachers("android", "programing", 150, 1))
        dataArrayList.add(Teachers("anghhdroid", "programing", 150, 2))
        dataArrayList.add(Teachers("andrfsoid", "programing", 150, 3))
        dataArrayList.add(Teachers("anghfsdhdroid", "programing", 150, 4))
        dataArrayList.add(Teachers("andrfsoid", "prografsdming", 150, 5))
        dataArrayList.add(Teachers("anddhroid", "programing", 150, 5))
        dataArrayList.add(Teachers("android", "prografsdming", 150, 6))
        dataArrayList.add(Teachers("andhdfroid", "programing", 150, 7))
        dataArrayList.add(Teachers("andgfsdroid", "profsdgraming", 150, 8))
        dataArrayList.add(Teachers("android", "programing", 150, 9))
        dataArrayList.add(Teachers("andgsroid", "profsdgraming", 150, 10))
        dataArrayList.add(Teachers("android", "progrsfdaming", 150, 11))
        dataArrayList.add(Teachers("andsdfroid", "prfsdograming", 150, 12))
        dataArrayList.add(Teachers("android", "programing", 15013, 13))
        teacherRecyclerView!!.setHasFixedSize(true)
        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        teacherRecyclerView!!.layoutManager = mLayoutManager
        //specify an adapter and creating on click listener for each obj
        mAdapter = TecherRecyclerViewAdapter(dataArrayList) { v, position ->
            val intent = Intent(context, UserProfileActivity::class.java).apply {
                putExtra("teacherId", dataArrayList[position].id)
            }
            startActivity(intent)
        }
        teacherRecyclerView!!.adapter = mAdapter
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            val profileIntent = Intent(this, SearchFiltersActivity::class.java)
            startActivity(profileIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_home) { // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_tools) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}