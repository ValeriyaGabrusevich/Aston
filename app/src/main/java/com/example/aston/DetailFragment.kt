package com.example.aston

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.aston.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val person = arguments?.getSerializable(EditFragment.PERSON_ARG) as Person
            val position = arguments?.getInt(EditFragment.POSITION_ARG)
            textViewPersonName.text = person.name
            textViewPersonSurname.text = person.surname
            textViewPersonPhoneNumber.text = person.phoneNumber
            Glide.with(requireActivity()).load(person.photo).circleCrop()
                .placeholder(R.drawable.baseline_person_24).into(imageViewPersonPhoto)

            btnEdit.setOnClickListener {
                position?.let {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, EditFragment.newInstance(person, it))
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PERSON_ARG = "PERSON_ARG"
        const val POSITION_ARG = "POSITION_ARG"

        @JvmStatic
        fun newInstance(person: Person, position: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PERSON_ARG, person)
                putInt(POSITION_ARG, position)
            }
        }
    }
}