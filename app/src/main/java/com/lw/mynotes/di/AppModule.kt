package com.lw.mynotes.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.lw.mynotes.BuildConfig
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.repository.FirestoreNoteRepositoryImpl
import com.lw.mynotes.featurenote.data.repository.NoteRepositoryImpl
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
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
    fun providesNoteRepository(db: MyNotesDatabase): NoteRepository{
        return NoteRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun providesFirebaseNoteRepository(): FirestoreNoteRepository{
        val db = FirebaseFirestore.getInstance()
//        val buildType = BuildConfig.BUILD_TYPE
//        if(buildType == "development"){
//            db.useEmulator("10.0.0.1", 8080)
//        }
        return FirestoreNoteRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun providesAuthenticationService(): AuthenticationService {
        return AuthenticationService()
    }
}