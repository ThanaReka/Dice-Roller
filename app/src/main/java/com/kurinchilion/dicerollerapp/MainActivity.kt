package com.kurinchilion.dicerollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kurinchilion.dicerollerapp.ui.theme.DiceRollerAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/* Note: The import androidx.compose.ui.Modifier statement imports the
 * androidx.compose.ui.Modifier package, which lets you reference the Modifier object.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerAppTheme {
                DiceRollerApp()
            }
        }
    }
}

/*
 * Most composable functions allow a modifier parameter to be passed in.
 * The default value of the modifier parameter is a Modifier object,
 * hence the = Modifier piece of the method signature. The default value of a parameter
 * lets anyone who calls this method in the future decide whether to pass a value for the parameter.
 * If they pass their own Modifier object, they can customize the behavior and decoration of the UI.
 * If they choose not to pass a Modifier object, it assumes the value of the default, which is the plain Modifier object.
 * The reason you should bother to pass a Modifier argument at all when there's a default
 * is because composables can undergo recomposition, which essentially means that the block of code in the @Composable method executes again.
 * If a Modifier object is created in a block of code, it could potentially be recreated and that isn't efficient.
 */


@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
//            painter = painterResource(R.drawable.dice_1),
//            contentDescription = "1"
            painter = painterResource(id = imageResource),
            contentDescription = result.toString())
        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { /*TODO*/ }) {
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}

/*
 * The modifier argument ensures that the composables in the Column() function
 * adhere to the constraints called on the modifier instance.
 */

/*
 * The wrapContentSize() method specifies that the available space should at least be as large
 * as the components inside of it.
 * However, because the fillMaxSize() method is used, if the components inside of the layout are
 * smaller than the available space, an Alignment object can be passed to wrapContentSize() method
 * that specifies how the components should align within the available space.
 */

/*
 * The Spacer composable takes a Modifier as a parameter. In this case, the Image is above the
 * Button, so there needs to be a vertical space between them. Therefore, the Modifier's height
 * can be set to apply to the Spacer.  Typically, dp dimensions are changed in increments of 4.dp.
 */

/*
 * The braces that the onClick parameter, is set to represent what is known as a lambda,
 * the area inside of the braces being the lambda body. When a function is passed as an argument,
 * it can also be referred to as a "callback".
*/

/*
 * The random() method in Kotlin can be used on a number range.
 * Ranges are designated by two periods between the first number in the range and the last number
 * in the range.
 */

/*
 * Composables are stateless by default, which means that they don't hold a value and can be recomposed
 * any time by the system, which results in the value being reset. However, Compose provides a convenient
 * way to avoid this by storing an object in memory using the remember composable.
 */

/*
 * The mutableStateOf() function returns an observable. You learn more about observables later,
 * but for now this basically means that when the value of the result variable changes,
 * a recomposition is triggered, the value of the result is reflected, and the UI refreshes.
 */

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

