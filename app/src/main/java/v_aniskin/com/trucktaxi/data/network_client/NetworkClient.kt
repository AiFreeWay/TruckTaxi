package v_aniskin.com.trucktaxi.data.network_client

import v_aniskin.com.trucktaxi.data.network_client.responses.ApiController
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import v_aniskin.com.trucktaxi.application.utils.Logger


/**
 * Created by root on 25.06.17.
 */

class NetworkClient {

    private val API_URL = "http://test.com"

    private var mApiController: ApiController

    init {
        Logger.testLog("NetworkClient Create")
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        mApiController = retrofit.create(ApiController::class.java)
    }
}