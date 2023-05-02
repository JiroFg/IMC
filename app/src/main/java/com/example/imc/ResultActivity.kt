package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.imc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object{
        const val IMC_RESULT = "IMC_RESULT"
    }

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val result = intent.extras?.getDouble(IMC_RESULT)
        //binding.result.text = "Tu índice de masa corporal es $result"
        initUI(result!!)

        binding.btn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUI(result:Double){
        binding.numResult.text = result.toString()
        when(result){
            in 0.00..18.50 -> {
                binding.result.text = getString(R.string.title_under_weight)
                binding.result.setTextColor(ContextCompat.getColor(this,R.color.under))
                binding.descriptionResult.text = getString(R.string.desc_under_weight)
            }
            in 18.51..24.99 -> {
                binding.result.text = getString(R.string.title_normal_weight)
                binding.result.setTextColor(ContextCompat.getColor(this,R.color.normal))
                binding.descriptionResult.text = getString(R.string.desc_normal_weight)
            }
            in 25.00..29.99 -> {
                binding.result.text = getString(R.string.title_over_weight)
                binding.result.setTextColor(ContextCompat.getColor(this,R.color.over))
                binding.descriptionResult.text = getString(R.string.desc_over_weight)
            }
            in 30.00..99.00 -> {
                binding.result.text = getString(R.string.title_obesity_weight)
                binding.result.setTextColor(ContextCompat.getColor(this,R.color.obesity))
                binding.descriptionResult.text = getString(R.string.desc_obesity_weight)
            }
            else ->{
                binding.result.text = getString(R.string.error)
                binding.descriptionResult.text = getString(R.string.error)
            }
        }
    }
}