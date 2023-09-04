package pe.edu.upeu.asistenciaupeujc


import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.navigation.NavigationHost
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.AppDrawer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Dialog
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.MultiFloatingActionButton
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.TopBar
import pe.edu.upeu.asistenciaupeujc.ui.theme.AsistenciaUPeUJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkMode = remember { mutableStateOf(false) }
            AsistenciaUPeUJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    MainScreen(darkMode = darkMode)
                }
                
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AsistenciaUPeUJCTheme {
        Greeting("Android")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    darkMode: MutableState<Boolean>,
    //themeType: MutableState<ThemeType>
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val navigationItems = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.Pantalla4
    )
    val navigationItems2 = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
    )
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: Destinations.Pantalla1.route
    val list = currentRoute.split("/")
    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(route = list[0], scope = scope, scaffoldState = drawerState,
                navController = navController, items = navigationItems)
                        },
        drawerState = drawerState) {
        val snackbarHostState = remember { SnackbarHostState() }
        val snackbarMessage = "Succeed!"
        val showSnackbar = remember { mutableStateOf(false) }
        val context = LocalContext.current
        val fabItems = listOf(
            FabItem(
                Icons.Filled.ShoppingCart,
                "Shopping Cart"
            ) {
                val toast = Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG) // in Activity
                toast.view!!.getBackground().setColorFilter(android.graphics.Color.CYAN,PorterDuff.Mode.SRC_IN)
                toast.show()
            },
            FabItem(
                Icons.Filled.Favorite,
                "Favorite"
            ) { /*TODO*/ }
        )

        Scaffold(
            topBar = {
                TopBar(
                    list[0],
                    scope,
                    drawerState,
                    openDialog = { openDialog.value = true },
                    displaySnackBar = {
                        scope.launch {
                            if (showSnackbar.value)
                                snackbarHostState.showSnackbar(snackbarMessage,duration = SnackbarDuration.Short,
                                    actionLabel = "Aceptar")

                            when(showSnackbar.value){
                                true ->{
                                Log.d("MainActivity", "Snackbar Accionado")
                            }
                                false ->{
                                Log.d("MainActivity", "Snackbar Ignorado")
                            }
                            }
                        }
                    }
                )
            }, modifier = Modifier,
            floatingActionButton = {
                MultiFloatingActionButton(
                    fabIcon = Icons.Filled.Add,
                    items = fabItems,
                    showLabels = true
                )
                /*MultiFloatingActionButton(
                    items = listOf(
                        FabButtonItem(
                            iconRes = Icons.Filled.Home,
                            label = "Home"
                        ),
                        FabButtonItem(
                            iconRes = Icons.Filled.List,
                            label = "Lista"
                        ),
                        FabButtonItem(
                            iconRes = Icons.Filled.Notifications,
                            label = "Noti"
                        )
                    ),
                    onFabItemClicked = {
                        Toast.makeText(context, it.label, Toast.LENGTH_SHORT).show()
                    },
                    fabIcon = FabButtonMain(),
                    fabOption = FabButtonSub()
                )*/
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = { BottomAppBar {
                BottomNavigationBar(navController = navController)

            }}
        ) {
            NavigationHost(navController, darkMode, modif= it )
        }

    }
    Dialog(showDialog = openDialog.value, dismissDialog = {
        openDialog.value = false })
}