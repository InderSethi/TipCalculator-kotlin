@file:Suppress("UNREACHABLE_CODE")

package org.hyperskill.calculator.tip

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edittext = findViewById<EditText>(R.id.edit_text)
        var slider = findViewById<Slider>(R.id.slider)
        var tv = findViewById<TextView>(R.id.text_view)
        var tip: BigDecimal
        var amt: Double =0.00


        edittext.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (edittext.text.toString() == ""){
                    tv.text=""
                } else {
                    amt = edittext.text.toString().toDouble()
                    var check : Double = 0.00
                    check = amt * slider.value/100
                    tip = check.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)

                    tv.text = (("Tip amount: " + tip))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

             //   tv.text = (("Bill value: " + edittext.text.toString() +", tip percentage: "+slider.value+"%"))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ///
            }
        })

        slider.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_MOVE ->
                        if (edittext.text.toString() == "") {
                            tv.text = ""
                        } else {
                            amt = edittext.text.toString().toDouble()
                            var check : Double = 0.00
                            check = amt * slider.value/100
                            tip = check.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                            tv.text = (("Tip Amount: " + tip))
                        }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })


    }

}