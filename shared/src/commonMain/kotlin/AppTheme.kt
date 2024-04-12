import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle
import models.AlertTone
import models.Tone
import myapplication.shared.generated.resources.FiraCode_Bold
import myapplication.shared.generated.resources.FiraCode_Light
import myapplication.shared.generated.resources.FiraCode_Medium
import myapplication.shared.generated.resources.FiraCode_Regular
import myapplication.shared.generated.resources.FiraCode_Retina
import myapplication.shared.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

val green = Color(0xff27ae60)
val lightGreen = Color(0xff2ecc71)
val turquoise = Color(0xff16a085)
val lightTurquoise = Color(0xff1abc9c)
val blue = Color(0xff2980b9)
val lightBlue = Color(0xff3498db)
val purple = Color(0xff8e44ad)
val lightPurple = Color(0xff9b59b6)
val navy = Color(0xff2c3e50)
val lightNavy = Color(0xff34495e)
val lightLightNavy = Color(0xff54697e)
val yellow = Color(0xfff39c12)
val lightYellow = Color(0xfff1c40f)
val orange = Color(0xffd35400)
val lightOrange = Color(0xffe67e22)
val red = Color(0xffc0392b)
val lightRed = Color(0xffe74c3c)

val lightGrey = Color(0xffD5D5D5)
val medGrey = Color(0xff929292)
val darkGrey = Color(0xff646464)

var color = mutableStateOf(navy)
var appStyle = mutableStateOf(PaletteStyle.FruitSalad)
var defaultTone = mutableStateOf(Tone(AlertTone.Casual.name))

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val fontFamily = FontFamily(
        Font(Res.font.FiraCode_Bold),
        Font(Res.font.FiraCode_Medium),
        Font(Res.font.FiraCode_Light),
        Font(Res.font.FiraCode_Regular),
        Font(Res.font.FiraCode_Retina),
    )

    val defaultTypography = Typography()
    DynamicMaterialTheme(
        animate = true,
        seedColor = color.value,
        style = appStyle.value,
        isExtendedFidelity = false,
        typography = Typography(
            displayLarge = defaultTypography.displayLarge.copy(fontFamily = fontFamily),
            displayMedium = defaultTypography.displayMedium.copy(fontFamily = fontFamily),
            displaySmall = defaultTypography.displaySmall.copy(fontFamily = fontFamily),

            headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = fontFamily),

            titleLarge = defaultTypography.titleLarge.copy(fontFamily = fontFamily),
            titleMedium = defaultTypography.titleMedium.copy(fontFamily = fontFamily),
            titleSmall = defaultTypography.titleSmall.copy(fontFamily = fontFamily),

            bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = defaultTypography.bodySmall.copy(fontFamily = fontFamily),

            labelLarge = defaultTypography.labelLarge.copy(fontFamily = fontFamily),
            labelMedium = defaultTypography.labelMedium.copy(fontFamily = fontFamily),
            labelSmall = defaultTypography.labelSmall.copy(fontFamily = fontFamily)
        ),
        content = content
    )
}