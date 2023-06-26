package com.example.primera_view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primera_view.R
import com.example.primera_view.databinding.FragmentFirstBinding
import com.example.primera_view.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentThird.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentThird : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstancesState: Bundle?): View? {

        binding = FragmentThirdBinding.inflate(layoutInflater,
            container,false)

        return binding.root
    }
}