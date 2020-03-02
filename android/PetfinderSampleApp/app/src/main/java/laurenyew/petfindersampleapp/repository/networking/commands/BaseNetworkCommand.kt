package laurenyew.petfindersampleapp.repository.networking.commands

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import laurenyew.petfindersampleapp.repository.networking.PetFinderApiBuilder
import laurenyew.petfindersampleapp.repository.networking.api.PetfinderApi
import kotlin.coroutines.CoroutineContext

/**
 * Base network command with appropriate coroutine scope
 */
open class BaseNetworkCommand : CoroutineScope {
    private var job = Job()
    var api: PetfinderApi? = PetFinderApiBuilder.apiBuilder(PetfinderApi::class.java)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun finish() {
        job.cancel()
    }

    companion object {
        val TAG: String = BaseNetworkCommand::class.java.simpleName
    }
}