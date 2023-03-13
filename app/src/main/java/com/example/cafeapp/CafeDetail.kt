package com.example.cafeapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cafeapp.databinding.ActivityCafeDetailBinding


class CafeDetail : AppCompatActivity() {

    companion object {
        const val KEY_CAFE = "key_cafe"
    }

    private lateinit var binding : ActivityCafeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_detail)

        binding = ActivityCafeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLayoutData()
        runShareButton()

    }

    private fun setLayoutData(){
        val cafeData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_CAFE, Cafe::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_CAFE)
        }

        if (cafeData != null) {
            Glide.with(this).load(cafeData.gambarCafe).into(binding.gambarCafeDetail)
            binding.namaCafeDetail.text = cafeData.namaCafe
            binding.alamatCafeDetail.text = cafeData.alamatCafe
            binding.deskripsiCafeDetail.text = cafeData.deskripsiCafe
            Glide.with(this).load(cafeData.gambarMenuCafe).into(binding.gambarMenuDetail)
        }

        //Support Action Bar
        supportActionBar?.apply {
            title = cafeData?.namaCafe
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun runShareButton(){
        binding.actionShare.setOnClickListener {

            val namaCafe = binding.namaCafeDetail.text.filter { !it.isWhitespace() }
            val url = "https://www.google.com/search?q=$namaCafe"

            val shareIntent = Intent(Intent.ACTION_SEND)


            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(shareIntent, "Share to"))
        }
    }


}