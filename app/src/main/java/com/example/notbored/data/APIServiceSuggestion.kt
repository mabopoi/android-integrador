package com.example.notbored.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceSuggestion {
    @GET("/api/activity")
    suspend fun getSuggestion(
        @Query("participants") participants: String? = null,
        @Query("type") type: String? = null
    ): Response<SuggestionResponse>
}