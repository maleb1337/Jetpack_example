package cl.maleb.jetpackexample.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import cl.maleb.jetpackexample.R
import cl.maleb.jetpackexample.databinding.FragmentNotificationsBinding
import cl.maleb.jetpackexample.ui.home.HomeViewData
import cl.maleb.jetpackexample.util.ARG_HOME_VIEW_DATA

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textNotifications.setOnClickListener {
            val test = HomeViewData(name = "test", "hola")
            val deepLink = "myapp://home/$test".toUri()


            val deepLinkRequest = NavDeepLinkRequest.Builder
                .fromUri("myapp://home/".toUri())
                .setAction("android.intent.action.MY_ACTION")
                .setMimeType("type/subtype")
                .build()

//            findNavController().previousBackStackEntry?.arguments?.putParcelable(
//                "homeViewData", HomeViewData(name = "test", "hola")
//            )
            val args = Bundle()
            args.putParcelable(ARG_HOME_VIEW_DATA, HomeViewData(name = "test", "hola"))
            val deepLinkExplicit = NavDeepLinkBuilder(requireContext())
                .setGraph(R.navigation.main_nav_notification)
                .setDestination(R.id.homeFragment)
                .setArguments(args)
                .createPendingIntent()

//            deepLinkExplicit.send()

//            val action = HomeFragmentDirections.actionHomeFragmentSelf(
//                homeViewData = HomeViewData(name = "test", "hola")
//            )

//            val action2 = NotificationsFragmentDirections.actionHomeFragmentSelf()

            findNavController().navigate(deepLink)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}