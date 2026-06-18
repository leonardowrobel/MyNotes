package com.lw.mynotes.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.lw.mynotes.BuildConfig
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.repository.FirestoreNotesRepositoryImpl
import com.lw.mynotes.featurenote.data.repository.NotesRepositoryImpl
import com.lw.mynotes.featurenote.domain.repository.FirestoreNotesRepository
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import com.lw.mynotes.featurenote.services.AuthenticationService
import com.lw.mynotes.featurenote.services.SynchronizationService
import com.lw.mynotes.featurenote.services.network.ConnectivityRepository
import com.lw.mynotes.featurenote.services.network.NetworkConnectivityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // APP DB --------------------------------------------------------------------------------------
    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): MyNotesDatabase{
        return Room.databaseBuilder(
            app,
            MyNotesDatabase::class.java,
            MyNotesDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(true).build() // TODO: check this
    }

    // Repositories --------------------------------------------------------------------------------
    @Provides
    @Singleton
    fun providesNoteRepository(db: MyNotesDatabase): NotesRepository {
        return NotesRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun providesFirebaseNoteRepository(): FirestoreNotesRepository {
        val firestore = Firebase.firestore
        // TODO: Find out programmatically build config
        val localIp = BuildConfig.LOCAL_IP
        val localFirestorePort = BuildConfig.LOCAL_FIRESTORE_PORT
        firestore.useEmulator(localIp, localFirestorePort.toInt())

        val db = FirebaseFirestore.getInstance()
        return FirestoreNotesRepositoryImpl(db)
    }

    // Services ------------------------------------------------------------------------------------
    @Provides
    @Singleton
    fun providesNetworkConnectivityService(
        connectivityRepository: ConnectivityRepository,
        scope: CoroutineScope
    ): NetworkConnectivityService {
        return NetworkConnectivityService(scope, connectivityRepository)
    }

    @Provides
    @Singleton
    fun providesAuthenticationService(): AuthenticationService {
        return AuthenticationService()
    }

    @Provides
    @Singleton
    fun providesSynchronizationService(
        firestoreNotesRepository: FirestoreNotesRepository,
        notesRepository: NotesRepository,
        authenticationService: AuthenticationService,
        scope: CoroutineScope
    ): SynchronizationService {
        return SynchronizationService(
            firestoreNotesRepository,
            notesRepository,
            authenticationService,
            scope
        )
    }

    // Other ---------------------------------------------------------------------------------------
    @Provides
    @Singleton
    fun providesConnectivityObserver(@ApplicationContext appContext: Context): ConnectivityRepository {
        return ConnectivityRepository(appContext)
    }

    // TODO: review this and assure this is the best context
    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}