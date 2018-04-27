package com.example.chandu.vvit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CounselorActivity : AppCompatActivity() {
    var fbdb:FirebaseDatabase?=null
    lateinit var cno:EditText
    lateinit var pswd:EditText
    lateinit var submit: Button
    var ctx=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor)
        cno=findViewById(R.id.cno)
        pswd=findViewById(R.id.pswd)
        submit=findViewById(R.id.submit)
        fbdb= FirebaseDatabase.getInstance()
        submit.setOnClickListener {
            login()

        }

        }
    fun login(){
        val newref=fbdb?.getReference(cno.text.toString())
        newref!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()) {
                    val hashMap = p0?.value as HashMap<String, String>
                    val cpswd = hashMap["cpswd"]
                    if (cpswd == pswd.text.toString()) {
                        val intent = Intent(applicationContext, Edit::class.java)
                        intent.putExtra("cno", cno.text.toString())
                        ctx.finish()
                        startActivity(intent)

                    } else {
                        Toast.makeText(applicationContext, "Enter valid password", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(applicationContext, "Enter valid id", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    }

