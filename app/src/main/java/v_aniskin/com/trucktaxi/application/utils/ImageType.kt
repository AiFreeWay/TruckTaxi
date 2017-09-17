package v_aniskin.com.trucktaxi.application.utils

/**
 * Created by root on 17.09.17.
 */
class ImageType {

    companion object {

        val IMAGE_DRIVER_RULE = 10
        val IMAGE_REGISTRATION = 11
        val IMAGE_PTC = 12
        val IMAGE_OSAGO = 13

        val CAR_PHOTO_1 = 14
        val CAR_PHOTO_2 = 15
        val CAR_PHOTO_3 = 16
        val CAR_PHOTO_INTERIER = 17


        fun isImageType(value: Int): Boolean =
                when(value) {
                    IMAGE_DRIVER_RULE -> true
                    IMAGE_REGISTRATION -> true
                    IMAGE_PTC -> true
                    IMAGE_OSAGO -> true

                    CAR_PHOTO_1 -> true
                    CAR_PHOTO_2 -> true
                    CAR_PHOTO_3 -> true
                    CAR_PHOTO_INTERIER -> true
                    else -> false
                }
    }
}