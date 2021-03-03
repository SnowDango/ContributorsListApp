package com.snowdango.yumemicodetest.data.netwrok

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.snowdango.yumemicodetest.BuildConfig
import com.snowdango.yumemicodetest.data.netwrok.githubapi.ContributorsApi
import com.snowdango.yumemicodetest.data.netwrok.githubapi.UserInfoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    val contributorsApi: ContributorsApi by lazy { provideContributorsApi().create(ContributorsApi::class.java) }
    val userInfoApi: UserInfoApi by lazy { provideContributorsApi().create(UserInfoApi::class.java) }

    private fun provideContributorsApi() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_GITHUB_BASE)
        .client(provideOkHttpClient(provideLoggingInterceptor()))
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        )).build()

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

}