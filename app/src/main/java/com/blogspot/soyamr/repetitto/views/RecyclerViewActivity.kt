package com.blogspot.soyamr.repetitto.views


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.repetitto.Constants
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.FilterParameter
import com.blogspot.soyamr.repetitto.RetrofitPojo.User
import com.blogspot.soyamr.repetitto.RetrofitPojo.UserById
import com.blogspot.soyamr.repetitto.RetrofitSingleton
import com.blogspot.soyamr.repetitto.model.TecherRecyclerViewAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    var dataArrayList: MutableList<User> = ArrayList()
    //private var mQuestions: MutableList<Question> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
//        val fab = findViewById<FloatingActionButton>(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        //get the current user data from server
        getCurrentUserInfo()
        //start of initializing the recyclerView
        initializeTheTecherRecyclerView()
        getUsersListFromServer()
    }

    private fun getCurrentUserInfo() {
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        val userId: Int? = sharedPreference.getInt(Constants.userId, -1)
        val userToken: String? = sharedPreference.getString(Constants.token, "null")
        Log.v("amor", "id: " + userId)
        Log.v("amor", "token: " + userToken)

        RetrofitSingleton.retrofitObject
                .getCurrentUser(userId.toString(), userToken).enqueue(object : retrofit2.Callback<UserById> {
                    override fun onFailure(call: Call<UserById>, t: Throwable) {
                        Toast.makeText(this@RecyclerViewActivity, "something wentWrong", Toast.LENGTH_LONG).show()
                        return
                    }

                    override fun onResponse(call: Call<UserById>, response: Response<UserById>) {
                        var userById: UserById? = response.body()
                        fillCurrentUserProfile(userById)
                    }
                })
    }

    private fun fillCurrentUserProfile(userById: UserById?) {
        //todo fill the user data
        userFirstName.text = userById?.firstName
        textView.text = userById?.email
        Log.v("amor", "first: " + userById?.firstName)

    }

    fun initializeTheTecherRecyclerView() {

        teacher_recycler_view!!.setHasFixedSize(true)
        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        teacher_recycler_view!!.layoutManager = mLayoutManager
        //specify an adapter and creating on click listener for each obj
        mAdapter = TecherRecyclerViewAdapter(dataArrayList) { v, position ->
            val intent = Intent(this, UserProfileActivity::class.java).apply {
                putExtra("teacherId", dataArrayList[position].id)
            }
            startActivity(intent)
        }
        teacher_recycler_view!!.adapter = mAdapter
    }

    fun getUsersListFromServer() {
        Log.e("yes", FilterParameter.limit.toString())
        RetrofitSingleton.retrofitObject.getUsersUsingFilter(FilterParameter.onlyTeacher
                , FilterParameter.course
                , FilterParameter.limit
                , FilterParameter.search
                , FilterParameter.faculty
                , FilterParameter.subject).enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@RecyclerViewActivity, "something wentWrong"
                        + " " + t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val users: List<User> = response.body().orEmpty()

//                mAdapter = TecherRecyclerViewAdapter(users) { v, position ->
//                    val intent = Intent(context, UserProfileActivity::class.java).apply {
//                        putExtra("teacherId", users[position].id)
//                    }
//                    startActivity(intent)
//                }
//                teacherRecyclerView!!.adapter = mAdapter
//                mAdapter?.notifyDataSetChanged()

                if (users != null) {
                    dataArrayList.clear()
                    dataArrayList.addAll(users)
                    mAdapter?.notifyDataSetChanged()
                    Log.v("amor", "users is not null")
                    //Log.v("amor","fisrst user= "+dataArrayList[0].lastName)
                } else {
                    Log.v("amor", "users is null")
                }

            }
        })
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            finish();
            moveTaskToBack(true)
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
        } else if (id == R.id.action_logout) {
            disableTokenFromServer()
//            val profileIntent = Intent(this, MainActivity::class.java)
//            startActivity(profileIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun disableTokenFromServer() {
        //it works in second time only!!!

        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        val token: String? = sharedPreference.getString(Constants.token, "")


        RetrofitSingleton.retrofitObject.logOut(token).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@RecyclerViewActivity, "something wentWrong"
                        + " " + t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>?) {
                deleteTheUserIdAndToken()
                openSignInActivity()
            }
        })
    }

    private fun openSignInActivity() {
        val profileIntent = Intent(this, MainActivity::class.java)
        startActivity(profileIntent)
    }

    private fun deleteTheUserIdAndToken() {
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt(Constants.userId, -1)
        editor.putString(Constants.token, "")
        editor.apply()
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