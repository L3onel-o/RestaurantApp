package com.example.testeableapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class RestaurantUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //1 Mensaje de pedido vacío visible al inicio (1 pt)
    @Test
    fun testMensajePedidoVacioVisibleAlInicio() {
        composeTestRule.setContent { RestaurantOrderApp(viewModel = RestaurantViewModel()) }

        composeTestRule.onNodeWithTag("emptyOrderMessage").assertIsDisplayed()
    }

    //2 Todos los items del menú visibles
    @Test
    fun testItemsDelMenuEstanVisibles() {
        composeTestRule.setContent { RestaurantOrderApp(viewModel = RestaurantViewModel()) }

        composeTestRule.onNodeWithTag("menuItem_1").assertExists()
        composeTestRule.onNodeWithTag("menuItem_10").assertExists()
    }

    //3 El total general se actualiza
    @Test
    fun testTotalGeneralSeActualizaAlAgregarItem() {
        composeTestRule.setContent { RestaurantOrderApp(viewModel = RestaurantViewModel()) }

        composeTestRule.onNodeWithTag("addButton_1").performClick()

        composeTestRule.onNodeWithTag("totalValue").assertTextEquals("5.50 €")
    }



    //1ra prueba extra
    @Test
    fun testRealizarPedidoMuestraDialogoConfirmacion() {
        composeTestRule.setContent { RestaurantOrderApp(viewModel = RestaurantViewModel()) }

        composeTestRule.onNodeWithTag("addButton_2").performClick()

        composeTestRule.onNodeWithTag("placeOrderButton").performScrollTo().performClick()

        composeTestRule.onNodeWithTag("confirmationDialog").assertIsDisplayed()
    }

    //2da orueba extra
    @Test
    fun testIncrementarCantidadDesdeElCarritoActualizaLaVista() {

        composeTestRule.setContent { RestaurantOrderApp(viewModel = RestaurantViewModel()) }

        composeTestRule.onNodeWithTag("addButton_1").performClick()

        composeTestRule.onNodeWithTag("incrementOrderItem_1").performScrollTo().performClick()

        composeTestRule.onNodeWithTag("orderItemQuantity_1").assertTextEquals("2")
    }
}