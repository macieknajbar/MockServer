package com.example.mockserver.rest.api

import com.example.mockserver.rest.api.data.DataPresenter
import org.junit.Before
import org.mockito.Mockito

class DataPresenterApiTest {

    private lateinit var dataPresenter: DataPresenterApi
    private val presenter = Mockito.mock(DataPresenter::class.java)

    @Before fun setUp() {
        dataPresenter = DataPresenterApi(presenter)
    }


}