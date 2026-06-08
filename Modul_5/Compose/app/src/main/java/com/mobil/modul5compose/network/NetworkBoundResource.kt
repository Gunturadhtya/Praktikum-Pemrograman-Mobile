package com.mobil.modul5compose.network

import com.mobil.modul5compose.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
): Flow<Resource<ResultType>> = flow {

    val data = query().firstOrNull()

    if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            val fetchResult = fetch()
            saveFetchResult(fetchResult)
            emitAll(query().map { Resource.Success(it) })
        } catch (throwable: Throwable) {
            emitAll(query().map { Resource.Error(throwable, it) })
        }
    } else {
        emitAll(query().map { Resource.Success(it!!) })
    }
}