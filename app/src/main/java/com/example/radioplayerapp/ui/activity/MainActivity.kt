package com.example.radioplayerapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.radioplayerapp.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this) {}

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("33BE2250B43518CCDA7DE426D04EE231"))
                .build()
        )

        startAgain()


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                    R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }//onCreate


    //Load an ad
    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, AD_UNIT_ID, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    mInterstitialAd = null
                    mAdIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    Toast.makeText(
                        this@MainActivity,
                        "onAdFailedToLoad() with error $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    //Show the ad
                    showInterstitial()
                    Toast.makeText(this@MainActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
                }
            })
    }//loadAd

    // Show the ad if it's ready. Otherwise toast
    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            //Set the FullScreenContentCallback
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                  //  loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d(TAG, "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(this)
        } else {
            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            //startAgain()
        }
    }//showInterstitial

    // Request a new ad if one isn't already loaded.
    private fun startAgain() {
        if (!mAdIsLoading && mInterstitialAd == null) {
            mAdIsLoading = true
            loadAd()
        }
    }
    companion object {
        const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    }
}