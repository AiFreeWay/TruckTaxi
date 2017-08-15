package v_aniskin.com.trucktaxi.domain.models

/**
 * Created by root on 22.07.17.
 */

class Profile(error: String?, status: String?): ResponseMonade(error, status)  {

    var mFirstName: String? = ""
    var mLastName: String? = ""
    var mFatherName: String? = ""
    var mEmail: String? = ""
    var mFormalizDate: String? = ""
    var mRate: String? = ""
    var mStatus: String? = ""
    var mPhotoFileId: String? = ""
    var mMainCarNumber: String? = ""
    var mMainCarModel: String? = ""
    var mMainCarType: String? = ""
    var mPhoto: String? = ""
    var mIsActive: String? = ""
    var mId: String? = ""
    var mType: String? = ""
}
