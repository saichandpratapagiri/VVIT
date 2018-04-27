package com.example.chandu.vvit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by chandu on 15/4/18.
 */
class Info : Fragment() {
    lateinit var complaint: TextView
    var bundle = Bundle()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = getArguments()
        complaint = view!!.findViewById(R.id.complaint)
        complaint.setText(bundle.getString("complaints"))

    }
}