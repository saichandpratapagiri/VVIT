package com.example.chandu.vvit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    var firebasedb:FirebaseDatabase?=null
    var bundle=Bundle()
    var ctx=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val go=findViewById<Button>(R.id.go)
        val regdno=findViewById<AutoCompleteTextView>(R.id.regdno)
        val cno=findViewById<AutoCompleteTextView>(R.id.cno)
        val strregd=resources.getStringArray(R.array.regd)
        val strname=resources.getStringArray(R.array.counselor)
        val adapter1= ArrayAdapter(this, android.R.layout.simple_list_item_1, strregd)
        val adapter2= ArrayAdapter(this, android.R.layout.simple_list_item_1, strname)
        regdno.setAdapter(adapter1)
        cno.setAdapter(adapter2)
        firebasedb= FirebaseDatabase.getInstance()
        if (regdno.text != null) {
            go.setOnClickListener {
                getDatafromFb()
                }
            }



        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.abc,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent=Intent(applicationContext,CounselorActivity::class.java)
        ctx.finish()
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        ctx.finish()
        System.exit(0)
    }
    fun getDatafromFb(){

        val newref=firebasedb!!.getReference(cno.text.toString())
        newref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                try {
                    val hashMap = p0?.value as HashMap<String, String>
                    val cname = hashMap["cname"]
                    val cno1 = hashMap["cphno"]
                    bundle.putString("cphno", cno1!!.toString())
                    bundle.putString("counselor", cname!!.toString())
                    bundle.putString("cno", cno.text.toString())

                    val ref = newref.child(regdno.text.toString())
                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError?) {
                            Toast.makeText(applicationContext, p0.toString(), Toast.LENGTH_SHORT).show()
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            if(p0!!.exists()){
                                val hashMap = p0!!.value as HashMap<String, Long>
                                val h1 = p0!!.value as HashMap<String, String>

                                if (hashMap.size > 0) {
                                    val att = hashMap["percentage"]
                                    val backlogs = hashMap["backlogs"]
                                    val complaint = h1["complaints"]
                                    val xxx = regdno.text.toString()

                                    //Toast.makeText(applicationContext,att.toString()+" "+backlogs.toString()+" "+ref.parent.key,Toast.LENGTH_SHORT).show()
                                    bundle.putString("xxx", xxx)
                                    bundle.putString("complaints", complaint)
                                    bundle.putInt("att", att!!.toInt())
                                    bundle.putInt("backlogs", backlogs!!.toInt())
                                    val intent = Intent(applicationContext, MainActivity::class.java)
                                    intent.putExtra("bundle", bundle)
                                    ctx.finish()
                                    startActivity(intent)
                                }
                            }
                            else{
                                Toast.makeText(applicationContext, "Enter Valid register number", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })

                }catch (e:Exception){
                    Toast.makeText(applicationContext, "Enter Valid number", Toast.LENGTH_SHORT).show()
                }
            }

    })

    }
}


