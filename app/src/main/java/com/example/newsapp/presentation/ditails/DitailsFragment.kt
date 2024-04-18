package com.example.newsapp.presentation.ditails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.newsapp.R
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentDitailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DitailsFragment : Fragment() {

    private lateinit var binding: FragmentDitailsBinding
    private val viewModel: DitailsViewModel by viewModels()
    private lateinit var newsEntity: NewsEntity
    private var fromDash: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDitailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val fromDash = arguments?.getBoolean("home")
            val img = arguments?.getString("img") ?: "There`s no image"
            val keyword = arguments?.getString("content") ?: " "
            val category = arguments?.getString("category") ?: " "
            val creator = arguments?.getString("creator") ?: " "
            val desc = arguments?.getString("desc") ?: " "

            newsEntity = NewsEntity(
                title = arguments?.getString("title")!!,
                description = desc,
                pubDate = arguments?.getString("date")!!,
                imageUrl = img,
                category = category,
                creator = creator,
                keyword = keyword
            )
            bind(newsEntity)
        }

        binding.imgSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val savedEntity = NewsEntity(
                    title = newsEntity.title,
                    description = newsEntity.description,
                    pubDate = newsEntity.pubDate,
                    imageUrl = newsEntity.imageUrl,
                    category = newsEntity.category,
                    creator = newsEntity.creator,
                    keyword = newsEntity.keyword,
                    saved = fromDash
                )
                viewModel.insert(newsEntity = savedEntity)
                withContext(Dispatchers.Main) {
                    binding.imgSave.isVisible = fromDash == true
                    binding.imgSave.setBackgroundResource(R.drawable.ic_saved)
                }
            }
        }

    }

    fun bind(newsEntity: NewsEntity) {
        binding.run {
            tvTitle.text = newsEntity.title
            tvDesc.text = newsEntity.description
            tvCreator.text = newsEntity.creator
            tvDateOfRelease.text = newsEntity.pubDate
            tvKeyword.text = newsEntity.keyword
            tvCategory.text = newsEntity.category
        }
    }
}