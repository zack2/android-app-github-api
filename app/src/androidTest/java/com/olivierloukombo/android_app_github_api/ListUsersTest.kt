package com.olivierloukombo.android_app_github_api

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.olivierloukombo.android_app_github_api.ui.theme.AndroidappgithubapiTheme
import org.junit.Rule
import org.junit.Test

class ListUsersTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun showList(){
        rule.setContent {
           // AndroidappgithubapiTheme()

            //do this
            rule.onNodeWithText("1").performClick()
            //check something
        }
    }
}