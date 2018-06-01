package com.viroge.utils.kotlinapp.home

import android.os.Bundle
import com.viroge.utils.kotlinapp.base.BaseFragment
import com.viroge.utils.kotlinapp.base.SideMenuActivity
import kotlin.reflect.KClass

class HomeActivity : SideMenuActivity() {

    override fun getFragmentType(): KClass<out BaseFragment> {
        return HomeFragment::class
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}