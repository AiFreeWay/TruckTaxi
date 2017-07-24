package v_aniskin.com.trucktaxi.domain.mappers

import v_aniskin.com.trucktaxi.data.network_client.responses.ProfileResponse
import v_aniskin.com.trucktaxi.domain.models.Profile

/**
 * Created by root on 22.07.17.
 */

class ProfileMapper {

    companion object {

        fun mapProfile(response: ProfileResponse): Profile {
            val profile: Profile = Profile(response.error, response.status)
            profile.mFirstName = response.myFirstName
            profile.mLastName = response.myLastName
            profile.mFatherName = response.myFatherName
            profile.mEmail = response.myEmail
            profile.mFormalizDate = response.myFormalizDate
            profile.mRate = response.myRate
            profile.mStatus = response.myStatus
            profile.mPhotoFileId = response.myPhotoFileId
            profile.mMainCarNumber = response.myMainCarNumber
            profile.mMainCarModel = response.myMainCarModel
            profile.mMainCarType = response.myMainCarType
            profile.mPhoto = response.myPhoto
            profile.mIsActive = response.isActive
            profile.mId = response.myId
            profile.mType = response.myType
            return profile
        }
    }
}
