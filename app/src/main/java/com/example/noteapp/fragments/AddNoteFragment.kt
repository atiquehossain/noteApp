package com.example.noteapp.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.nexgenScript.noteapp.adapter.NoteAdapter

// Fragment class for adding a new note
class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    // View binding property for accessing views in the fragment_add_note layout
    private var addNoteFragment: FragmentAddNoteBinding? = null
    private val binding get() = addNoteFragment!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNoteView: View

    // Inflates the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addNoteFragment = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called after the view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adds this fragment as a MenuProvider to handle menu-related events
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        (requireActivity() as? Activity)?.invalidateOptionsMenu()

        // Retrieves the ViewModel from the parent activity
        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view

        // Sets up the click listener for the save button
        binding.save.setOnClickListener {
            saveNote()
        }
    }

    // Function to save the note
    private fun saveNote() {
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            // Creates a new Note object and adds it to the ViewModel
            val note = Note(0, noteTitle, noteDesc)
            notesViewModel.addNote(note)
            Toast.makeText(addNoteView.context, "Note saved", Toast.LENGTH_SHORT).show()
            // Navigates back to the home fragment after saving the note
            addNoteView.findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        } else {
            // Shows a toast message if the note title is empty
            Toast.makeText(addNoteView.context, "Title cannot be empty", Toast.LENGTH_LONG).show()
        }
    }

    // Creates the menu
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    // Handles menu item selections
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNote()
                true
            }
            else -> false
        }
    }

    // Clears the binding property when the fragment is destroyed
    override fun onDestroy() {
        super.onDestroy()
        addNoteFragment = null
    }
}
