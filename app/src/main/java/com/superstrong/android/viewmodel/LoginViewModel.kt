package com.superstrong.android.viewmodel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superstrong.android.data.User
import com.superstrong.android.data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun PostLogin(username: String, password: String,context: Context) {
        val user = User(username, password) // 전송할 데이터 모델 객체 생성

        val call = RetrofitClient.backendApiService.login(user) // POST 요청 보내기

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.toString()
                    // 요청 성공 처리
                } else {
                    // 요청 실패 처리
                    val errorBody = response.errorBody()?.toString()
                    Toast.makeText(context, "로그인이 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "통신 실패", Toast.LENGTH_SHORT).show()
                // 통신 실패 처리
            }
        })
    }

}
