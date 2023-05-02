package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.imc.ResultActivity.Companion.IMC_RESULT
import com.example.imc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentWeight:Double = 60.0
    private var currentAge:Double = 20.0
    private var currentHeight: Double = 120.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponentes()
    }

    private fun initComponentes() {
        binding.cardviewMale.setOnClickListener {
            setGenderColor(true)
        }
        binding.cardviewFemale.setOnClickListener {
            setGenderColor(false)
        }
        binding.rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toDouble()
            binding.textHeight.text = "$currentHeight cm"
        }
        binding.btnSubtractWeight.setOnClickListener {
            currentWeight--
            binding.textWeight.text = currentWeight.toString()
        }
        binding.btnPlusWeight.setOnClickListener {
            currentWeight++
            binding.textWeight.text = currentWeight.toString()
        }
        binding.btnSubtractAge.setOnClickListener {
            currentAge--
            binding.textAge.text = currentAge.toString()
        }
        binding.btnPlusAge.setOnClickListener {
            currentAge++
            binding.textAge.text = currentAge.toString()
        }
        binding.btnCalculate.setOnClickListener {
            navigateToResult(calculateIMC())
        }
    }

    private fun navigateToResult(calculateIMC: Double) {
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra(IMC_RESULT, calculateIMC)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val imc = currentWeight/((currentHeight/100)*(currentHeight/100))
        val df = DecimalFormat("#.##")
        return df.format(imc).toDouble()
    }

    private fun setGenderColor(isSelected:Boolean){
        if(isSelected){
            binding.cardviewMale.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.background_component_selected)
            )
            binding.cardviewFemale.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.background_component)
            )
        }else{
            binding.cardviewFemale.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.background_component_selected)
            )
            binding.cardviewMale.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.background_component)
            )
        }
    }

    

}