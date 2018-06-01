package com.viroge.utils.kotlinapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.g_toolbar.*
import kotlinx.android.synthetic.main.ka_activity_sidemenu.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        setSupportActionBar(toolbar)

        // Add content
        layoutInflater.inflate(getMainContainer(), content);
    }

    protected abstract fun getMainContainer(): Int

    protected abstract fun getContentView(): Int

    protected abstract fun getMenu(): Int

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(getMenu(), menu)
        return true
    }
}