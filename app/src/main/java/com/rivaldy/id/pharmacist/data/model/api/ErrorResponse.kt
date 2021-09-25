package com.rivaldy.id.pharmacist.data.model.api


import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    /**
     * TODO-CHANGE
     * CHANGE THIS DATA CLASS WITH YOUR ERROR RESPONSE API
     **/

    @SerializedName("status")
    var statusCode: Int? = null,
    @SerializedName("message")
    var statusMessage: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
)