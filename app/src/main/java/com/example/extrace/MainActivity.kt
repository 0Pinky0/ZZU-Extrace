package com.example.extrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.extrace.models.User
import com.example.extrace.models.UserLoader
import com.example.extrace.ui.theme.ExtraceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val users = UserLoader.getAll()
        setContent {
            ExtraceTheme {
                UserApp(users = users, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun UserRow(user: User) {
    // 一块板子，以及横竖间隔
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // 板子里的内容
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            // 占满宽度，长度留空24dp
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        ) {
            Text(user.lastName + ' ' + user.firstName, fontSize = 17.sp)
            Text(user.telephone, fontSize = 15.sp)
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(users) { user ->
            UserRow(user)
        }
    }
}

@Composable
fun UserApp(users: List<User>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        UserList(users = users)
    }
}

@Preview(showBackground = true)
@Composable
fun UserAppPreview() {
    MaterialTheme {
        UserApp(
            users = listOf(
                User(
                    1,
                    "0Pinky0",
                    "005135",
                    "佳乐",
                    "王",
                    "18853187736"
                ),
                User(
                    2,
                    "Dovish",
                    "005135",
                    "天浩",
                    "李",
                    "13583167770"
                ),
            ), modifier = Modifier.fillMaxSize()
        )
    }
}
