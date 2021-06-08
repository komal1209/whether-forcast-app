package com.example.weatherforecastingapplication.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.example.weatherforecastingapplication.R
import com.example.weatherforecastingapplication.adapter.WeatherViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : AppCompatActivity() {

    companion object {
        @kotlin.jvm.JvmField
        val TAG: String = WeatherActivity::class.java.simpleName
    }

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolBar();
        initViews()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            createPermissions()
        }
        setStatePageAdapter()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener(View.OnClickListener { view: View? -> onBackPressed() })
        }
    }

    private fun initViews() {
        viewPager = findViewById<ViewPager>(R.id.viewPager)
        tabLayout = findViewById<TabLayout>(R.id.tabs)
    }

    private fun createPermissions() {
        try {
            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        token?.continuePermissionRequest()
                    }
                }).check()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setStatePageAdapter() {
        val viewPageAdapter = WeatherViewPagerAdapter(supportFragmentManager)
        viewPageAdapter.addFragment(CurrentFragment(), getString(R.string.current))
        viewPageAdapter.addFragment(HistoryFragment(), getString(R.string.history))
        viewPager.adapter = viewPageAdapter
        tabLayout.setupWithViewPager(viewPager, true)
    }
}
