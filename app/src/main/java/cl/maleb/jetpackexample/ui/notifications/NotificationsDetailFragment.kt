package cl.maleb.jetpackexample.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.maleb.jetpackexample.databinding.FragmentNotificationsDetailBinding
import cl.maleb.jetpackexample.ui.home.HomeViewData


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
        binding.textViewClickable.setOnClickListener {
            /**
             * deeplink implicit
             */
            val deepLink = "myapp://home/".toUri()
            /**
             * deeplink explicit
             */
//            val args = Bundle()
//            args.putString("mystring", "Argument Value")
//            val deepLink = NavDeepLinkBuilder(requireContext())
//                .setGraph(R.navigation.nav_graph_home)
//                .setDestination(R.id.homeFragment)
//                .setArguments(args)
//                .createPendingIntent()

            findNavController().navigate(deepLink)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}