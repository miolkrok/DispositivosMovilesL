package com.example.primera_view.animiolknova.ui.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.primera_view.R
import com.example.primera_view.animiolknova.ui.activities.AniMiolkNovaAnimesActivity
import com.example.primera_view.databinding.FragmentSearchBinding
import com.example.primera_view.logic.jikanLogic.JikanAnimeLogic
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    var selectImgUri = Uri.parse("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
        adicional()
    }

    private fun adicional(){
        binding.btnSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/jpeg"
            }
            startActivityForResult(
                Intent.createChooser(intent, "Agregar una imagen"), SELECT_IMAGE_REQUEST_CODE
            )

        }
        binding.btnSend.setOnClickListener {
            binding.cpCarga.visibility = View.VISIBLE
            lifecycleScope.launch() {
                var tmpBusqueda = JikanAnimeLogic().getAnimeWithImg(
                    File(
                        getRealPathFromURI(selectImgUri)
                    )
                )
                var intent = Intent(activity?.baseContext, AniMiolkNovaAnimesActivity::class.java)
                val json = Gson().toJson(tmpBusqueda)
                intent.putExtra("listaDatos", json)
                startActivity(intent)
                binding.cpCarga.visibility = View.GONE
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            selectImgUri = data.data
            try {
                val selectedImageBitmap =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectImgUri)
                binding.ivSelectedImage.setImageBitmap(
                    selectedImageBitmap
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getRealPathFromURI(uri: Uri): String? {
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            cursor.use {
                it.moveToFirst()
                val idx = it.getColumnIndex(MediaStore.Images.Media.DATA)
                it.getString(idx)
            }
        }
    }


    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val SELECT_IMAGE_REQUEST_CODE = 1
        private const val PERMISSION_REQUEST_CODE = 100
    }
}