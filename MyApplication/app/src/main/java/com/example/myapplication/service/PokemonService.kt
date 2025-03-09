import com.example.myapplication.service.PokemonServiceInterface

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonService{
    var retrofit: PokemonServiceInterface = Retrofit.Builder()
        .baseUrl("https://tyradex.vercel.app/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonServiceInterface::class.java)
}
