package com.example.musicplayerapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayerapp.MusicApp
import com.example.musicplayerapp.R
import com.example.musicplayerapp.adapter.MusicAdapter
import com.example.musicplayerapp.databinding.FragmentClassicBinding
import com.example.musicplayerapp.databinding.FragmentPopBinding
import com.example.musicplayerapp.model.MusicItem
import com.example.musicplayerapp.presenters.ClassicMusicsPresenter
import com.example.musicplayerapp.presenters.MusicViewContract
import com.example.musicplayerapp.presenters.PopMusicsPresenter
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopFragment : Fragment(), MusicViewContract {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    @Inject
    lateinit var popPresenter: PopMusicsPresenter

    private val binding by lazy {
        FragmentPopBinding.inflate(layoutInflater)
    }

    private val musicAdapter by lazy {
        MusicAdapter()


    }

//    private val classicPresenter: ClassicMusicsPresenter by lazy {
//        ClassicMusicPresenter(requireContext(), this, repository,)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        MusicApp.musicComponent.inject(this)

        popPresenter.initializePresenter(this)

        binding.popRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        popPresenter.checkNetworkConnection()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        popPresenter.getPopMusics()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        popPresenter.destroyPresenter()
    }

    override fun loadingMusics(isLoading: Boolean) {
        // binding.flowerRecycler.visibility = View.GONE
        // binding.progressBar.visibility = View.VISIBLE
    }

    override fun musicsSuccess(musics: List<MusicItem>) {
//        binding.progressBar.visibility = View.GONE
//        binding.flowerRecycler.visibility = View.VISIBLE

        Log.d("network","this is musicsSuccess")
        musicAdapter.updateMusics(musics)
    }

    override fun onError(throwable: Throwable) {
        //binding.flowerRecycler.visibility = View.GONE
        //binding.progressBar.visibility = View.GONE

        AlertDialog.Builder(requireContext())
            .setTitle("AN ERROR HAS OCCURRED")
            .setMessage(throwable.localizedMessage)
            .setPositiveButton("DISMISS") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlowersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}