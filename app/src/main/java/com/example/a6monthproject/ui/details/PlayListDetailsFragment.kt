package com.example.a6monthproject.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6monthproject.R
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.databinding.FragmentPlayList2Binding
import com.example.a6monthproject.databinding.PlaylistItemBinding
import com.example.a6monthproject.ui.playlist.PlayListFragment


class PlayListDetailsFragment : BaseFragment<FragmentPlayList2Binding, PlayListDetailsViewModel>() {
    private lateinit var adapter : PlayListDetailsAdapter

    override val viewModel: PlayListDetailsViewModel by lazy {
        ViewModelProvider(this)[PlayListDetailsViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayList2Binding {
        return FragmentPlayList2Binding.inflate(inflater, container, false)
    }

    override fun initViewModel() {
        val id = arguments?.getString(PlayListFragment.PLAYLIST_KEY)
        val title = arguments?.getString(PlayListFragment.TITLE)
        val des = arguments?.getString(PlayListFragment.DESCRIPTION)
        val series = arguments?.getString(PlayListFragment.SERIES)
        viewModel.getPlayListDetails(id.toString()).observe(viewLifecycleOwner){
            it.items?.let { it1 -> adapter.addPlayListItems(it1) }
            binding.coorTitle.text = title
            binding.coorDes.text = des
            binding.tvSeries.text = requireContext().getString(R.string.series_count, series)
        }


    }

    override fun initView() {

        adapter = PlayListDetailsAdapter()
        binding.rvDetails.adapter = adapter

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}