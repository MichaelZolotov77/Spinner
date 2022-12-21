package com.example.lists.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.lists.baseadapter.Character
import com.example.lists.databinding.ItemCharacterBinding

typealias OnDeletePressedListener = (Character) -> Unit

class CharacterAdapter(
    private val characters : List<Character>,
    private val onDeletePressedListener : OnDeletePressedListener
) : BaseAdapter(), View.OnClickListener {

    override fun getItem(position: Int): Character {
        return characters[position]
    }

    override fun getItemId(position: Int): Long {
        return characters[position].id
    }

    override fun getCount(): Int {
        return characters.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       return getDefaultView(position, convertView, parent, isDropdownView = false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getDefaultView(position, convertView, parent, isDropdownView = true)
    }

    private fun getDefaultView(position: Int, convertView: View?, parent: ViewGroup, isDropdownView: Boolean) : View{
        val binging =
            convertView?.tag as ItemCharacterBinding? ?:
            createBinding(parent.context)

        val character = getItem(position)

        binging.titleTextView.text = character.name
        binging.deleteImageView.tag = character
        binging.deleteImageView.visibility = if (isDropdownView) View.VISIBLE else View.GONE
        return binging.root
    }


    override fun onClick(v: View) {
        val character = v.tag as Character
        onDeletePressedListener.invoke(character)
    }

    private fun createBinding(context: Context): ItemCharacterBinding {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(context))
        binding.deleteImageView.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }








}