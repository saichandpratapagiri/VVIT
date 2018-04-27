package com.example.chandu.vvit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Edit : AppCompatActivity() {
    lateinit var regdno:AutoCompleteTextView
    lateinit var att:EditText
    lateinit var b:EditText
    lateinit var go: Button
    lateinit var save:Button
    lateinit var complaint:EditText
    lateinit var fbdb1:FirebaseDatabase
    var ctx=this
    var s1:Long=0
    var s2:Long=0
    var s=" "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val intent=intent
        regdno=findViewById(R.id.regdno)
        att=findViewById(R.id.att)
        b=findViewById(R.id.b)
        go=findViewById(R.id.go)
        save=findViewById(R.id.save)
        complaint=findViewById(R.id.complaints)
        val strregd=resources.getStringArray(R.array.regd)
        val adapter1= ArrayAdapter(this, android.R.layout.simple_list_item_1, strregd)
        regdno.setAdapter(adapter1)
        fbdb1= FirebaseDatabase.getInstance()
        val x=intent.extras["cno"]
        val ref=fbdb1.getReference(x.toString())
        go.setOnClickListener {
            //Toast.makeText(applicationContext,x.toString(),Toast.LENGTH_SHORT).show()
               ref!!.addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p1: DataSnapshot) {
                        if(p1.hasChild(regdno.text.toString())==true){
                            Toast.makeText(applicationContext,"credentials of "+regdno.text.toString(),Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(applicationContext,"No such Number",Toast.LENGTH_LONG).show()
                        }
                        val ref2=ref.child(regdno.text.toString())
                        ref2!!.addValueEventListener(object : ValueEventListener{
                            override fun onCancelled(p0: DatabaseError?) {

                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                val att1 = p1.child("percentage").value
                                val b1 = p1.child("backlogs").value
                                val cmnts=p1.child("complaints").value
                                att.setText(att1.toString())
                                b.setText(b1.toString())
                                complaint.setText(cmnts.toString())
                            }
                        })

                    }
                })

        }
        regdno.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (att.text.toString()==s1.toString() && b.text.toString()==s2.toString())
                {
                 save.isEnabled=false
                }
                else{
                    save.isEnabled=true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 //To change body of created functions use File | Settings | File Templates.
            }
        })
        save.setOnClickListener {
            /*ref!!.child("percentage").setValue(att.text)
            ref!!.child("backlogs").setValue(b.text)
            ref!!.child("complaints").setValue(complaint.text)*/

            ref!!.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p1: DataSnapshot) {
                    if(p1.hasChild(regdno.text.toString())==true){
                        Toast.makeText(applicationContext,"Data svaed",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,regdno.text.toString(),Toast.LENGTH_LONG).show()
                    }
                    val ref2=ref.child(regdno.text.toString())
                    ref2!!.addValueEventListener(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {

                        }

                        override fun onDataChange(p1: DataSnapshot) {
                            val a=ref2.child("percentage")
                            a.setValue(att.text.toString().toLong())
                            val b1=ref2.child("backlogs")
                            b1.setValue(b.text.toString().toLong())
                            val c=ref2.child("complaints")
                            c.setValue(complaint.text.toString())
                        }
                    })

                }
            })
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent=Intent(applicationContext,SplashActivity::class.java)
        ctx.finish()
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext,SplashActivity::class.java))
        ctx.finish()

    }
}


