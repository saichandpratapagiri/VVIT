package com.example.chandu.vvit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    var bundle1 = Bundle()
    val hfragment = Home()
    val ifragment = Info()
    var regdno = " "
    var att = 0
    var backlogs = 0
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = "Home"
                //bundle.putString("key",regdno)
                // Toast.makeText(applicationContext,bundle.toString(), Toast.LENGTH_SHORT).show()
                hfragment.arguments = bundle1
                supportFragmentManager.beginTransaction().replace(R.id.container, hfragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                toolbar.title = "Info"
                ifragment.arguments=bundle1
                supportFragmentManager.beginTransaction().replace(R.id.container, ifragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            toolbar = supportActionBar!!
            bundle1 = intent.getBundleExtra("bundle")
            regdno = bundle1.getString("xxx")
            att = bundle1.getInt("att")
            backlogs = bundle1.getInt("backlogs")
            toolbar.title = "Home"
            //bundle.putString("key",regdno)

            // Toast.makeText(applicationContext,bundle.toString(), Toast.LENGTH_SHORT).show()
            hfragment.arguments = bundle1
            supportFragmentManager.beginTransaction().replace(R.id.container, hfragment).commit()
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val intent = intent
        val message = intent.getStringExtra("message")
        if (!message.isNullOrEmpty()) {
            AlertDialog.Builder(this)
                    .setTitle("Notification")
                    .setMessage(message)
                    .setPositiveButton("Ok", { dialog, which -> }).show()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent= Intent(applicationContext,SplashActivity::class.java)
        finish()
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}