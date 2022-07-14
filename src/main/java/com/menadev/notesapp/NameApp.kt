package com.menadev.notesapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class NameApp :Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .name("notes.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)




    }




}