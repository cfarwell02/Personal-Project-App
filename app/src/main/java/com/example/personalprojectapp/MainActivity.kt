package com.example.personalprojectapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            PracticeTheme {
                val contacts = remember { mutableStateListOf<Contacts>() }
                AppNavigation(people = contacts)
            }
        }
    }
}

@Composable
fun AppNavigation(people: List<Contacts>) {
    // State variable to track the current screen
    val currentScreen = remember { mutableStateOf("home") }
    val usecfarwell02/GitHub-Android-ApprInputList = remember { mutableStateListOf<String>() }
    val contacts = remember { mutableStateListOf(*people.toTypedArray()) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    PracticeTheme(darkTheme = darkModeEnabled) {
        Crossfade(targetState = currentScreen.value, label = "") { screen ->
            when (screen) {

                "home" -> HomeScreen(
                    onNavigateToUserList = { currentScreen.value = "userList" },
                    onNavigateToOtherScreen = { currentScreen.value = "betweenscreens" },
                    onNavigateToSettings = { currentScreen.value = "settings" },
                    onNavigateToMoneyScreen = { currentScreen.value = "money"}
                )

                "userList" -> UserListScreen(
                    onNavigate = { currentScreen.value = "home" },
                    userInputList = userInputList
                )

                "betweenscreens" -> ContactScreen(
                    onNavigateBack = { currentScreen.value = "home" },
                    contacts = contacts,
                    onNavigateToAddContact = { currentScreen.value = "addContact" }
                )

                "settings" -> SettingsScreen(
                    darkModeEnabled = darkModeEnabled,
                    onDarkModeToggle = { darkModeEnabled = it },
                    onNavigateBack = { currentScreen.value = "home" }
                )

                "addContact" -> AddContactScreen(
                    onNavigateBack = { currentScreen.value = "betweenscreens" },
                    onAddContact = { newContact -> contacts.add(newContact) }
                )

                "money" -> MoneyScreen(
                    onNavigateBack = { currentScreen.value = "home" },
                )
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigateToUserList: () -> Unit, onNavigateToOtherScreen: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToMoneyScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome to your App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )


        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(onClick = onNavigateToUserList) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_list),
                        contentDescription = "Go to User List",
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    "User List",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(onClick = onNavigateToMoneyScreen) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_money),
                        contentDescription = "Go to Money Screen",
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    "Money",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(onClick = onNavigateToOtherScreen) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_contacts),
                        contentDescription = "Go to Contacts",
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text("Contacts",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(onClick = onNavigateToSettings) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_settings),
                        contentDescription = "Go to Contacts",
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text("Settings",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun UserListScreen(onNavigate: () -> Unit, userInputList: MutableList<String>) {
    val context = LocalContext.current
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Enter something") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.systemBars.asPaddingValues())
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (userInput.isNotBlank()) {
                    userInputList.add(userInput)
                    Toast.makeText(context, "Added: $userInput", Toast.LENGTH_SHORT).show()
                    userInput = ""
                } else {
                    Toast.makeText(context, "Input cannot be empty", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add to List")
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "My List",
                style = MaterialTheme.typography.headlineLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(userInputList) { index, item ->
                    Row {
                        Text (
                            text = "${index + 1}.",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .padding(top = 3.dp, end = 4.dp)
                                .weight(1f)
                        )

                        IconButton(
                            onClick = {
                                userInputList.remove(item)
                                Toast.makeText(context, "Removed: $item", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.image_delete),
                                contentDescription = "Delete",
                                modifier = Modifier.offset(y = (-6).dp)
                            )
                        }
                    }
                }
            }

            Button(
                onClick = onNavigate,
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Go back to Apps")
            }
        }
    }
}

@Composable
fun ContactScreen(onNavigateBack: () -> Unit, contacts: MutableList<Contacts>, onNavigateToAddContact: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Contacts",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 16.dp)
                        .align(Alignment.TopEnd)

                )
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .align(Alignment.TopStart),

                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "Back to Home Screen",
                        modifier = Modifier
                            .size(200.dp)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(contacts) { contact ->
                    ContactCard(contact, onDelete = { contacts.remove(contact)})
                }
            }
        }

        FloatingActionButton(
            onClick = onNavigateToAddContact,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Contact"
            )
        }
    }
}

@Composable
fun SettingsScreen(darkModeEnabled: Boolean, onDarkModeToggle: (Boolean) -> Unit, onNavigateBack: () -> Unit) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(WindowInsets.systemBars.asPaddingValues())

        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Dark Mode", modifier = Modifier.weight(1f))
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = onDarkModeToggle

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Notifications", modifier = Modifier.weight(1f))
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }

        Button(onClick = onNavigateBack, modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp)) {
            Text("Back to Home")
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun MoneyScreen(onNavigateBack: () -> Unit) {
    var balance by remember { mutableDoubleStateOf(0.0) }
    val transactions = remember { mutableStateListOf<Transaction>() }
    var amountInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Total Balance: $${String.format("%.2f", balance)}",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(WindowInsets.systemBars.asPaddingValues())
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(vertical = 8.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isExpense) "Expense" else "Income",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp)
            )

            Switch(
                checked = isExpense,
                onCheckedChange = { isExpense = it },
                modifier = Modifier.padding(16.dp)
            )
        }


        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descriptionInput,
            onValueChange = { descriptionInput = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (amountInput.isNotBlank() && descriptionInput.isNotBlank()) {
                    val amount = amountInput.toDoubleOrNull()
                    if (amount != null) {
                        val adjustedAmount = if (isExpense) -amount else amount
                        transactions.add(Transaction(descriptionInput, amount, isExpense))
                        balance += adjustedAmount
                        amountInput = ""
                        descriptionInput = ""
                    }
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Input Transaction")
        }

        Text(
            text = "Transactions:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(transactions) { transaction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = transaction.description,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = if (transaction.isExpense)
                            "-$${String.format("%.2f", transaction.amount)}"
                        else
                            "$${String.format("%.2f", transaction.amount)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .padding(WindowInsets.systemBars.asPaddingValues())
                .width(200.dp)
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Go back to Apps")
        }
    }
}

@Composable
fun AddContactScreen(onNavigateBack: () -> Unit, onAddContact: (Contacts) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Add New Contact",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 2.dp)
                .padding(WindowInsets.systemBars.asPaddingValues())
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (firstName.isNotBlank() && lastName.isNotBlank() && age.isNotBlank() && phoneNumber.isNotBlank()) {
                    val newContact = Contacts(
                        firstName = firstName,
                        lastName = lastName,
                        age = age.toInt(),
                        phoneNumber = phoneNumber
                    )
                    onAddContact(newContact)
                    onNavigateBack()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Contact")
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        ) {
            Text("Cancel")
        }
    }
}

@Composable
fun ContactCard(contacts: Contacts, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.person_image),
                contentDescription = "Photo of person",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${contacts.firstName} ${contacts.lastName}",
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                Text(text = "Age: ${contacts.age}")

                Text(
                    text = "Phone Number: ${contacts.phoneNumber}",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(id = R.drawable.image_delete),
                    contentDescription = "Delete Contact"
                )
            }
        }
    }
}

@Composable
fun PracticeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}