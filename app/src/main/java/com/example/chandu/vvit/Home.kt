package com.example.chandu.vvit
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast

/**
 * Created by chandu on 15/4/18.
 */
class Home : Fragment()  {
   var bundle=Bundle()
    lateinit var text:TextView
    lateinit var text2:TextView
    lateinit var text3:TextView
    lateinit var text4:TextView
    lateinit var text5:TextView
    var t:String="abc"
    var t1:Int=0
    var t2:Int=0
    var t3:String="xyz"
    var t4:String="xyz"
    val ctx=this
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.home,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        try {
            bundle=getArguments()
        }
            catch (e:Exception){
                Toast.makeText(this.context, " 1 "+e.toString(), Toast.LENGTH_SHORT).show()
                Log.d("tag",e.toString())
            }
        try{
           t= bundle.getString("xxx")
            text = view!!.findViewById<TextView>(R.id.tv)
            text2 = view.findViewById<TextView>(R.id.tv2)
            text3 = view.findViewById<TextView>(R.id.tv3)
            text4= view.findViewById<TextView>(R.id.tv4)
            text5= view.findViewById<TextView>(R.id.tv5)

            text.setText(t)
            t1=bundle.getInt("att")
            t2=bundle.getInt("backlogs")
            t3=bundle.getString("counselor")
            t4=bundle.getString("cphno")
            text2.setText(t1.toString())
            text3.setText(t2.toString())
            text4.setText(t3)
            text5.setText(t4)
            text5.setOnClickListener {
                val call= Intent(Intent.ACTION_CALL)
                call.setData(Uri.parse("tel:"+t4))
                if(ContextCompat.checkSelfPermission(this.activity,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this.activity, arrayOf(Manifest.permission.CALL_PHONE), Int.MAX_VALUE)
                else
                   startActivity(call)
            }
        }catch (e:Exception){
            Toast.makeText(this.context, " 2 "+e.toString(), Toast.LENGTH_SHORT).show()
            Log.d("tag",e.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }



   /* companion object {
        fun newInstance(): Home = Home()
    }*/

}