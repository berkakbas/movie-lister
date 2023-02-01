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
        val person = intent.getSerializableExtra("person") as PersonModel?
        val personId = intent.getSerializableExtra("person_id") as Int?

        person?.let {
            personDetailsViewModel.fetchPerson(it.id!!)
            personDetailsViewModel.fetchPersonImage(it.id)
            bindPersonDetails(person)
        }
        personId?.let {
            personDetailsViewModel.fetchPerson(it)
            bindPersonFromNetwork()
            personDetailsViewModel.fetchPersonImage(it)
        }

        bindPersonImage()

        setContentView(binding.root)
    }

    private fun bindPersonDetails(person: PersonModel) {
        binding.personName.text = person.name
        binding.bioText.text = person.biography
    }

    private fun bindPersonFromNetwork() {
        lifecycleScope.launchWhenStarted {
            personDetailsViewModel.currentPerson.collect { person ->
                binding.personName.text = person.name
                binding.bioText.text = person.biography
            }
        }
    }

    private fun bindPersonImage() {
        lifecycleScope.launchWhenStarted {
            personDetailsViewModel.currentImage.collect { personImageModel ->
                Picasso.get().load(personImageModel.imageUrl + personImageModel.filePath).into(binding.personImage)
            }
        }
    }
}