package ice_and_fire_api.api

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
import `ice-and-fire-api`.api.common.Common
import `ice-and-fire-api`.api.inter.RetrofitService
import `ice-and-fire-api`.api.model.Hero
import `ice-and-fire-api`.api.util.requestPermissionCompat
import `ice-and-fire-api`.api.util.showSnackbar
import ice_and_fire_api.api.HeroAdapter
import ru.rut.api.R

const val PERMISSION_REQUEST = 1

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var layout: View
    private lateinit var heroRetrofitService: RetrofitService
    lateinit var heroAdapter: HeroAdapter
    lateinit var layouManager: LinearLayoutManager
    private lateinit var heroRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.main_activity)

        heroRetrofitService = Common.retrofitService
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
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission", "Granted")
                layout.showSnackbar("Granted", Snackbar.LENGTH_SHORT)
            } else {
                Log.i("Permission", "Denied")
                layout.showSnackbar("Denied", Snackbar.LENGTH_SHORT)
            }
        }
    }

    private fun loadData() {
        if (
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
        heroRetrofitService.characters(150, 13).enqueue(object : Callback<MutableList<Hero>> {
            override fun onResponse(
                call: Call<MutableList<Hero>>,
                response: Response<MutableList<Hero>>
            ) {
                heroAdapter = HeroAdapter(baseContext, response.body() as MutableList<Hero>)
                heroRecyclerView.adapter = heroAdapter
                heroAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MutableList<Hero>>, t: Throwable) {
                t.printStackTrace()
                Log.e("NETWORK", "Failed to download data")
            }
        }
        )

    }

    private fun requestInternetPermission() {
        if (
            shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)
            &&
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_NETWORK_STATE)
            &&
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
        ) {
            layout.showSnackbar("Required", Snackbar.LENGTH_INDEFINITE, "Ok") {
                requestPermissionCompat(
                    arrayOf(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.CAMERA
                    ), PERMISSION_REQUEST
                )
            }

        } else {
            layout.showSnackbar("Not available", Snackbar.LENGTH_SHORT)
            requestPermissionCompat(
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.CAMERA
                ), PERMISSION_REQUEST
            )
        }
    }

}