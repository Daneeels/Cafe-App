package com.example.cafeapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val cafeList = ArrayList<Cafe>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cafeList.addAll(dataList())
        runRV()


    }

    private fun dataList() : ArrayList<Cafe>{

        val cafeDataList = ArrayList<Cafe>()
        val cafeNama = resources.getStringArray(R.array.cafe_nama_datas)
        val cafeAlamat = resources.getStringArray(R.array.cafe_alamat_datas)
        val cafeDeskripsi = resources.getStringArray(R.array.cafe_deskripsi_datas)
        val cafeGambar = resources.getStringArray(R.array.cafe_gambar_datas)
        val menuCafeGambar = resources.getStringArray(R.array.cafe_menu_datas)

        for (i in cafeNama.indices){
            cafeDataList.add(Cafe(cafeGambar[i], cafeNama[i], "📌 " + cafeAlamat[i], cafeDeskripsi[i], menuCafeGambar[i]))
        }

        return  cafeDataList
    }

    private fun runRV(){
        binding.cafeRv.layoutManager = LinearLayoutManager(this)
        val cafeAdapter = CafeAdapter(cafeList)
        binding.cafeRv.adapter = cafeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}