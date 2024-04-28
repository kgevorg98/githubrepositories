package com.mycomp.githubrepositories.presentation.ui.downloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycomp.githubrepositories.databinding.FragmentDownloadsBinding
import com.mycomp.githubrepositories.presentation.extensions.onEach
import com.mycomp.githubrepositories.presentation.ui.downloads.adapter.DownloadedRepositoriesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DownloadsFragment : Fragment() {
    private var _binding: FragmentDownloadsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DownloadsViewModel by viewModel()

    private lateinit var downloadedRepositoriesAdapter: DownloadedRepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        downloadedRepositoriesAdapter = DownloadedRepositoriesAdapter()
        onEach()
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onEach() {
        onEach(viewModel.getDownloadedRepos) {
            it?.let {
                downloadedRepositoriesAdapter.submitList(it)
            }
        }
    }

    private fun initRecycler() {
        binding.rvDownloads.apply {
            adapter = downloadedRepositoriesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
}