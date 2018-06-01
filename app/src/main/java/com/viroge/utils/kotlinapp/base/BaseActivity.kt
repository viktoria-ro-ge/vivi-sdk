package com.viroge.utils.kotlinapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.ka_activity_base.*
import kotlin.reflect.KClass

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        setSupportActionBar(toolbar)
    }

    protected abstract fun getFragmentType(): KClass<out BaseFragment>

    protected abstract fun getContentView(): Int

    protected abstract fun getMenu(): Int

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(getMenu(), menu)
        return true
    }
}