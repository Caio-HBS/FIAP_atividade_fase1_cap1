package br.com.fiap.carrerup.service

import br.com.fiap.carrerup.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("?nat=br&exc=login,phone,cell,id,gender")
    fun getUsers(@Query("results") results: Int): Call<UserResponse>

}