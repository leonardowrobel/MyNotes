package com.lw.mynotes.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.repository.FirestoreNoteRepositoryImpl
import com.lw.mynotes.featurenote.data.repository.NotesRepositoryImpl
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import com.lw.mynotes.featurenote.services.AuthenticationService
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
    fun providesNoteRepository(db: MyNotesDatabase): NotesRepository{
        return NotesRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun providesFirebaseNoteRepository(): FirestoreNoteRepository{
        val db = FirebaseFirestore.getInstance()
        return FirestoreNoteRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun providesAuthenticationService(): AuthenticationService {
        return AuthenticationService()
    }
}