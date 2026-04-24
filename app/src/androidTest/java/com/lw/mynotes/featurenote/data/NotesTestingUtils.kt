package com.lw.mynotes.featurenote.data

import com.lw.mynotes.featurenote.data.model.NoteEntity

class NotesTestingUtils {

    private val titleSamples = listOf<String>(
        "Learn about Polymer",
        "Watch Pluralsight course on Docker",
        "",
        "Complete presentation prep for Aurelia presentation",
        "Instrument creation of development environment with Puppet",
        "",
        "Transition code base to ES6",
        "Sort your master list into categories",
        "Pick up clothes from the laundromat",
        "",
        "Get ingredients for dinner tonight",
        "Find a birthday gift for Amy",
        "",
        "Drop off the package at the post office",
        "Identical twins don’t have the same fingerprints."
    )

    // TODO: Add lists
    private val contentSamples = listOf<String>(
        "Writing absolutely everything down, you will be getting it off your mind and onto paper. It will ensure you don't forget anything, and hopefully, it will also help your mind feel less cramped.",
        "The running list of every single task you have on your radar will be referred to as your master list.",
        "To be your most productive, you need to have tunnel vision on the tasks in front of you. Remove the background noise and the stress of future chores by making situation-specific lists.",
        "",
        "Draw a star next to the most important items, and rewrite the list in order of priority.",
        "Give yourself a cushion of 10 to 15 minutes between each task.",
        "By now, your checklist is probably looking pretty long. If you have any tasks that you can save for another day, write them on a separate list. ",
        "",
        "A cloud weighs around a million tonnes. A cloud typically has a volume of around 1km3 and a density of around 1.003kg per m3 – that's a density that’s around 0.4 per cent lower than the air surrounding it (this is how they are able to float).",
        "Earth’s rotation is changing speed. It's actually slowing. This means that, on average, the length of a day increases by around 1.8 seconds per century. 600 million years ago a day lasted just 21 hours.",
        "Parents in the US consistently rank as the world's unhappiest. Happiness and wellbeing surveys are far from an exact science, but parents in the US report being far less happy than non-parents. In contrast, Portuguese parents seem to be slightly happier than non-parents.",
        "Earlobes have no biological purpose. While they are rich in nerve endings and may play a role in social bonding, many scientists argue that earlobes don’t have any true biological purpose.",
        "When you're reading – even silently – the muscles of your mouth, tongue and larynx activate.",
        "The largest piece of fossilised dinosaur poo discovered is over 30cm long and over two litres in volume.",
        "There’s no such thing as zero-calorie foods.",
        "Water might not be wet.",
        ""
    )

    fun createNoteEntity(): NoteEntity {
        return NoteEntity(
            title = titleSamples[(0.. (titleSamples.size - 1)).random()],
            content = contentSamples[(0.. (contentSamples.size - 1)).random()]
        )
    }

    fun createNoteEntities(qtd: Int = 1): List<NoteEntity> {
        val notes = mutableListOf<NoteEntity>()
        for (i in 1..qtd){
            notes.add(createNoteEntity())
        }
        return notes
    }
}