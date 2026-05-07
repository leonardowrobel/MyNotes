package com.lw.mynotes.featurenote.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SyncNotesService: Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}