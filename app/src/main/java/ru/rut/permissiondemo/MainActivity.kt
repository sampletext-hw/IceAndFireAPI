 package ru.rut.permissiondemo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rut.permissiondemo.common.Common
import ru.rut.permissiondemo.inter.RetrofitService
import ru.rut.permissiondemo.model.Hero
import ru.rut.permissiondemo.util.requestPermissionCompat
import ru.rut.permissiondemo.util.showSnackbar

 const val PERMISSION_REQUEST = 1

 class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

     private lateinit var layout: View
     private lateinit var retService: RetrofitService
     lateinit var heroAdapter: HeroAdapter
     lateinit var layouManager: LinearLayoutManager
     private lateinit var heroRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.main_activity)

        retService = Common.retrofitService
        heroRecyclerView = findViewById(R.id.content)
        layouManager = LinearLayoutManager(this)
        heroRecyclerView.layoutManager = layouManager

        loadData()

    }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if(requestCode == PERMISSION_REQUEST){
             if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                 Log.i("Permission", "Granted")
                 layout.showSnackbar("Granted", Snackbar.LENGTH_SHORT)
             } else {
                 Log.i("Permission", "Denied")
                 layout.showSnackbar("Denied", Snackbar.LENGTH_SHORT)
             }
         }
     }

     private fun loadData(){
         if(
             checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
             &&
             checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
             &&
             checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                 ) {
             Log.i("Permission", "Available")
             layout.showSnackbar("Available", Snackbar.LENGTH_SHORT)
             requestData()
         } else {
             requestInternetPermission()
         }
     }

     private fun requestData() {
         retService.getHeroList().enqueue(object : Callback<MutableList<Hero>>{
             override fun onResponse(
                 call: Call<MutableList<Hero>>,
                 response: Response<MutableList<Hero>>
             ) {
                 heroAdapter = HeroAdapter(baseContext,response.body() as MutableList<Hero>)
                 heroAdapter.notifyDataSetChanged()
                 heroRecyclerView.adapter = heroAdapter


             }

             override fun onFailure(call: Call<MutableList<Hero>>, t: Throwable) {

             }

         }
         )

     }

     private fun requestInternetPermission(){
         if (
             shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)
             &&
             shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_NETWORK_STATE)
             &&
             shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
         ){
             layout.showSnackbar("Required", Snackbar.LENGTH_INDEFINITE, "Ok") {
                 requestPermissionCompat(arrayOf(Manifest.permission.INTERNET,
                     Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CAMERA), PERMISSION_REQUEST)
             }

         } else {
             layout.showSnackbar("Not available", Snackbar.LENGTH_SHORT)
             requestPermissionCompat(arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CAMERA), PERMISSION_REQUEST)
         }
     }

}