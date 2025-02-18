package ru.myitschool.work.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.myitschool.work.R
import ru.myitschool.work.databinding.ActivityRootBinding

// НЕ ИЗМЕНЯЙТЕ НАЗВАНИЕ КЛАССА!
@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val windowInsetsController = window.insetsController
        windowInsetsController?.hide(android.view.WindowInsets.Type.statusBars() or android.view.WindowInsets.Type.navigationBars())

    }
}