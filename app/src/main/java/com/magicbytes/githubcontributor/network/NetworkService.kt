package com.magicbytes.githubcontributor.network

import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("/repos/ruby/ruby/stats/contributors")
    fun allContributions(): Call<List<Contribution>>

    @GET("/users/{userName}")
    fun userInfo(@Path("userName") userName: String): Call<User>
}