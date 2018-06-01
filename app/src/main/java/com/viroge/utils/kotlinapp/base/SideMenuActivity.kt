package com.viroge.utils.kotlinapp.base

import com.viroge.utils.examples.R

abstract class SideMenuActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.ka_activity_base
    }

    override fun getMenu(): Int {
        return R.menu.menu_main
    }
}
