package com.rivaldy.id.mvvmtemplateapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rivaldy.id.mvvmtemplateapp.data.DataRepository
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rivaldy on 27/10/21
 * Find me on my Github -> https://github.com/im-o
 */

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MovieViewModel(dataRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testSomeUI(): Unit = runBlocking {
        launch(Dispatchers.Main) {

        }
    }

    @Test
    fun getMovies(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            viewModel.getMoviesApiCall()
            val movies = viewModel.movies.getOrAwaitValue()
            print("getMoviesApiCall getMovies() execute : 1\n")
            when (movies) {
                is DataResource.Loading -> print("getMoviesApiCall : Loading")
                is DataResource.Success -> print("getMoviesApiCall : Movies ${movies.value}")
                is DataResource.Failure -> print("getMoviesApiCall : Failure")
                else -> print("getMoviesApiCall : VALUE -> $movies")
            }
        }
        print("getMoviesApiCall getMovies() execute : 2\n")
    }

    @Test
    fun getMoviesApiCall() {
    }
}