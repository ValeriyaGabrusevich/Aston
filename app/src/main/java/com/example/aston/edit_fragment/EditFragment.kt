package com.example.aston.edit_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.aston.R
import com.example.aston.show_fragment.ShowPeopleFragment
import com.example.aston.data.Person
import com.example.aston.databinding.EditFragmentBinding

class EditFragment : Fragment() {
    private var _binding: EditFragmentBinding? = null
    private val binding get() = _binding!!

    private val editViewModel by viewModels<EditViewModel> { EditViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val person = arguments?.getSerializable(PERSON_ARG) as Person
            val position = arguments?.getInt(POSITION_ARG)
            editTextViewPersonName.setText(person.name)
            editTextViewPersonSurname.setText(person.surname)
            editTextViewPersonPhoneNumber.setText(person.phoneNumber)
            Glide.with(requireActivity()).load(person.photo).circleCrop()
                .placeholder(R.drawable.baseline_person_24).into(imageViewPersonPhoto)

            observe()


            btnCheck.setOnClickListener {
                position?.let {
                    editViewModel.onSavePerson(
                        id = person.id,
                        name = editTextViewPersonName.text.toString(),
                        surname = editTextViewPersonSurname.text.toString(),
                        phoneNumber = editTextViewPersonPhoneNumber.text.toString(),
                        photo = person.photo,
                        position = it
                    )
                }

            }

        }
    }

    private fun observe() {
        val sideEffectObserver = Observer<Unit> {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ShowPeopleFragment())
                .addToBackStack(null)
                .commit()
        }

        editViewModel.sideEffectLiveData.observe(this, sideEffectObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PERSON_ARG = "PERSON_ARG"
        const val POSITION_ARG = "POSITION_ARG"

        @JvmStatic
        fun newInstance(person: Person, position: Int) = EditFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PERSON_ARG, person)
                putInt(POSITION_ARG, position)
            }
        }
    }
}