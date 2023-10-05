package com.example.aston.show_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston.DetailFragment
import com.example.aston.R
import com.example.aston.data.Person
import com.example.aston.databinding.RecyclerFragmentBinding

class ShowPeopleFragment : Fragment() {
    private var _binding: RecyclerFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShowPeopleViewModel by viewModels { ShowPeopleViewModel.Factory }
    private lateinit var adapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PersonAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        initViews()
        observe()
    }

    private fun initViews() {
        val manager = LinearLayoutManager(requireContext())
        adapter = PersonAdapter(viewModel)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        val nameObserver = Observer<List<Person>> { items ->
            adapter.setItems(items)
        }
        val sideEffectObserver = Observer<Pair<Person, Int>> { pair ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainerView,
                    DetailFragment.newInstance(pair.first, pair.second)
                )
                .addToBackStack(null)
                .commit()
        }
        viewModel.peopleLiveData.observe(this, nameObserver)
        viewModel.sideEffectLiveData.observe(this, sideEffectObserver)
    }
}