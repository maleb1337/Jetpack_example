package cl.maleb.jetpackexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cl.maleb.jetpackexample.databinding.FragmentHomeBinding
import cl.maleb.jetpackexample.util.deserializeObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val args: HomeFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val homeViewData: HomeViewData? =
            args.homeViewData?.deserializeObject(HomeViewData::class.java)

        homeViewModel._text.postValue(homeViewData?.greet.orEmpty())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.actionButton.setOnClickListener {
            val deepLink =
                "myapp://details".toUri()
            findNavController().navigate(deepLink)

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}