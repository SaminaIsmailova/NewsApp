package com.example.newsapp.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.NewsModel
import com.example.newsapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel:DashboardViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter: NewsAdapter = NewsAdapter(this::onClick)

        binding.rvNews.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getNews().observe(viewLifecycleOwner){
                adapter.submitList(it.data?.results)
            }
        }
    }

    private fun onClick(newsModel: Article) {
        val bundle = Bundle()
        bundle.putString("title",newsModel.title)
        bundle.putString("desc",newsModel.description)
        bundle.putString("img",newsModel.imageUrl)
        bundle.putString("date",newsModel.pubDate)
        bundle.putString("category",newsModel.category.toString())
        bundle.putString("content",newsModel.content)
        bundle.putString("creator",newsModel.creator.toString())
        findNavController().navigate(R.id.ditailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}