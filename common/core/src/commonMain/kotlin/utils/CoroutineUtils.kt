package utils

import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runCatchingNonCancellation(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        e.printStackTrace()

        //TODO: сделать нормальную обработку ошибок
        Result.failure(Exception("Something went wrong"))
    }
}