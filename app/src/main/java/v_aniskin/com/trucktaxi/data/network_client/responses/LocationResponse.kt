package v_aniskin.com.trucktaxi.data.network_client.responses

import java.util.*

/**
 * Created by root on 17.09.17.
 */
class LocationResponse {

    var georouteData = GeorouteData()

    class GeorouteData {

        var georouteGeoetry: List<Array<Double>> = Collections.emptyList()
    }
}