package com.necdetzr.notiflax2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.notiflax2.data.model.ReminderModel
import com.necdetzr.notiflax2.data.repository.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReminderViewModel : ViewModel() {
    private val repository = ReminderRepository()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _reminders = MutableStateFlow<List<ReminderModel>>(emptyList())
    val reminders: StateFlow<List<ReminderModel>> = _reminders
    private val _isReminderAdded = MutableStateFlow<Boolean?>(false)
    val isReminderAdded: StateFlow<Boolean?> = _isReminderAdded
    private val _isReminderDeleted = MutableStateFlow<Boolean?>(false)
    val isReminderDeleted: StateFlow<Boolean?> = _isReminderDeleted

    fun fetchReminders(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getReminders(userId) { reminderList ->
                _reminders.value = reminderList
                _isLoading.value = false
                println("reminders = ${reminderList}")
            }
        }
    }

    fun addReminder(reminder: ReminderModel) {
        repository.addReminder(reminder) { success ->
            _isReminderAdded.value = false
            if (success) {
                _isReminderAdded.value = true
                println("Success reminder added.")
            }
        }
    }
    fun resetReminderAddedState() {
        _isReminderAdded.value = false
    }
    fun deleteReminder(reminderId: String,userId: String) {
        viewModelScope.launch {
            repository.deleteReminder(reminderId) { success ->
                _isReminderDeleted.value = false

                if (success) {
                    _isReminderAdded.value = true
                    fetchReminders(userId)
                    println("Success reminder deleted.")
                }
            }
        }

    }

}