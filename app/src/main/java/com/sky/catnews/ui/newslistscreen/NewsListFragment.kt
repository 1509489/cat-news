package com.sky.catnews.ui.newslistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sky.catnews.databinding.FragmentNewsListBinding
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.ui.adapters.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNewsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeNewsList() {
        viewModel.fetchNews().observe(viewLifecycleOwner, Observer { networkResource ->
            when (networkResource) {
                is NetworkResource.Loading -> binding.apply {
                    incError.llError.visibility = View.GONE
                    rvNewsList.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                }
                is NetworkResource.Success -> networkResource.data?.let { newsDto ->
                    val newsListAdapter = NewsListAdapter()

                    binding.apply {
                        tvTitle.text = newsDto.title
                        binding.rvNewsList.visibility = View.VISIBLE
                        binding.pbLoading.visibility = View.GONE
                        rvNewsList.adapter = newsListAdapter
                    }
                    newsListAdapter.apply {
                        submitList(newsDto.newsData)
                        itemViewClickListener = { data ->
                            val destination = NewsListFragmentDirections
                                .actionNewsListFragmentToNewsStoryFragment(data.id)
                            findNavController().navigate(destination)
                        }
                    }
                }
                is NetworkResource.Error -> binding.apply {
                    rvNewsList.visibility = View.GONE
                    pbLoading.visibility = View.GONE
                    incError.apply {
                        llError.visibility = View.VISIBLE
                        btnTryAgain.setOnClickListener { observeNewsList() }
                    }
                }
            }
        })
    }
}
