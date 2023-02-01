package com.example.movielister.person

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.databinding.ActivityPersonDetailsBinding
import com.example.movielister.model.PersonModel
import com.squareup.picasso.Picasso

class PersonDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailsBinding

    private val personDetailsViewModel by lazy { ViewModelProvider(this)[PersonDetailsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonDetailsBinding.inflate(layoutInflater)

        val intent = intent
        val person = intent.getSerializableExtra("person") as PersonModel

        person.id?.let {
            personDetailsViewModel.fetchPerson(it)
            personDetailsViewModel.fetchPersonImage(it)
        }

        bindPersonDetails(person)
        bindPersonImage()

        setContentView(binding.root)
    }

    private fun bindPersonDetails(person: PersonModel) {
        binding.personName.text = person.name
        binding.bioText.text = person.biography
    }

    private fun bindPersonImage() {
        lifecycleScope.launchWhenStarted {
            personDetailsViewModel.currentImage.collect { personImagePath ->
                Picasso.get().load(personImagePath.imageUrl + personImagePath).into(binding.personImage)
            }
        }
    }
}