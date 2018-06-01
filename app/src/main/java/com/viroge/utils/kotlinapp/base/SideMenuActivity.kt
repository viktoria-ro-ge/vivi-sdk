package com.viroge.utils.kotlinapp.base

import com.viroge.utils.R

abstract class SideMenuActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.ka_activity_sidemenu
    }

    override fun getMenu(): Int {
        return R.menu.ka_menu
    }
}
