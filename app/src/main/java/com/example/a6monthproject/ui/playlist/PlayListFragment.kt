package com.example.a6monthproject.ui.playlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6monthproject.R
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.databinding.FragmentPlayListBinding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.ui.internet.CheckInternet

class PlayListFragment :  BaseFragment<FragmentPlayListBinding, PlayListViewModel>(){
    override val viewModel: PlayListViewModel by lazy {
        ViewModelProvider(this)[PlayListViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayListBinding {
         return FragmentPlayListBinding.inflate(inflater, container, false)
    }
    private lateinit var adapter : AdapterPlaylist
   private var list: ArrayList<Item> = ArrayList()



    override fun initView() {
        adapter = AdapterPlaylist(requireContext(), list)
        binding.rvPlaylist.adapter = adapter
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
                findNavController().navigate(R.id.internerConnectionFragment)

            }
        }
    }



    override fun initViewModel() {
            viewModel.getPlaylist(list, adapter).observe(viewLifecycleOwner) {
                Log.d("ololo", "in fragment $it")
            }

    }


}