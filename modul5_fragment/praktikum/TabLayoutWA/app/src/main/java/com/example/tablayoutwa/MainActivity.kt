package com.example.tablayoutwa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import com.example.tablayoutwa.databinding.ActivityMainBinding
import com.example.tablayoutwa.fragment.CallFragment
import com.example.tablayoutwa.fragment.ChatFragment
import com.example.tablayoutwa.fragment.KomunitasFragment
import com.example.tablayoutwa.fragment.StatusFragment
import com.example.tablayoutwa.fragment.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpTab()
        binding.more.setOnClickListener{ view ->
            showPopupMenu(view)
        }

    }

    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.inflate(R.menu.menu_more) // Inflate menu resource
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item1 -> {
                    // Aksi saat menu item 1 dipilih
                    true
                }
                R.id.menu_item2 -> {
                    // Aksi saat menu item 2 dipilih
                    true
                }
                // Tambahkan aksi untuk item menu lain sesuai kebutuhan
                else -> false
            }
        }
        popupMenu.show()
    }


    fun setUpTab(){
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(KomunitasFragment(), "")
        fragmentAdapter.addFragment(ChatFragment(), "Chat")
        fragmentAdapter.addFragment(StatusFragment(), "Status")
        fragmentAdapter.addFragment(CallFragment(), "Panggilan")

        binding.viewPager.adapter = fragmentAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.multiple_users_silhouette)

        val layout = (binding.tabs.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams

        layoutParams.weight = 0f
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layout.layoutParams = layoutParams

    }
}