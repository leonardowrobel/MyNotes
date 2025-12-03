package com.lw.mynotes.di

import android.app.Application
import androidx.room.Room
import com.lw.mynotes.feature_note.data.data_source.MyNotesDatabase
import com.lw.mynotes.feature_note.data.repository.NoteRepositoryImpl
import com.lw.mynotes.feature_note.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): MyNotesDatabase{
        return Room.databaseBuilder(
            app,
            MyNotesDatabase::class.java,
            MyNotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: MyNotesDatabase): NoteRepository{
        return NoteRepositoryImpl(db.notesDao)
    }
}