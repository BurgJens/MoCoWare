package de.mocoware.model

import android.util.Log
import com.google.android.gms.nearby.connection.*
import java.util.*


data class AvailableGame(val name: String, val gameID: String)

object GameConnection{

    private var onlineMode = false

    private var host = false

    private var gameToJoin : AvailableGame? = null
    private var currentGame : Game? = null

    private val games = mutableListOf<Game>()

    fun addNewGame(name: String, rounds: Int) {
        games.add(Game(name, rounds))
    }

    fun getAvailableGames() : List<AvailableGame>{
        val list = mutableListOf<AvailableGame>()
        games.forEach{ each ->
            list.add(AvailableGame(each.getName(), each.getID()))
        }
        return list.toList()
    }

    fun getGameByID(id : String) : Game{
        val i = games.firstOrNull{ it.getID() == id.substring(1..4) }
        if (i == null){
            return Game("Wrong ID")
        }else return i
    }

    fun setOnlineMode(setMode : Boolean){
        onlineMode = setMode
    }

    fun setCurrentGame(game: Game){
        currentGame = game
    }

    fun getCurrentGame() : Game?{
        return currentGame
    }

    fun getCurrentGameName() : String?{
        return currentGame?.getName()
    }

    fun getCurrentGameID() : String?{
        return currentGame?.getID()
    }
    //Nearby Implementationen

    var mRemoteEndpointId: String? = null
    lateinit var mConnectionsClient: ConnectionsClient

    fun startAdvertising() {
        mConnectionsClient.startAdvertising(
            getNickName(),
            SERVICE_ID,
            mConnectionLifecycleCallback,
            AdvertisingOptions(Strategy.P2P_CLUSTER)
        )
            .addOnSuccessListener {

        }
            .addOnFailureListener {

            }

    }

    private fun stopAdvertising() {
        mConnectionsClient.stopAdvertising()
    }

    fun startDiscovery(){
        mConnectionsClient.startDiscovery(
            packageName,
            mEndpointDiscoveryCallback,
            DiscoveryOptions(Strategy.P2P_CLUSTER)
        )
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
    private fun stopDiscovery() {
        mConnectionsClient.stopDiscovery()
    }

    private fun sendString(content: String) {
        mConnectionsClient.sendPayload(
            mRemoteEndpointId!!,
            Payload.fromBytes(content.toByteArray(Charsets.UTF_8))
        )
    }

    private fun disconnectFromEndpoint() {
        mConnectionsClient.disconnectFromEndpoint(mRemoteEndpointId!!)
        mRemoteEndpointId = null
    }

    private val mEndpointDiscoveryCallback = object : EndpointDiscoveryCallback() {

        override fun onEndpointFound(endpointId: String, info: DiscoveredEndpointInfo) {
            // An endpoint was found. We request a connection to it.


            mConnectionsClient.requestConnection(
                getNickName(),
                endpointId,
                mConnectionLifecycleCallback
            )
                .addOnSuccessListener {
                    // We successfully requested a connection. Now both sides
                    // must accept before the connection is established.

                }
                .addOnFailureListener {
                    // Nearby Connections failed to request the connection.
                    // TODO: retry

                }
        }

        override fun onEndpointLost(endpointId: String) {
            // A previously discovered endpoint has gone away.

        }
    }

    private val mConnectionLifecycleCallback = object : ConnectionLifecycleCallback() {

        override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {


            // Automatically accept the connection on both sides.
            mConnectionsClient.acceptConnection(endpointId, mPayloadCallback)
        }

        override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {


            when (result.status.statusCode) {
                ConnectionsStatusCodes.STATUS_OK -> {

                    // We're connected! Can now start sending and receiving data.
                    mRemoteEndpointId = endpointId

                    sendString("Hello ${mRemoteEndpointId}!")
                }

                ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED -> {
                    // The connection was rejected by one or both sides.

                }

                ConnectionsStatusCodes.STATUS_ERROR -> {
                    // The connection broke before it was able to be accepted.

                }
            }
        }

        override fun onDisconnected(endpointId: String) {
            // We've been disconnected from this endpoint. No more data can be
            // sent or received.

            disconnectFromEndpoint()
        }

    }

    private val mPayloadCallback = object : PayloadCallback() {
        override fun onPayloadReceived(endpointId: String, payload: Payload) {
            //debug("mPayloadCallback.onPayloadReceived $endpointId")

            when (payload.type) {
                Payload.Type.BYTES -> {
                    val data = payload.asBytes()!!
                    //debug("Payload.Type.BYTES: ${data.toString(Charsets.UTF_8)}")
                }
                Payload.Type.FILE -> {
                    val file = payload.asFile()!!
                    //debug("Payload.Type.FILE: size: ${file.size}")
                    // TODO:

                }
                Payload.Type.STREAM -> {
                    val stream = payload.asStream()!!
                    //debug("Payload.Type.STREAM")
                    // TODO:
                }
            }
        }

        override fun onPayloadTransferUpdate(endpointId: String, update: PayloadTransferUpdate) {
            // Bytes payloads are sent as a single chunk, so you'll receive a SUCCESS update immediately
            // after the call to onPayloadReceived().
            // debug("mPayloadCallback.onPayloadTransferUpdate $endpointId")
        }
    }


    fun onConnectionInitiated(){
        TODO()

    }

    private fun getNickName() = UUID.randomUUID().toString()



}