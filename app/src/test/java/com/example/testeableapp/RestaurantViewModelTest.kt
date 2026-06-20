package com.example.testeableapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class RestaurantViewModelTest {

    private lateinit var viewModel: RestaurantViewModel

    @Before
    fun setup() {
        viewModel = RestaurantViewModel()
    }

    //1 Agregar item al pedido
    @Test
    fun testAgregarItemCantidadEsUno() {
        viewModel.addItem(1)

        val cantidad = viewModel.quantities.value[1]
        assertEquals(1, cantidad)
    }

    //2 Incrementar/Decrementar cantidad
    @Test
    fun testIncrementarYDecrementarModificaCantidad() {
        viewModel.addItem(2)

        viewModel.incrementItem(2)
        assertEquals(2, viewModel.quantities.value[2])

        viewModel.decrementItem(2)
        assertEquals(1, viewModel.quantities.value[2])
    }

    //3 Eliminar item al decrementar desde 1
    @Test
    fun testDecrementarDesdeUnoEliminaItem() {
        viewModel.addItem(3)
        viewModel.decrementItem(3)

        assertFalse(viewModel.quantities.value.containsKey(3))
    }

    //4 Cálculo del total a pagar
    @Test
    fun testCalculoTotalSumaCorrectamente() {
        viewModel.addItem(1)
        viewModel.addItem(2)

        val totalEsperado = 11.50
        assertEquals(totalEsperado, viewModel.total.value, 0.01)
    }

    //1er prueba extra
    @Test
    fun testDecrementarItemNoExistenteNoHaceNada() {
        viewModel.decrementItem(5)

        assertFalse(viewModel.quantities.value.containsKey(5))
    }

    //2da prueba extra
    @Test
    fun testDismissConfirmationLimpiaElPedido() {
        viewModel.addItem(1)

        viewModel.dismissConfirmation()

        assertEquals(0, viewModel.quantities.value.size)
    }
}
