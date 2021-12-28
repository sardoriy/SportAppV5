package uz.mufassal.apihomework

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mufassal.apihomework.network.Resource
import uz.mufassal.apihomework.utils.toast

class MainActivity : AppCompatActivity() {

    private val imageUrl = "https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__480.jpg"

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refresh_api.setOnClickListener {
            getData()
        }

    }

    private fun getData() {
        val fruit_name = search_fruit.text.toString()
        if (fruit_name.isNotEmpty()){
            val progressDialog = ProgressDialog(this)
            mainViewModel.getFruit("${fruit_name}")
            mainViewModel.fruitsResponse.observe(this, androidx.lifecycle.Observer {
                it.getContent().let { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            val forToast = search_fruit.text.toString()
                            progressDialog.setMessage("${forToast}\thaqida ma'lumotlar qidirilmoqda...")
                            progressDialog.show()
                        }
                        is Resource.Success -> {
                            Log.d("here", "in success")
                            progressDialog.dismiss()
                            api_text.text = resource.data.name
                            api_text2.text = resource.data.family
                            api_text3.text = resource.data.genus
                            api_text4.text = resource.data.order

                            api_text5.text = "Uglevodlar : ${resource.data.nutritions.carbohydrates}\n\nProtein : ${resource.data.nutritions.protein}\n\nVazni : ${resource.data.nutritions.fat}\n\nKaloriyasi : ${resource.data.nutritions.calories}\n\nShakar : ${resource.data.nutritions.sugar}"

//                        Glide.with(this).load(resource.data.message).into(api_image)
                        }
                        is Resource.Error -> {
                            toast("Xatolik yuz berdi.\nInternet aloqasini tekshiring")
                            Log.d("error", "Error: ${resource.exception.message}")
                        }
                        is Resource.GenericError -> {
                            toast("Server bilan aloqa yo'q")
                            Log.d("error", "GenericError: ${resource.errorResponse.message}")
                        }
                    }
                }

            })
        }
        else{
            toast("Biron meva nomini yozing.")
        }
    }

}