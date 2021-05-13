package com.sky.catnews.ui.storyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sky.catnews.R
import com.sky.catnews.databinding.FragmentStoryBinding
import com.sky.catnews.dto.NewsStoryDto
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.ui.adapters.NewsStoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryFragment : Fragment() {

    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!
    private val args: StoryFragmentArgs by navArgs()
    private val viewModel: StoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeStory() {
        viewModel.fetchStory(args.storyId).observe(viewLifecycleOwner, Observer { networkResource ->
            when (networkResource) {
                is NetworkResource.Loading -> binding.apply {
                    incError.llError.visibility = View.GONE
                    rvStoryContent.visibility = View.GONE
                    incHero.clHero.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                }
                is NetworkResource.Success -> networkResource.data?.let { storyDto ->
                    setupHero(storyDto)
                    val storyAdapter = NewsStoryAdapter()
                    storyAdapter.submitList(storyDto.contents)
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        rvStoryContent.visibility = View.VISIBLE
                        incHero.clHero.visibility = View.VISIBLE
                        rvStoryContent.adapter = storyAdapter
                    }
                }
                is NetworkResource.Error -> binding.apply {
                    rvStoryContent.visibility = View.GONE
                    incHero.clHero.visibility = View.GONE
                    pbLoading.visibility = View.GONE
                    incError.llError.visibility = View.VISIBLE
                    incError.btnTryAgain.setOnClickListener { observeStory() }
                }
            }
        })
    }

    private fun setupHero(storyDto: NewsStoryDto) {
        binding.incHero.tvHeroTitle.text = storyDto.headline
        storyDto.heroImage.apply {
            Glide.with(requireContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.nyan_cat)
                .into(binding.incHero.ivHero)
            binding.incHero.tvHeroTitle.contentDescription = accessibilityText
        }
    }
}
