package com.menadev.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class AddNotesActivity : AppCompatActivity() {
         private lateinit var titleED:EditText
         private lateinit var descriptionED:EditText
         private lateinit var savenotesBtn:Button
         private lateinit var realm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

       // init Views
        realm= Realm.getDefaultInstance()
        titleED = findViewById(R.id.title_EdiText)
        descriptionED = findViewById(R.id.title_description)
        savenotesBtn = findViewById(R.id.saveNotesBtn)

      //  onclick listener

        savenotesBtn.setOnClickListener {

            addNotesToDB()

        }




    }

    private fun addNotesToDB() {

        try {
            //Auto Increment ID

            realm.beginTransaction()
            val currentIdNumber:Number?=realm.where(Notes::class.java).max("id")
            val nextID:Int
            nextID = if (currentIdNumber==null){
                1

            }else{
                currentIdNumber.toInt() +1
            }


            val notes = Notes()
            notes.title = titleED.text.toString()
            notes.description = descriptionED.text.toString()
            notes.id= nextID



         //   copy this transaction & commit
            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()

            Toast.makeText(this,"Notes added Successfully",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()


        }catch (e:Exception){

            Toast.makeText(this,"Failed $e",Toast.LENGTH_SHORT).show()



        }



    }
}