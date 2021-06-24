package cl.maleb.jetpackexample.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.maleb.jetpackexample.databinding.FragmentNotificationsDetailBinding
import cl.maleb.jetpackexample.ui.home.HomeViewData
import cl.maleb.jetpackexample.ui.notifications.viewpager.ViewPagerAdapter
import cl.maleb.jetpackexample.util.ARG_HOME_VIEW_DATA


class NotificationsDetailFragment : Fragment() {

    private var _binding: FragmentNotificationsDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = adapter

        Toast.makeText(requireContext(), "", Toast.LENGTH_LONG).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}