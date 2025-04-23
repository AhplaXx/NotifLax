package com.necdetzr.notiflax2.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.necdetzr.notiflax2.data.model.ReminderModel

class ReminderRepository {
    private val db = FirebaseFirestore.getInstance()
    private val remindersCollection = db.collection("reminders")

    fun addReminder(reminder: ReminderModel,onComplete:(Boolean)->Unit) {
        val document = remindersCollection.document()
        val reminderWithId=reminder.copy(id = document.id)
        document.set(reminderWithId)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }

    }
    fun getReminders(userId:String,onResult:(List<ReminderModel>)->Unit){
     val db = FirebaseFirestore.getInstance()
     remindersCollection
         .whereEqualTo("userId",userId)
         .get()
         .addOnSuccessListener {documents->
             val reminders = documents.map { it.toObject(ReminderModel::class.java) }
             onResult(reminders)

         }
         .addOnFailureListener{
             onResult(emptyList())


         }
    }
    fun deleteReminder(reminderId: String, onComplete: (Boolean) -> Unit) {
        remindersCollection.document(reminderId).delete()
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }
}