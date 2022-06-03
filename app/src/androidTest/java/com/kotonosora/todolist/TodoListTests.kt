package com.kotonosora.todolist

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoListTests {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scroll_to_item() {
//        onView(withId(R.id.todos_list)).perform(
//            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3)
//        )
//        onView(withText("Todo 3")).check(matches(isDisplayed()))
    }
}