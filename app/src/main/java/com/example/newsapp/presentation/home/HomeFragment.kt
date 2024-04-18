package com.example.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.dashboard.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter: NewsEntityAdapter = NewsEntityAdapter(this::onClick)

        binding.rv.adapter = adapter
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAll().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

    }

    private fun onClick(newsModel: NewsEntity) {
        val bundle = Bundle()
        bundle.putBoolean("home", false)
        bundle.putString("title", newsModel.title)
        bundle.putString("desc", newsModel.description)
        bundle.putString("img", newsModel.imageUrl)
        bundle.putString("date", newsModel.pubDate)
        bundle.putString("content", newsModel.keyword)
        bundle.putString("category", newsModel.category.toString())
        bundle.putString("creator", newsModel.creator.toString())
        findNavController().navigate(R.id.ditailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}