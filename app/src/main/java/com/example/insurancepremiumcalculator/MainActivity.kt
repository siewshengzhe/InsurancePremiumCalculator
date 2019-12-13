package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myData:PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        buttonCalculate.setOnClickListener(){
            calculatePremium()
        }
        buttonReset.setOnClickListener(){
            reset()
        }
    }

    fun getPremium():Double{
        return when(spinnerAge.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 +
                    (if (radioButtonMale.isChecked)50.00 else 0.00)+
                    (if (checkBoxSmoker.isChecked)100.00 else 0.00)
            2 -> 90.00 +
                    (if (radioButtonMale.isChecked)100.00 else 0.00)+
                    (if(checkBoxSmoker.isChecked) 150.00 else 0.00 )
            3 -> 120.00 +
                    (if (radioButtonMale.isChecked)150.00 else 0.00)+
                    (if(checkBoxSmoker.isChecked)200.00 else 0.00)
            4 ->150.00 +
                    (if (radioButtonMale.isChecked)200.00 else 0.00)+
                    (if(checkBoxSmoker.isChecked) 250.00 else 0.00 )
            else -> 150.00+
                    (if (radioButtonMale.isChecked)200.00 else 0.00)+
                    (if(checkBoxSmoker.isChecked) 300.00 else 0.00 )

        }
    }

    fun calculatePremium(){
        myData.premiumAmount = getPremium()
        display()
    }

    fun display(){
        if (myData.premiumAmount != 0.00)
            textViewPremium.text ="RM " + myData.premiumAmount.toString()
    }

    fun reset(){
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        checkBoxSmoker.setChecked(false)
        textViewPremium.setText("")
    }

}

