package com.sky.catnews.ui.newslistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sky.catnews.databinding.FragmentNewsListBinding
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.ui.adapters.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsListAdapter = NewsListAdapter()
        observeNewsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeNewsList() {
        viewModel.fetchNews().observe(viewLifecycleOwner, Observer { networkResource ->
            when (networkResource) {
                is NetworkResource.Loading -> {
                }
                is NetworkResource.Success -> networkResource.data?.let { newsDto ->
                    newsListAdapter.apply {
                        submitList(newsDto.newsData)
                        binding.rvNewsList.adapter = this
                        itemViewClickListener = { data ->
                            Toast.makeText(requireContext(), data.headline, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                is NetworkResource.Error -> {

                }
            }
        })
    }
}
