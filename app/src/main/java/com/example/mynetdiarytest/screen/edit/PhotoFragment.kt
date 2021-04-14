package com.example.mynetdiarytest.screen.edit

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.BuildConfig
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseFragment
import com.example.mynetdiarytest.screen.injectViewModel
import com.example.mynetdiarytest.utils.loadImage
import kotlinx.android.synthetic.main.f_photo.*
import java.io.File
import javax.inject.Inject

class PhotoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<EditViewModel>(viewModelFactory) }

    private var imageURI: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getPhoto()?.let {
            photoImageView.loadImage(it)
        }

        takePhotoButton.setOnClickListener {
            openCamera()
        }

        choosePhotoButton.setOnClickListener {
            openGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                imageURI?.let {
                    viewModel.setPhoto(it)
                    photoImageView.loadImage(it)
                }
            }
            REQUEST_IMAGE_GALLERY -> {
                data?.data?.let {
                    it.let {
                        viewModel.setPhoto(it)
                        photoImageView.loadImage(it)
                    }
                }
            }
        }
    }

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    private fun openCamera() {
        if (arePermissionsGranted(requireContext(), CAMERA_PERMISSION)) {
            imageURI = FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".fileprovider",
                File(requireContext().externalCacheDir, "pickImageResult.jpeg")
            )
            imageURI?.let {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        } else {
            requestPermissions(CAMERA_PERMISSION, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openGallery() {
        Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).also { openGalleryIntent ->
            startActivityForResult(openGalleryIntent, REQUEST_IMAGE_GALLERY)
        }
    }

    private fun arePermissionsGranted(
        context: Context,
        permissions: Array<String>
    ): Boolean {
        if (permissions.isEmpty()) return true

        return permissions.none {
            ActivityCompat.checkSelfPermission(
                context,
                it
            ) != PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 101
        private const val REQUEST_IMAGE_GALLERY = 102

        private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)

        fun getInstance(): PhotoFragment = PhotoFragment()
    }
}