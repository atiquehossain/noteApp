package com.example.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.nexgenScript.noteapp.adapter.NoteAdapter

// This is a Fragment class that represents the home screen of the note app
class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider {

    // View binding property for accessing views in the fragment_home layout
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ViewModel for managing UI-related data
    private lateinit var notesViewModel: NoteViewModel
    // Adapter for the RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    // Inflates the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called after the view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adds this fragment as a MenuProvider to handle menu-related events
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Retrieves the ViewModel from the parent activity
        notesViewModel = (activity as MainActivity).noteViewModel
        // Sets up the RecyclerView
        setupHomeRecyclerView()

        // Sets up the Floating Action Button to navigate to the add note screen
        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
    }

    // Searches for notes based on a query
    private fun searchNote(query: String?) {
        val searchQuery = "%$query%"
        notesViewModel.searchNote(searchQuery).observe(viewLifecycleOwner) { list ->
            noteAdapter.differ.submitList(list)
        }
    }

    // Handles the event when the search query is submitted
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    // Handles the event when the search query text changes
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

    // Clears the binding property when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Creates the menu
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)
        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    // Handles menu item selections
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle menu item clicks if any
        /*return when (menuItem.itemId) {
            R.id.someMenuItem -> {
                // Handle the action for this menu item
                true
            }
            else -> false
        }*/
        return false
    }

    // Updates the UI based on the list of notes
    private fun updateUI(note: List<Note>?) {
        if (note != null && note.isNotEmpty()) {
            binding.emptyNotesImage.visibility = View.GONE
            binding.homeRecyclerView.visibility = View.VISIBLE
        } else {
            binding.emptyNotesImage.visibility = View.VISIBLE
            binding.homeRecyclerView.visibility = View.GONE
        }
    }

    // Sets up the RecyclerView with a StaggeredGridLayoutManager and NoteAdapter
    private fun setupHomeRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        notesViewModel.getAllNotes().observe(viewLifecycleOwner) { note ->
            noteAdapter.differ.submitList(note)
            updateUI(note)
        }
    }
}
