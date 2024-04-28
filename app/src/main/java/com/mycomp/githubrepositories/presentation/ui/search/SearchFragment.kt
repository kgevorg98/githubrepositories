package com.mycomp.githubrepositories.presentation.ui.search

import android.app.DownloadManager
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mycomp.githubrepositories.R
import com.mycomp.githubrepositories.databinding.FragmentSearchBinding
import com.mycomp.githubrepositories.presentation.extensions.isNetWorkAvailable
import com.mycomp.githubrepositories.presentation.ui.search.adapter.ReposAdapter
import com.mycomp.githubrepositories.presentation.utils.DownloadReceiver
import com.mycomp.githubrepositories.presentation.utils.ZipDownloader
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), DownloadReceiver.DownloadCallback {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var reposAdapter: ReposAdapter
    private lateinit var downloadReceiver: DownloadReceiver
    private lateinit var zipDownloader: ZipDownloader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        zipDownloader = ZipDownloader(requireContext())
        reposAdapter = ReposAdapter(onDownloadClickListener = { item ->
            zipDownloader.download(item.fullName)
            viewModel.addToDownloads(item)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        downloadReceiver = DownloadReceiver()
        downloadReceiver.registerCallback(this)
        initMenu(menuHost)
        setEditTextListener()
        addAdapterStateListener()
        initRecycler()
    }

    private fun addAdapterStateListener() {
        reposAdapter.addLoadStateListener { loadState ->

            //loading state
            val isLoading = loadState.source.refresh is LoadState.Loading
            binding.progress.isVisible = isLoading

            // Empty state
            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && reposAdapter.itemCount == 0
            binding.emptyView.isVisible = isEmptyList
        }
    }

    private fun setEditTextListener() {
        binding.search.editText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (isNetWorkAvailable()) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getGithubReposByUserName(userName = v.text.toString())
                            .collectLatest {
                                reposAdapter.submitData(it)
                            }
                    }
                } else {
                    makeToast(R.string.no_network_connection)
                }
                true
            }
            false
        }
    }


    private fun initMenu(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.download -> {
                        val action =
                            SearchFragmentDirections.actionSearchFragmentToDownloadsFragment()
                        findNavController().navigate(action)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.CREATED)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        requireActivity().registerReceiver(downloadReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(downloadReceiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initRecycler() {
        binding.rvRepos.apply {
            adapter = reposAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDownloadComplete() {
        makeToast(R.string.successful_download)
    }

    override fun onFailDownload() {
        makeToast(R.string.failed_download)
    }

    private fun makeToast(@StringRes stringId: Int) {
        Toast.makeText(requireContext(), stringId, Toast.LENGTH_SHORT).show()
    }
}