package com.example.a6monthproject.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6monthproject.R
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.databinding.FragmentPlayListBinding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.ui.internet.CheckInternet

class PlayListFragment :  BaseFragment<FragmentPlayListBinding, PlayListViewModel>(){

    private lateinit var adapter : PlaylistAdapter

    override val viewModel: PlayListViewModel by lazy {
        ViewModelProvider(this)[PlayListViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayListBinding {
         return FragmentPlayListBinding.inflate(inflater, container, false)
    }


    override fun initView() {
        adapter = PlaylistAdapter(requireContext(), this::onItemClick)
        binding.rvPlaylist.adapter = adapter
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
                findNavController().navigate(R.id.internerConnectionFragment)

            }
        }
    }
    private fun onItemClick(item: Item){
        findNavController().navigate(R.id.playListDetailsFragment, bundleOf(
            PLAYLIST_KEY to item.id,
            TITLE to item.snippet?.title,
            DESCRIPTION to item.snippet?.channelTitle,
            SERIES to item.contentDetails?.itemCount.toString()))
    }



    override fun initViewModel() {
            viewModel.getPlaylist().observe(viewLifecycleOwner) {
                it.items?.let { it1 -> adapter.addPlayListItems(it1) }
            }

    }
    companion object{
        const val PLAYLIST_KEY = "key.id.playlist"
        const val TITLE = "title"
        const val DESCRIPTION = "des"
        const val SERIES = "series"
    }


}