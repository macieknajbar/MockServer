package com.example.mockserver.main

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private val view = Mockito.mock(MainView::class.java)

    @Before fun setUp() {
        presenter = MainPresenter(view)
    }

    @Test fun `asd`() {

    }
}